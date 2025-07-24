package com.stephen.assignment;

/**
 * A windows console app with only the following behaviors:
 * 1. Console app takes exactly 1 argument.
 * a. Output to console a message when invalid number of arguments are used.
 * b. Application must not crash. Any exception must be output to console.
 * 2. Accept a string representing the absolute path of the test image as the input.
 * a. Example C:\img_1.jpg
 * 3. Output a number representing the number of vertical lines.
 */
public class VerticalLineScanner {
    private static final String CONFIG_FILE = "/config.properties";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Missing image file path!");
            printUsage();
            System.exit(1);
        }

        //Load config file
        Config config = new Config();
        try {
            config.loadConfig(CONFIG_FILE);
        } catch (Exception e) {
            String errMsg = String.format("Failed to load/parse config file %s and fail back to default ones: %s", CONFIG_FILE, e.getMessage());
            //It should use logging library to do the logging.
            //To make it simple, print it on the console.
            System.out.println(errMsg);
            e.printStackTrace();
        }

        //load image file
        String filePath = args[0];
        JPGImage img = new JPGImage();
        try {
            img.load(filePath);
        } catch (Exception e) {
            String errMsg = "Failed to load the image file: " + filePath;
            //It should use logging library to do the logging.
            //To make it simple, print it on the console.
            e.printStackTrace();
            System.exit(2);
        }

        img.setBackgroundColor(config.getBackGroundColor());
        img.setSpecifiedLineColor(config.getLineColor());
        int count = img.countVerticalLines(config.getLineMinLength(), config.getPixelRoamingRange());
        System.out.println(count);
    }

    private static void printUsage() {
        String usage = "Usage: \n java -jar image-processor-<xxx>.jar <full_image_file_path>";
        System.out.println(usage);
    }
}
