package ru.korb;

public class Point {

    public double x;
    public double y;
//    public double x2;
//    public double y2;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double distance(Point p2) {
        return Math.sqrt(Math.pow((p2.x - this.x), 2) + Math.pow((p2.y - this.y), 2));
    }

//    public double x1;
//    public double x2;
//    public double y1;
//    public double y2;

//    public Point p1 (double x1,double x2){
//
//
//    }
//
//    public Point p2 (double y1, double y2){
//
//
//    }
}
