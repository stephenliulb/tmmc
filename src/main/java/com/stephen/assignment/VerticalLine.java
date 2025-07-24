package com.stephen.assignment;

public class VerticalLine extends Line {
    public VerticalLine(int x1, int y1, int y2) {
        super(x1, y1, x1, y2);
    }

    public boolean isEligibleToMerge(VerticalLine other, int pixelRoamingRange) {
        if(other == null) return false;
        return (y1 == other.y1 || Math.abs(y1 - other.y1) <= pixelRoamingRange)
                && (y2 == other.y2 || Math.abs(y2 - other.y2) <= pixelRoamingRange)
                && (Math.abs(x1 - other.x1) == 1 | Math.abs(x1 - other.x1) <= pixelRoamingRange);
    }

    public static VerticalLine copy(VerticalLine vl){
        return new VerticalLine(vl.x1, vl.y1, vl.y2);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)(%d,%d)", x1, y1, x2, y2);
    }

}
