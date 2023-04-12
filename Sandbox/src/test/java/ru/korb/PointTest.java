package ru.korb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test

    public void testDistance () {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 6);
        Assert.assertEquals(p1.distance(p2), 1.0);

        Point p3 = new Point(7, 9);
        Point p4 = new Point(7, 6);
        Assert.assertEquals(p3.distance(p4), 3.0);

        Assert.assertEquals(p1.distance(p4), 4.123105625617661);
        Assert.assertEquals(p2.distance(p3), 5);
        Assert.assertEquals(p2.distance(p2), 0);
    }
}
