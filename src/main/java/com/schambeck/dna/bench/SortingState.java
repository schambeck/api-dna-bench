package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;

@State(Scope.Benchmark)
public class SortingState {

    @Param({"100000003"})
    public int size;

    public int[] input;

    @Setup
    public void setUp() {
        createInput();
        Arrays.sort(input);
    }

    @TearDown
    public void tearDown() {
        input = null;
    }

    private void createInput() {
        input = new int[size];
        Random random = new Random();
        int max = 9;
        int min = 0;
        for (int i = 0; i < input.length; i++) {
            int num = random.nextInt(max - min) + min;
            input[i] = num;
        }
        input[size / 2] = 9;
    }

}
