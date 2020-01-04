# Performance

This part focuses on the simulation of the actual game. The technical part will be discussed in the [performance](performance.md) document.

## Technical Set-up

Since this games relies on setting the seed per Session, all operations within the session have to be executed sequentially in order to guarantee the same results. However, the implementation options to achieve this vary:

1. `iterative`: Providing an all-iterative approach.
2. `streams`: Providing a parallel approach with Java Streams

This set up leaves room for other implementations as well.

## Measurement

Different options will be evaluated using [jmh](https://openjdk.java.net/projects/code-tools/jmh/) directly integrated into this project.

## Results

No results yet.

## Verdict
