package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class Assignment4 {
    // number of different sizes will be tested
    private final static int nTest = 20;
    private final static int nRepeat = 100;
    private static int[] ns;

    private static int nConnection; // total connection established
    private static int nCall; // total call of random distinct pairs generated

    public static void main(String[] args) {
        ns = new int[nTest];
        for (int i = 0; i < nTest; i++) {
            //initialized the test
            ns[i] = (int)Math.pow(2,i+1);
            nCall = 0;
            nConnection = 0;
            // do test
            for (int j = 0; j<nRepeat; j++) {
                count(ns[i]);
            }
            // find mean
            nCall = nCall/nRepeat;
            nConnection = nConnection/nRepeat;
            System.out.println("test: " + i + " nSite: " + ns[i] + " nCall: " + nCall + " nConnection: " + nConnection);
        }


    }
    public static int count(int n) {
        Random random = new Random();
        UF uf = new UF_HWQUPC(n,true);
        while (uf.components()>1) {
            nCall++;
            //generate a pair of distinct int x and y
            int x = random.nextInt(n);
            int y;
            do {
                y = random.nextInt(n);
            } while (x==y);
            //
            if (!uf.isConnected(x,y)) {
                uf.union(x, y);
                nConnection++;
            }
        }
        //System.out.println("nSite: " + n + " nCall: " + nCall + " nConnection: " + nConnection);
        return nConnection;
    }
}
