package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayState {

    @Param({"1003", "10003"})
    public int dnaSize;

    public String[] dna;
    public String[] dna2;

//    public byte[] bytes;

    @Setup
    public void setUp() {
//        dna = randomDnaNoSequence(dnaSize);
//        dna2 = randomDnaNoSequence(dnaSize);
//        bytes = Arrays.toString(dna).getBytes(UTF_8);
    }

    @TearDown
    public void tearDown() {
        dna = null;
//        bytes = null;
    }

}
