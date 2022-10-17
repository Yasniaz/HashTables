package com.company;

import java.util.Random;

public class Benchmark {

    public static void benchmark(int iteration) {
        int[] nValues = new int[]{1000, 250, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000};
        Random rd = new Random();

        for(int i = 0; i < nValues.length; ++i) {
            long t = 0L;
            int value = Math.abs(rd.nextInt(nValues[i] - 1));

            for(int j = 0; j < iteration; ++j) {

                Zip node = new Zip("com/company/postnummer.csv");
                long t0 = System.nanoTime();

                node.lookUp("111 15");


                long t1 = System.nanoTime();
                t += t1 - t0;
            }

            System.out.println(nValues[i] + "\t" + t / (long)iteration);
        }

    }
}

