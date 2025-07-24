package com.stephen.assignment;

/**
 * Defines the interface to count vertical lines; It makes the image format agnostic;
 * The background color and line color can be specified.
 */
public interface Image {
    int countVerticalLines(int lineMinLength, int pixelRoamingRange);
    void setBackgroundColor(RGB bgColor);
    void setSpecifiedLineColor(RGB specifiedLineColor);
}

