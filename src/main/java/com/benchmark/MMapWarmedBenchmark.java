package com.benchmark;

import com.benchmark.util.FileUtil;
import com.benchmark.model.MappedFile;
import com.sun.jna.Platform;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 0, time = 5)
@Measurement(iterations = 1, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MMapWarmedBenchmark {

    @Param({"32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile mappedFile;
    byte[] payload;

    @Setup(Level.Iteration)
    public void setUp() {
        mappedFile = FileUtil.generateRandomMappedFile();
        if (Platform.isLinux()) {
            mappedFile.warmMappedFile();
        }

        payload = new byte[segmentSize];
        Arrays.fill(payload, (byte) 1);
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        mappedFile.destroy();
    }

    @Benchmark
    public void mmapCachedRead() {
        MappedByteBuffer mappedByteBuffer = mappedFile.getMappedByteBuffer();

        while (mappedByteBuffer.hasRemaining()) {
            mappedByteBuffer.get(payload);
        }
    }

    @Benchmark
    public void mmapCachedWrite() {
        MappedByteBuffer mappedByteBuffer = mappedFile.getMappedByteBuffer();

        int length = 0;
        while (length < mappedByteBuffer.capacity()) {
            length += segmentSize;
            mappedByteBuffer.put(payload);
        }
    }
}
