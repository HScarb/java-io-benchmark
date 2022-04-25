package com.benchmark;

import com.benchmark.util.FileUtil;
import com.benchmark.model.MappedFile;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

import static com.benchmark.consts.FileConst.FILE_SIZE;
import static com.benchmark.consts.FileConst.FLUSH_SIZE;

@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FileChannelBenchmark {

    @Param({"16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "65536", "1048576", "16777216", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile mappedFile;
    byte[] payload;
    ByteBuffer byteBuffer;

    private int len = 0;

    @Setup(Level.Iteration)
    public void setup() {
        mappedFile = FileUtil.generateRandomMappedFile();

        payload = new byte[segmentSize];
        Arrays.fill(payload, (byte) 1);
        byteBuffer = ByteBuffer.wrap(payload);
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        mappedFile.destroy();
    }

    @Benchmark
    public void fileChannelRead() throws IOException {
        FileChannel fileChannel = mappedFile.getFileChannel();

        ByteBuffer bytebuffer = ByteBuffer.allocate(segmentSize);
        while (true) {
            int len = fileChannel.read(bytebuffer);
            bytebuffer.clear();
            if (len == -1) {
                break;
            }
        }
    }

    @Benchmark
    public void fileChannelWrite() throws IOException {
        FileChannel fileChannel = mappedFile.getFileChannel();

        int length = 0;
        while (length < FILE_SIZE) {
            length += segmentSize;
            fileChannel.write(byteBuffer);
        }
    }

    @Benchmark
    public void asyncWrite() throws IOException, InterruptedException {
        FileChannel fileChannel = mappedFile.getFileChannel();

        len = 0;
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            do {
                if (len >= FLUSH_SIZE && len % FLUSH_SIZE == 0) {
                    try {
                        fileChannel.force(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (latch.getCount() != 0);
        }).start();

        while (len < FILE_SIZE) {
            len += segmentSize;
            fileChannel.write(byteBuffer);
        }
        latch.countDown();
        latch.await();
    }
}
