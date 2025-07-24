package com.stephen.assignment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines and provides the convenient methods to access configurations
 */
public class Config {
    public static final String KEY_BACKGROUND_COLOR = "background_color";
    public static final String KEY_LINE_COLOR = "line_color";
    public static final String KEY_LINE_MIN_LENGTH = "line_min_length";
    //Used to determine if two vertical pixel line belongs to same visual vertical line
    public static final String KEY_ROAMING_PIXEL_RANGE = "roaming_pixel_range";

    private static final RGB DEFAULT_BACKGROUND_COLOR = new RGB(1, 1, 1);
    private static final RGB DEFAULT_LINE_COLOR = new RGB(0, 0, 0);
    private static final int DEFAULT_LINE_MIN_LENGTH = 5;
    private static final int DEFAULT_ROAMING_PIXEL_RANGE = 5;

    Pattern rgbPattern = Pattern.compile("rgb\\((\\d+),\\s*(\\d+),\\s*(\\d+)\\)");

    private Properties props = new Properties();

    public Config() {
        props = new Properties();
    }

    public void loadConfig(String filePath) throws IOException {

        try (InputStream input = VerticalLineScanner.class.getResourceAsStream(filePath)) {
            props.load(input);
        }
    }

    public int getPixelRoamingRange() {
        String roamingPixelRangeStr = props.getProperty(KEY_ROAMING_PIXEL_RANGE);
        if (roamingPixelRangeStr == null || roamingPixelRangeStr.isEmpty()) {
            System.out.println("No pixel roaming range configured, using default one");
            return DEFAULT_ROAMING_PIXEL_RANGE;
        }

        int pixelRoamingRage = DEFAULT_ROAMING_PIXEL_RANGE;
        try {
            pixelRoamingRage = Integer.parseInt(roamingPixelRangeStr);
        }catch(Exception e) {
            System.out.println("Invalid pixel roaming range configured, using default one");
        }

        return pixelRoamingRage;
    }

    public int getLineMinLength() {
        String minLengthStr = props.getProperty(KEY_LINE_MIN_LENGTH);
        if (minLengthStr == null || minLengthStr.isEmpty()) {
            System.out.println("No line minimum length configured, using default one");
            return DEFAULT_LINE_MIN_LENGTH;
        }

        int minLength = DEFAULT_LINE_MIN_LENGTH;
        try {
            minLength = Integer.parseInt(minLengthStr);
        }catch(Exception e) {
            System.out.println("Invalid line minimum length configured, using default one");
        }

        return minLength;
    }

    public RGB getBackGroundColor() {
        String colorStr = props.getProperty(KEY_BACKGROUND_COLOR);
        if (colorStr == null || colorStr.isEmpty()) {
            System.out.println("No background color configured, using default one");
            return DEFAULT_BACKGROUND_COLOR;
        }

        RGB color = getColor(colorStr);
        return color == null ? DEFAULT_BACKGROUND_COLOR : color;
    }

    public RGB getLineColor() {
        String colorStr = props.getProperty(KEY_LINE_COLOR);
        if (colorStr == null || colorStr.isEmpty()) {
            System.out.println("No line color configured, using default one");
            return DEFAULT_LINE_COLOR;
        }

        RGB color = getColor(colorStr);
        return color == null ? DEFAULT_LINE_COLOR : color;
    }

    private RGB getColor(String colorStr) {
        RGB color = null;
        Matcher matcher = rgbPattern.matcher(colorStr);
        if (matcher.find()) {
            int r = Integer.parseInt(matcher.group(1));
            int g = Integer.parseInt(matcher.group(2));
            int b = Integer.parseInt(matcher.group(3));
            color = new RGB(r, g, b);
        } else {
            System.out.println("Unrecognized color then use default one:" + colorStr);
        }

        return color;
    }
}
