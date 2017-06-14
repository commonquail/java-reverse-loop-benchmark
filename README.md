# JMH benchmark: reverse vs. forward loops

Do reverse loops run faster than forward loops in Java?

## Conclusion

Stay tuned.

## Premise

[A blog post][blog] inspected the byte code of simple "forward" and "reverse"
loops, found the latter to produce one less instruction, and concluded it must
be faster. Unfortunately the post did not include a benchmark to support that
conclusion.

A "forward" loop is a typical loop:

```java
for (int i = 0; i < 42; i++) {
    thing(i);
}
```

A "reverse" loop is the semantically equivalent version that traverses
the opposite direction:

```java
for (int i = 42; i >= 0; i--) {
    thing(i);
}
```

This transformation requires the operation to be associative, which certainly
is no guarantee.

## Benchmark

For each forward and reverse loop there is a benchmark that caches the loop
termination condition ("Expression" in [the specification][spec]) and one that
doesn't, on the grounds that this saves another instruction. These are the
`cached` respectively `uncached` benchmarks.

There are two sets of these four benchmarks, one that uses only the loop
iterator and one that addresses the corresponding array element, on the grounds
that that's why you'd have an array to begin with. The latter is the `lookup`
benchmark set.

## Appendix: build and run

0. Install JDK 8 and Maven.
0. Run `make` to build and execute the benchmark, writing the result to standard out as well as `result.txt`.

If you don't have `make`, build and run manually with

```sh
$ mvn package
$ java -jar target/benchmarks.jar
```

[blog]: https://medium.com/@TravCav/why-reverse-loops-are-faster-a09d65473006
[spec]: https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.14
