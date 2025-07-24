package com.stephen.assignment;

import org.junit.Assert;
import org.junit.Test;

public class VerticalLineTest {

    @Test
    public void isEligibleToMerge() {
        VerticalLine subject = new VerticalLine(68, 43, 170);
        VerticalLine other = new VerticalLine(67, 48, 167);

        int pixelRoamingRange =5;
        Assert.assertTrue(subject.isEligibleToMerge(other, pixelRoamingRange));
    }
}