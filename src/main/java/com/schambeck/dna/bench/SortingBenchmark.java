package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.results.format.ResultFormatType.CSV;

public class SortingBenchmark {

    @Benchmark
    public int arrays(SortingState state) {
        return Arrays.binarySearch(state.input, 9);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortingBenchmark.class.getSimpleName())
                .forks(1)
                .warmupForks(0)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .mode(AverageTime)
                .timeUnit(MICROSECONDS)
                .result("jmh-results/sorting-results.csv")
                .resultFormat(CSV)
                .build();
        new Runner(opt).run();
    }

}
