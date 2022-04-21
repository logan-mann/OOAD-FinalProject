package com.naiflogan.finalproject.client.shapes;

public class ColorConstants {

    public static String RED = "0xFF0000";
    public static String ORANGE = "0xFF8000";
    public static String YELLOW = "0xFFFF00";
    public static String GREEN = "0x00FF00";
    public static String BLUE = "0x0000FF";
    public static String PURPLE = "0x7F00FF";

    public enum Colors {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        PURPLE;

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
