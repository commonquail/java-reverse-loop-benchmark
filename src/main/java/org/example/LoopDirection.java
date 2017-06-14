package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = {"-Xmx1g", "-Xms1g"})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LoopDirection {

    @Param({
        "0",
        "1",
        "2",
        "5",
        "10",
        "100",
        "1000",
        "10000",
        "100000",
        "1000000"})
    int size;

    int[] items;

    @Setup
    public void setup() {
        items = new int[size];
    }

    @Benchmark
    public int forward_uncached_bound() {
        int sum = 0;
        for (int i = 0; i < items.length; i++) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public int forward_cached_bound() {
        int sum = 0;
        final int len = items.length;
        for (int i = 0; i < len; i++) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public int reverse_uncached_bound() {
        int sum = 0;
        for (int i = items.length; i >= 0; i--) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public int reverse_cached_bound() {
        int sum = 0;
        final int len = items.length;
        for (int i = len; i >= 0; i--) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public int forward_uncached_bound_lookup() {
        int sum = 0;
        for (int i = 0; i < items.length; i++) {
            sum += items[i];
        }
        return sum;
    }

    @Benchmark
    public int forward_cached_bound_lookup() {
        int sum = 0;
        final int len = items.length;
        for (int i = 0; i < len; i++) {
            sum += items[i];
        }
        return sum;
    }

    @Benchmark
    public int reverse_uncached_bound_lookup() {
        int sum = 0;
        for (int i = items.length - 1; i >= 0; i--) {
            sum += items[i];
        }
        return sum;
    }

    @Benchmark
    public int reverse_cached_bound_lookup() {
        int sum = 0;
        final int len = items.length;
        for (int i = len - 1; i >= 0; i--) {
            sum += items[i];
        }
        return sum;
    }
}
