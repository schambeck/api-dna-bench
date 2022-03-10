package com.schambeck.dna.search;

import com.schambeck.dna.bench.TextSearchBenchmark;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.text.DecimalFormat;
import java.util.Collection;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

public class TextSearchBenchmarkTest {

    private static final double MAX_DEVIATION = 0.1;
    private static final DecimalFormat df = new DecimalFormat("0.000");

    @Test
    public void runJmhBenchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TextSearchBenchmark.class.getSimpleName())
                .forks(1)
                .warmupForks(0)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .mode(AverageTime)
                .timeUnit(MILLISECONDS)
                .build();
        Collection<RunResult> runResults = new Runner(opt).run();
        assertFalse(runResults.isEmpty());
        for (RunResult runResult : runResults) {
            String dnaSize = runResult.getParams().getParam("dnaSize");
            String sequenceOrientation = runResult.getParams().getParam("sequenceOrientation");
            if ("1003".equals(dnaSize)) {
                if ("horizontalLastRowLastCol".equals(sequenceOrientation)) {
                    assertDeviationWithin(runResult, 2.9);
                } else if ("verticalLastRowLastCol".equals(sequenceOrientation)) {
                    assertDeviationWithin(runResult, 7.3);
                } else if ("diagonalRightLastRowLastCol".equals(sequenceOrientation)) {
                    assertDeviationWithin(runResult, 6.6);
                } else if ("diagonalLeftLastRowLastCol".equals(sequenceOrientation)) {
                    assertDeviationWithin(runResult, 6);
                }
            }
        }
    }

    private static void assertDeviationWithin(RunResult result, double referenceScore) {
        double score = result.getPrimaryResult().getScore();
        double deviation = Math.abs(score / referenceScore - 1);
        String deviationString = df.format(deviation * 100) + "%";
        String maxDeviationString = df.format(MAX_DEVIATION * 100) + "%";
        String errorMessage = format("Deviation %s exceeds maximum allowed deviation %s", deviationString, maxDeviationString);
        assertTrue(deviation < MAX_DEVIATION, errorMessage);
    }

}
