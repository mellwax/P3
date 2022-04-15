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
                for (int j = i; j > a && points[j-1].getX() > points[j].getX(); j--) {
                    swap(points, j-1, j);
                }
            }
        } else {
            for (int i = a + 1; i < b; i++) {
                for (int j = i; j > a && points[j-1].getY() > points[j].getY(); j--) {
                    swap(points, j-1, j);
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
            PointPair closestPair = new PointPair(points[a], points[a + 1], points[a].getDistance(points[a + 1]));

            for (int i = a; i < b; i++) {
                for (int j = a; j < b; j++) {
                    if (i != j) {
                        double distance = points[i].getDistance(points[j]);
                        if (distance < closestPair.getDistance()) {
                            closestPair.setPoint1(points[i]);
                            closestPair.setPoint2(points[j]);
                            closestPair.setDistance(distance);
                        }
                    }
                }
            }
            return closestPair;
        }
    }

    // Implementieren Sie hier Ihre Lösung für die Bestimmung des Pivotelements
    public double getPivotValue(Point[] points, int a, int b, String method, Random random) {

        return -1.0;
    }

    // Implementieren Sie hier Ihre Lösung für das Aufteilen des Arrays
    public int split(Point[] points, int a, int b, double pivot) {
        
        return -1;
    }


    // Implementieren Sie hier Ihre Lösung für die Kombination zweier Teilprobleme
    public PointPair combination(Point[] points, int a, int b, double delta, int t, double L) {
        
        return null;
    }

}
