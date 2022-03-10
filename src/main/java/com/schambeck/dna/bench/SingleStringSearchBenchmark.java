package com.schambeck.dna.bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import static com.schambeck.dna.bench.SingleStringSearchState.PATTERN;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.results.format.ResultFormatType.CSV;

public class SingleStringSearchBenchmark {

    @Benchmark
    public boolean indexOf(SingleStringSearchState state) {
        return state.input.contains(PATTERN);
    }

//    @Benchmark
//    public boolean horspool(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.horspool);
//    }

//    @Benchmark
//    public boolean shiftOr(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.shiftOr);
//    }

//    @Benchmark
//    public boolean bndm(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.bndm);
//    }

//    @Benchmark
//    public boolean bom(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.bom);
//    }

//    @Benchmark
//    public boolean kmp(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.kmp);
//    }

//    @Benchmark
//    public boolean shiftAnd(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.shiftAnd);
//    }

//    @Benchmark
//    public boolean sunday(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.sunday);
//    }

//    @Benchmark
//    public boolean bpGlushkov(SingleStringSearchState state) {
//        return StringSearch.contains(state.input, state.bpGlushkov);
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SingleStringSearchBenchmark.class.getSimpleName())
                .forks(1)
                .warmupForks(0)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .mode(AverageTime)
                .timeUnit(MILLISECONDS)
                .result("jmh-results/single-string-search-results.csv")
                .resultFormat(CSV)
                .build();
        new Runner(opt).run();
    }

}
