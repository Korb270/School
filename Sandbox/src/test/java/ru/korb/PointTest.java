package ru.korb;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    Point p1 = new Point(3,5);
    Point p2 = new Point(3, 6);
    Point p3 = new Point(3.6, 9.7);
    Point p4 = new Point(7.2, 4.6);
    Point p5 = new Point(-4, 8);
    @Test
    // Проверяем с целыми числами
    public void testDistance () {
        Assert.assertEquals(p1.distance(p2), 1.0);
    }
    @Test
    // Проверяем с дродными числами
    public void testDistanceFraction () {
        Assert.assertEquals(p3.distance(p4), 6.2425956140054435);
    }
    @Test
    //Проверяем если координаты точек совпали
    public void testDistanceIdentical () {
        Assert.assertEquals(p2.distance(p2), 0);
    }
    @Test
    //Проверяем отрицательные числа
    public void testDistanceNegNum (){
        Assert.assertEquals(p5.distance(p2), 7.280109889280518);
    }
}
