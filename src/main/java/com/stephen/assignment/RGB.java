package com.stephen.assignment;

public class RGB {
    private final int red;
    private final int green;
    private final int blue;

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public boolean isSameColor(int pixelColor) {
        int pRed = (pixelColor >> 16) & 0xff;
        int pGreen = (pixelColor >> 8) & 0xff;
        int pBlue = pixelColor & 0xff;
        return (red == pRed) && (green == pGreen) && (blue == pBlue);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RGB)) return false;
        RGB other = (RGB) o;
        return (red == other.red) && (green == other.green) && (blue == other.blue);
    }

    @Override
    public int hashCode() {
        return ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
    }
}
