package com.benchmark;

import com.benchmark.model.MappedFile;
import com.benchmark.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import static com.benchmark.util.FileUtil.FILE_SIZE;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FileStreamBenchmark {
    @Param({"32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "1048576", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile file;
    FileInputStream fileInputStream = null;
    FileOutputStream fileOutputStream = null;
    byte[] payload;

    @Setup(Level.Iteration)
    public void setup() throws IOException {
        file = FileUtil.generateRandomFile();

        payload = new byte[segmentSize];
        Arrays.fill(payload, (byte) 1);
    }

    @TearDown(Level.Iteration)
    public void tearDown() throws IOException {
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        fileInputStream = null;
        fileOutputStream = null;
        file.getRandomAccessFile().close();
        file.destroy();
    }

    @Benchmark
    public void read() throws IOException {
        fileInputStream = new FileInputStream(file.getFile());
        while (true) {
            int len = fileInputStream.read(payload);
            if (len == -1) {
                break;
            }
        }
    }

    @Benchmark
    public void write() throws IOException {
        fileOutputStream = new FileOutputStream(file.getFile());
        int length = 0;
        while (length < FILE_SIZE) {
            length += segmentSize;
            fileOutputStream.write(payload);
        }
    }
}
