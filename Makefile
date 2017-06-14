benchmark_output=result.txt

.SUFFIXES:

.PHONY: all
all: run-benchmark

.PHONY: run-benchmark
run-benchmark: $(benchmark_output)

$(benchmark_output): target/benchmarks.jar
	java -jar $< -rf text -rff $@

target/benchmarks.jar: src/main/java/org/example/LoopDirection.java
	mvn package

.PHONY: clean
clean:
	mvn clean
	$(RM) $(benchmark_output)
