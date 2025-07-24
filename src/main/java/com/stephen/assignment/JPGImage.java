package com.stephen.assignment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implements operations (count vertical lines) on JPG format image
 */
public class JPGImage implements Image {
    private RGB bgColor;
    private RGB specifiedLineColor;
    private BufferedImage image;

    @Override
    public int countVerticalLines(int lineMinLength, int pixelRoamingRange) {
        List<VerticalLine> verticalLines = assembleVisualLines(scanVerticalLines(lineMinLength), pixelRoamingRange);
        return verticalLines.size();
    }

    /**
     * Find all vertical lines which lengths are greater or equal than minimum line length configuration.
     */
    private List<VerticalLine> scanVerticalLines(int lineMinLength) {
        int width = image.getWidth();
        int height = image.getHeight();

        List<VerticalLine> verticalLines = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            //tracking start point and end point of vertical line
            int y1 = -1, y2 = -1;
            for (int y = 0; y < height; y++) {
                if (specifiedLineColor.isSameColor(image.getRGB(x, y))) {
                    if (y1 == -1) {
                        y1 = y;
                    } else {
                        y2 = y;
                    }
                } else {
                    if (y2 - y1 + 1 >= lineMinLength) {
                        VerticalLine vl = new VerticalLine(x, y1, y2);
                        verticalLines.add(vl);
                    }
                    y1 = -1;
                    y2 = -1;
                }
            }

            //avoid missing last vertical line
            if (y2 - y1 + 1 >= lineMinLength) {
                VerticalLine vl = new VerticalLine(x, y1, y2);
                verticalLines.add(vl);
            }
        }

        return verticalLines;
    }

    /**
     * Assembles fragmented pixel-aligned vertical lines into complete visual lines
     * by connecting segments within the specified roaming range.
     *
     * @param verticalLines List of raw pixel-aligned line segments
     * @param pixelRoamingRange Maximum gap allowed between segments to be considered
     *                         part of the same visual line (in pixels)
     * @return List of assembled visual vertical lines
     */
    private List<VerticalLine> assembleVisualLines(List<VerticalLine> verticalLines, int pixelRoamingRange) {
        //Before merge, sort vertical lines by y value then x value based on the first point(x1, y1)
        Collections.sort(verticalLines, new Comparator<VerticalLine>() {
            @Override
            public int compare(VerticalLine o1, VerticalLine o2) {
                int delta = o2.y2 - o1.y1;
                if (delta == 0) {
                    delta = o2.x2 - o1.x1;
                }
                return delta;
            }
        });

        List<VerticalLine> mergedList = new ArrayList<>();
        VerticalLine mergedLine = null;
        for (VerticalLine line : verticalLines) {
            if (mergedLine == null) {
                mergedLine = line;
            } else if (line.isEligibleToMerge(mergedLine, pixelRoamingRange)) {
                mergedLine = line;
            } else {
                mergedList.add(VerticalLine.copy(mergedLine));
                mergedLine = null;
            }
        }

        if (mergedLine != null) {
            mergedList.add(mergedLine);
        }

        return mergedList;
    }

    @Override
    public void setBackgroundColor(RGB bgColor) {
        this.bgColor = bgColor;
    }

    @Override
    public void setSpecifiedLineColor(RGB specifiedLineColor) {
        this.specifiedLineColor = specifiedLineColor;
    }

    public void load(String filePath) throws IOException {
        //check the file in class path
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (stream != null) {
                image = ImageIO.read(stream);
                return;
            }
        }

        //load from file system for fail over
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Resource not found in classpath or filesystem: " + filePath, e);
        }
    }

}
