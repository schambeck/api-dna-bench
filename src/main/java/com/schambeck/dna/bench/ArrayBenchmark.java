package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

import static com.schambeck.dna.bench.ArrayBenchmark.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.results.format.ResultFormatType.CSV;

@Fork(value = FORK, warmups = WARMUPS)
@Warmup(iterations = 5, time = TIME)
@Measurement(iterations = ITERATIONS, time = TIME)
@BenchmarkMode(AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 10)
public class ArrayBenchmark {

    protected static final int FORK = 1;
    protected static final int WARMUPS = 1;
    protected static final int ITERATIONS = 1;
    protected static final int TIME = 1;

//    @Benchmark
//    public byte[] arraysToString(ArrayState state) {
//        return Arrays.toString(state.dna).getBytes(UTF_8);
//    }

//    @Benchmark
//    public byte[][] toBytes(ArrayState state) {
//        return HashUtil.toBytes(state.dna);
//    }

//    @Benchmark
//    public byte[] toBytesStream(ArrayState state) {
//        return HashUtil.toBytesStream(state.dna);
//    }

//    @Benchmark
//    public boolean equals(ArrayState state) {
//        return Arrays.equals(state.dna, state.dna2);
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ArrayBenchmark.class.getSimpleName())
                .forks(1)
                .warmupForks(1)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .mode(AverageTime)
                .timeUnit(MILLISECONDS)
                .result("jmh-results/array-results.csv")
                .resultFormat(CSV)
                .build();
        new Runner(opt).run();
    }

}
