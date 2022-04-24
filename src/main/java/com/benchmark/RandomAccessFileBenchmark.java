package com.benchmark;

import com.benchmark.model.MappedFile;
import com.benchmark.util.FileUtil;
import java.io.IOException;
import java.io.RandomAccessFile;
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

import static com.benchmark.consts.FileConst.FILE_SIZE;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RandomAccessFileBenchmark {
    @Param({"32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "1048576", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile mappedFile;
    public byte[] payload;

    @Setup(Level.Iteration)
    public void setup() {
        mappedFile = FileUtil.generateRandomMappedFile();
        payload = new byte[segmentSize];
        Arrays.fill(payload, (byte) 1);
    }

    @TearDown(Level.Iteration)
    public void teardown() {
        mappedFile.destroy();
    }

    @Benchmark
    public void read() throws IOException {
        RandomAccessFile raf = mappedFile.getRandomAccessFile();

        while (true) {
            int len = raf.read(payload);
            if (len == -1) {
                break;
            }
        }
    }

    @Benchmark
    public void write() throws IOException {
        RandomAccessFile raf = mappedFile.getRandomAccessFile();

        int length = 0;
        while (length < FILE_SIZE) {
            length += segmentSize;
            raf.write(payload);
        }
    }
}
