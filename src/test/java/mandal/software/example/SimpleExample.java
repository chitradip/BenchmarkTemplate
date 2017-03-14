package mandal.software.example;

import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by chitradip on 3/13/17.
 */
@State(Scope.Benchmark)
public class SimpleExample {


    public String a;
    public String b;


    @Setup(Level.Iteration)
    public void doSetup() {
        a = UUID.randomUUID().toString();
        b = UUID.randomUUID().toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10, timeUnit = TimeUnit.MICROSECONDS)
    public String concatenateStringsBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Some constant ")
                .append(a)
                .append(" Other Constant ")
                .append(b);

        return stringBuilder.toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10, timeUnit = TimeUnit.MICROSECONDS)
    public String concatenateStringsAdd() {
        return a + " Some constant " + b + " Other constant";
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10, timeUnit = TimeUnit.MICROSECONDS)
    public String concatenateStringsFormat() {
        return String.format("%s Some Constant %s Other Constant", a, b);
    }

}
