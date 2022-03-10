package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.schambeck.dna.bench.ForLoopBenchmark.*;

@Fork(value = FORK, warmups = WARMUPS)
@Warmup(iterations = 5, time = TIME)
@Measurement(iterations = ITERATIONS, time = TIME)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 10)
public class ForLoopBenchmark {

    protected static final int FORK = 1;
    protected static final int WARMUPS = 1;
    protected static final int ITERATIONS = 1;
    protected static final int TIME = 1;

    @Benchmark
    public int forLoop(ForLoopState state, Blackhole blackhole) {
        int rows = state.dna.length;
        for (String text : state.dna) {
            if (text.length() != rows) {
                blackhole.consume(text);
            }
        }
        return rows;
    }

    @Benchmark
    public boolean anyMatch(ForLoopState state) {
        int rows = state.dna.length;
        return Arrays.stream(state.dna).anyMatch(p -> p.length() != rows);
    }

    @Benchmark
    public Optional<String> findFirst(ForLoopState state) {
        int rows = state.dna.length;
        return Arrays.stream(state.dna)
                .filter(p -> p.length() != rows)
                .findFirst();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ForLoopBenchmark.class.getSimpleName())
                .forks(1)
                .warmupForks(1)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .build();
        new Runner(opt).run();
    }

}
