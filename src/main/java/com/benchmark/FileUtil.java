package com.benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.UUID;

public class FileUtil {
    public static final int FILE_SIZE = 1024 * 1024 * 1024;

    static File getRandomFile() {
        String fileName = UUID.randomUUID().toString();

        final File file = new File(fileName);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                file.delete();
            }
        }));
        return file;
    }

    static RandomAccessFile getRandomAccessFile() throws IOException {
        File file = FileUtil.getAlreadyFilledFile();
        RandomAccessFile ra = new RandomAccessFile(file, "r");
        return ra;
    }

    static FileChannel getFileChannel() throws IOException {
        File file = FileUtil.getRandomFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.setLength(FILE_SIZE);
        return raf.getChannel();
    }

    static MappedByteBuffer getMappedByteBuffer() throws IOException {
        File file = FileUtil.getRandomFile();
        MappedByteBuffer mb = new RandomAccessFile(file, "rw").getChannel().
            map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
        return mb;
    }

    static File getAlreadyFilledFile() throws IOException {
        File file = getRandomFile();
        FileOutputStream fo = new FileOutputStream(file);
        byte[] arr = new byte[FILE_SIZE];
        Arrays.fill(arr, (byte) 1);
        fo.write(arr);
        return file;
    }

}
