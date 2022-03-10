package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ForLoopState {

    @Param({"103", "1003", "10003"})
    public int dnaSize;

    public String[] dna;

    @Setup
    public void setUp() {
//        dna = randomDnaNoSequence(dnaSize);
    }

    @TearDown
    public void tearDown() {
        dna = null;
    }

}
