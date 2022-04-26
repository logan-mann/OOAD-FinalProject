package com.naiflogan.finalproject.client.shapes;

/**
 * Class containing constants for the various pen colors used in application
 */
public class ColorConstants {

    public static String RED = "0xFF0000";
    public static String ORANGE = "0xFF8000";
    public static String YELLOW = "0xFFFF00";
    public static String GREEN = "0x00FF00";
    public static String BLUE = "0x0000FF";
    public static String PURPLE = "0x7F00FF";

    //Enum for various colors, allows easy iteration through all color constants for creating buttons etc.
    public enum Colors {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        PURPLE;

        //Helper function to convert an enum value to appropriate hex string
        public static String getColorHexValue(Colors color) {
            switch (color) {
                case RED:
                    return ColorConstants.RED;
                case ORANGE:
                    return ColorConstants.ORANGE;
                case YELLOW:
                    return ColorConstants.YELLOW;
                case GREEN:
                    return ColorConstants.GREEN;
                case BLUE:
                    return ColorConstants.BLUE;
                case PURPLE:
                    return ColorConstants.PURPLE;
                default:
                    return ColorConstants.RED;
            }
        }
    }
    
}
