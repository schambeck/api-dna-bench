package com.schambeck.dna.bench;

import com.schambeck.dna.search.MatcherSearch;
import com.schambeck.dna.search.NeighborhoodSearch;
import com.schambeck.dna.search.TextSearch;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.results.format.ResultFormatType.JSON;

public class TextSearchBenchmark {

    @Benchmark
    public boolean neighborhoodSearch(TextSearchState state) {
        TextSearch textSearch = new NeighborhoodSearch();
        return textSearch.contains(state.dna);
    }

    @Benchmark
    public boolean matcherSearch(TextSearchState state) {
        TextSearch textSearch = new MatcherSearch();
//        System.out.printf("Iterations: %,d%n", textSearch.getIterations());
        return textSearch.contains(state.dna);
    }

    public static void main(String[] args) throws RunnerException {
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
                .result("jmh-results/text-search-results.json")
                .resultFormat(JSON)
                .build();
        new Runner(opt).run();
    }

}
