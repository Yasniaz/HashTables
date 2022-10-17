package com.company;

public class Main {

    public static void main(String[] args) {

        Zip node = new Zip("com/company/postnummer.csv");

        Benchmark.benchmark(10000);
    }
}
