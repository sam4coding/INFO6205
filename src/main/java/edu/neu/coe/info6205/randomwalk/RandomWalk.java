/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.io.IOException;
import java.util.Random;
import java.lang.Math;
import java.io.FileWriter;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // FIXME do move by replacing the following code
        // throw new RuntimeException("Not implemented");
        // update the current position by adding the increment
        x += dx;
        y += dy;
        // END 
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // FIXME
        // Do randomMove m times
        for (int i = 0; i < m; i++) {
            randomMove();
        }
        // END 
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // FIXME by replacing the following code
        // formula to find Euclid distance of 2 points, Pythagorean theorem
         return Math.sqrt(x*x+y*y);
        // END 
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);

        /******* do a series of random walk and record the result in a csv file *******/
        // set up a series of steps, increasing exponentially
        // open a file for the record
        try {
            FileWriter fp = new FileWriter("RandomWalkResult.csv");
            fp.write("test,steps;displacement\n");
            for (int i = 0; i < m; i++) {
                double meanDistance = 0;//
                double tests = Math.pow(2,i); // every test is twice as long as its previous test
                meanDistance = randomWalkMulti((int)tests, n);// calculate the meanDistance of 30 tests for each m
                String lineToPrint = String.format("step %d: %.2f over %d experiments of %d steps \n", i, meanDistance, n, (int)tests);
                fp.write(i+","+tests+","+meanDistance+"\n"); // write to a file
                System.out.print(lineToPrint);
            }
            fp.close();
        } catch (IOException e) {
            System.out.println("File operation error:");
            e.printStackTrace();
        }

        // close the file


    }

}
