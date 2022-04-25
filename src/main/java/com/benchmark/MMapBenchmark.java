package com.benchmark;

import com.benchmark.model.MappedFile;
import com.benchmark.util.FileUtil;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
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

import static com.benchmark.consts.FileConst.FLUSH_SIZE;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MMapBenchmark {

    @Param({"16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "65536", "1048576", "16777216", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile mappedFile;
    byte[] payload;

    private volatile int len;

    @Setup(Level.Iteration)
    public void setUp() {
        mappedFile = FileUtil.generateRandomMappedFile();

        payload = new byte[segmentSize];
        Arrays.fill(payload, (byte) 1);
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        mappedFile.destroy();
    }

    @Benchmark
    public void mmapRead() {
        MappedByteBuffer mappedByteBuffer = mappedFile.getMappedByteBuffer();

        while (mappedByteBuffer.hasRemaining()) {
            mappedByteBuffer.get(payload);
        }
    }

    @Benchmark
    public void mmapWrite() {
        MappedByteBuffer mappedByteBuffer = mappedFile.getMappedByteBuffer();

        int length = 0;
        while (length < mappedByteBuffer.capacity()) {
            length += payload.length;
            mappedByteBuffer.put(payload);
        }
    }

    @Benchmark
    public void asyncWrite() throws InterruptedException {
        MappedByteBuffer mappedByteBuffer = mappedFile.getMappedByteBuffer();

        len = 0;
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            do {
                if (len >= FLUSH_SIZE && len % FLUSH_SIZE == 0) {
                    mappedByteBuffer.force();
                }
            }
            while (latch.getCount() != 0);
        }).start();

        while (len < mappedByteBuffer.capacity()) {
            len += payload.length;
            mappedByteBuffer.put(payload);
        }
        latch.countDown();
    }
}
