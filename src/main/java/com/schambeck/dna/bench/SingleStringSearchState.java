package com.schambeck.dna.bench;

//import net.amygdalum.stringsearchalgorithms.patternsearch.chars.BPGlushkov;
//import net.amygdalum.stringsearchalgorithms.search.chars.*;
import org.openjdk.jmh.annotations.*;

import static com.schambeck.dna.util.RandomStringUtil.randomDnaHorizontalSequenceRow;

@State(Scope.Benchmark)
public class SingleStringSearchState {

    private static final int SEQUENCE_COUNT = 4;
    public static final String PATTERN = "GGGG";
    public String input;
//    public StringSearchAlgorithm horspool;
//    public StringSearchAlgorithm shiftOr;
//    public StringSearchAlgorithm bndm;
//    public StringSearchAlgorithm bom;
//    public StringSearchAlgorithm kmp;
//    public StringSearchAlgorithm shiftAnd;
//    public StringSearchAlgorithm sunday;
//    public StringSearchAlgorithm bpGlushkov;

    @Param({"100000000"})
    public int size;

    @Setup
    public void setUp() {
        input = randomDnaHorizontalSequenceRow(size, size - SEQUENCE_COUNT);
//        horspool = new Horspool(PATTERN);
//        shiftOr = new ShiftOr(PATTERN);
//        bndm = new BNDM(PATTERN);
//        bom = new BOM(PATTERN);
//        kmp = new KnuthMorrisPratt(PATTERN);
//        shiftAnd = new ShiftAnd(PATTERN);
//        sunday = new Sunday(PATTERN);
//        bpGlushkov = new BPGlushkov(PATTERN);
    }

    @TearDown
    public void tearDown() {
        input = null;
//        horspool = null;
//        shiftOr = null;
//        bndm = null;
//        bom = null;
//        kmp = null;
//        shiftAnd = null;
//        sunday = null;
//        bpGlushkov = null;
    }

}
