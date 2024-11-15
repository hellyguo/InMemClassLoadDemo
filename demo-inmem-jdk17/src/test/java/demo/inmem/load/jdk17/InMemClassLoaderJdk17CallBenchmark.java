package demo.inmem.load.jdk17;

import demo.base.SimTargetBytes;
import demo.base.inf.SimTarget;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/11/24 12:21 AM
 */

@State(Scope.Benchmark)
@Fork(value = 3, jvmArgsAppend = {
        "-Xmx2G",
        "-Xms2G"
})
@Threads(value = 1)
@Warmup(iterations = 8, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@SuppressWarnings("unchecked")
public class InMemClassLoaderJdk17CallBenchmark {

    private static final SimTarget TARGET1;
    private static final SimTarget TARGET2;

    static {
        try {
            Class<? extends SimTarget> targetClass1 =
                    (Class<? extends SimTarget>) new InMemClassLoaderImpl().loadClass("demo.base.impl.SimTargetImpl",
                                                                                      SimTargetBytes.SIM_TARGET_BYTES);
            Class<? extends SimTarget> targetClass2 =
                    (Class<? extends SimTarget>) new InMemClassLoaderHiddenImpl().loadClass(
                            "demo.base.impl.SimTargetImpl",
                            SimTargetBytes.SIM_TARGET_BYTES);
            TARGET1 = targetClass1.getDeclaredConstructor().newInstance();
            TARGET2 = targetClass2.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void testTarget1() {
        callTarget(TARGET1);
    }

    @Benchmark
    public void testTarget2() {
        callTarget(TARGET2);
    }

    private void callTarget(SimTarget target) {
        target.outputData("hello");
    }

}
