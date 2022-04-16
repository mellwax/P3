package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;
import java.util.Random;

public class StudentSolutionImplementation implements StudentSolution {
    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Kristijan", // Vorname
                "Todorovic", // Nachname
                "11806442" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für Insertion Sort
    public void insertionSort(Point[] points, int a, int b, boolean sortX) {
        if (sortX) {
            for (int i = a + 1; i < b; i++) {
                for (int j = i; j > a && points[j - 1].getX() > points[j].getX(); j--) {
                    swap(points, j-1, j);
                }
            }
        } else {
            for (int i = a + 1; i < b; i++) {
                for (int j = i; j > a && points[j - 1].getY() > points[j].getY(); j--) {
                    swap(points, j - 1, j);
                }
            }
        }
    }

    private void swap(Point[] points, int a, int b) {
        Point temp = points[a];
        points[a] = points[b];
        points[b] = temp;
    }

    // Implementieren Sie hier Ihre Lösung für Brute Force
    public PointPair bruteForce(Point[] points, int a, int b) {
        if (b - a <= 1) {
            return null;
        } else {
            PointPair closestPair = new PointPair(points[a], points[a + 1], points[a].getDistance(points[a + 1])); // default closest pair

            for (int i = a; i < b; i++) { // compare every point
                for (int j = a; j < b; j++) { // with every other point
                    if (i != j) {
                        double distance = points[i].getDistance(points[j]); // calculate the distance
                        if (distance < closestPair.getDistance()) { // and save the closest pair
                            closestPair.setPoint1(points[i]);
                            closestPair.setPoint2(points[j]);
                            closestPair.setDistance(distance);
                        }
                    }
                }
            }
            return closestPair; // finally, return the closest pair
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Bestimmung des Pivotelements
    public double getPivotValue(Point[] points, int a, int b, String method, Random random) {
        if (b - a <= 1) {
            return -1.0;
        }
        switch (method) {
            case "Random":
                return points[random.nextInt(b - a) + a].getX(); // return a random x within range
            case "First":
                return points[a].getX(); // return first x
            case "Median Of Three":
                double top = points[a].getX();
                double mid = points[(b - a - 1) / 2 + a].getX();
                double bot = points[b - 1].getX();

                double[] arr = {top, mid, bot}; // create array
                for (int i = 1; i < arr.length; i++) { // sort the elements
                    for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                        double temp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = temp;
                    }
                }

                return arr[1]; // return the middle one
            default:
                return -1.0;
        }
    }

    // Implementieren Sie hier Ihre Lösung für das Aufteilen des Arrays
    public int split(Point[] points, int a, int b, double pivot) {
        if (b - a <= 1) {
            return -1;
        } else {

            double closest = Double.MAX_VALUE;
            int t = a;
            // find a t closest to the pivot value
            for (int i = a; i < b; i++) {
                double difference = Math.abs(points[i].getX() - pivot);
                if (difference < closest) {
                    closest = difference;
                    t = i;
                }
            }
            // rearrange array so all elements < pivot have an index < t
            // and elements > pivot have an index > t
            for (int i = a; i < b; i++) {
                if ((points[i].getX() > points[t].getX() && i < t) || (points[i].getX() < points[t].getX() && i > t)) {
                    swap(points, i, t);
                    t = i;
                }
                if (i == b - 1 && !isInOrder(points, a, t, b, pivot)) {
                    i = a;
                }
            }
            if (t == a) { // t cannot be a or b
                t++;
            }

            return t;
        }
    }

    private boolean isInOrder(Point[] points, int a, int t, int b, double pivot) {
        for (int i = a; i < t; i++) {
            if (points[i].getX() > pivot) {
                return false;
            }
        }
        for (int i = t; i < b; i++) {
            if (points[i].getX() < pivot) {
                return false;
            }
        }
        return true;
    }

    // Implementieren Sie hier Ihre Lösung für die Kombination zweier Teilprobleme
    public PointPair combination(Point[] points, int a, int b, double delta, int t, double L) {
        int newA = a;
        int newB = b;
        for (int i = a; i < b; i++) {
            if (L - points[i].getX() <= delta) {
                newA = i;
                break;
            }
        }
        for (int i = b - 1; i >= a; i--) {
            if (points[i].getX() - L <= delta) {
                newB = i + 1;
                break;
            }
        }
        insertionSort(points, newA, newB, false);

        return bruteForce(points, newA, newB);
    }
}
