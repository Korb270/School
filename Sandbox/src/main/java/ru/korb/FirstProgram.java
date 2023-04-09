package ru.korb;

public class FirstProgram {
    public static void main(String[] args) {
//        hello("world");
//        hello("user");
//        hello("Igor");
//
//        double l = 7;
//        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));
//
//        double a = 3;
//        double b = 6;
//        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));

        Point p1 = new Point(3,5);
        Point p2 = new Point(3,6);
        System.out.println("Расстояние между точками равно " + distance(p1, p2));
    }

//    public static void hello(String somebody) {
//        System.out.println("Hello, " + somebody + "!");
//    }
//
//    public static double area(double len) {
//
//        return len * len;
//    }
//
//    public static double area(double a, double b) {
//        return a * b;
//    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
}