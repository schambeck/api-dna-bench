package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;

import static com.schambeck.dna.util.RandomStringUtil.*;

@State(Scope.Benchmark)
public class TextSearchState {

    private static final int SEQUENCE_COUNT = 4;

    @Param({"1003"})
    public int dnaSize;

    @Param({/*"middle", */"end"})
    public String sequencePlace;

    @Param({
            /*"horizontalFirstRowLastCol", "horizontalLastRowFirstCol", */"horizontalLastRowLastCol",
            /*"verticalFirstRowLastCol", "verticalLastRowFirstCol", */"verticalLastRowLastCol",
            "diagonalRightLastRowLastCol",
            "diagonalLeftLastRowLastCol"
    })
    public String sequenceOrientation;

    public String[] dna;

    @Setup
    public void setUp() {
        horizontalSequence();
        verticalSequence();
        diagonalRight();
        diagonalLeft();
    }

    @TearDown
    public void tearDown() {
        dna = null;
    }

    private void horizontalSequence() {
        int row;
        int col;
        if ("middle".equals(sequencePlace)) {
            if ("horizontalFirstRowLastCol".equals(sequenceOrientation)
                    || "horizontalLastRowFirstCol".equals(sequenceOrientation)
                    || "horizontalLastRowLastCol".equals(sequenceOrientation)) {
                row = dnaSize / 2;
                col = dnaSize / 2;
            } else {
                return;
            }
        } else if ("end".equals(sequencePlace)) {
            if ("horizontalFirstRowLastCol".equals(sequenceOrientation)) {
                row = 0;
                col = dnaSize - SEQUENCE_COUNT;
            } else if ("horizontalLastRowFirstCol".equals(sequenceOrientation)) {
                row = dnaSize - 1;
                col = 0;
            } else if ("horizontalLastRowLastCol".equals(sequenceOrientation)) {
                row = dnaSize - 1;
                col = dnaSize - SEQUENCE_COUNT;
            } else {
                return;
            }
        } else {
            return;
        }
        dna = randomDnaHorizontalSequence(dnaSize, row, col);
    }

    private void verticalSequence() {
        int row;
        int col;
        if ("middle".equals(sequencePlace)) {
            if ("verticalFirstRowLastCol".equals(sequenceOrientation)
                    || "verticalLastRowFirstCol".equals(sequenceOrientation)
                    || "verticalLastRowLastCol".equals(sequenceOrientation)) {
                row = dnaSize / 2;
                col = dnaSize / 2;
            } else {
                return;
            }
        } else if ("end".equals(sequencePlace)) {
            if ("verticalFirstRowLastCol".equals(sequenceOrientation)) {
                row = 0;
                col = dnaSize - 1;
            } else if ("verticalLastRowFirstCol".equals(sequenceOrientation)) {
                row = dnaSize - SEQUENCE_COUNT;
                col = 0;
            } else if ("verticalLastRowLastCol".equals(sequenceOrientation)) {
                row = dnaSize - SEQUENCE_COUNT;
                col = dnaSize - 1;
            } else {
                return;
            }
        } else {
            return;
        }
        dna = randomDnaVerticalSequence(dnaSize, row, col);
    }

    private void diagonalRight() {
        if ("diagonalRightLastRowLastCol".equals(sequenceOrientation)) {
            int row;
            int col;
            if ("middle".equals(sequencePlace)) {
                row = dnaSize / 2;
                col = dnaSize / 2;
            } else if ("end".equals(sequencePlace)) {
                row = dnaSize - SEQUENCE_COUNT;
                col = dnaSize - SEQUENCE_COUNT;
            } else {
                return;
            }
            dna = randomDnaDiagonalSequenceRight(dnaSize, row, col);
        }
    }

    private void diagonalLeft() {
        if ("diagonalLeftLastRowLastCol".equals(sequenceOrientation)) {
            int row;
            int col;
            if ("middle".equals(sequencePlace)) {
                row = dnaSize / 2;
                col = dnaSize / 2;
            } else if ("end".equals(sequencePlace)) {
                row = dnaSize - SEQUENCE_COUNT;
                col = dnaSize - SEQUENCE_COUNT;
            } else {
                return;
            }
            dna = randomDnaDiagonalSequenceLeft(dnaSize, row, col);
        }
    }

}
