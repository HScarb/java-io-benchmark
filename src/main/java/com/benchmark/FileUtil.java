package com.benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
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

    public static MappedByteBuffer mapFileChannel(FileChannel fileChannel) throws IOException {
        return fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
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

    public static MappedFile generateRandomMappedFile() {
        try {
            return new MappedFile(UUID.randomUUID().toString(), FILE_SIZE);
        } catch (IOException e) {
            System.out.println("Failed to generate random mapped file");
        }
        return null;
    }

    public static void cleanMmapFile(ByteBuffer buffer) {
        if (buffer != null && buffer.isDirect() && buffer.capacity() != 0) {
            invoke(invoke(viewed(buffer), "cleaner"), "clean");
        }
    }

    private static Object invoke(final Object target, final String methodName, final Class<?>... args) {
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    Method method = method(target, methodName, args);
                    method.setAccessible(true);
                    return method.invoke(target);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    private static Method method(Object target, String methodName, Class<?>[] args)
        throws NoSuchMethodException {
        try {
            return target.getClass().getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            return target.getClass().getDeclaredMethod(methodName, args);
        }
    }

    private static ByteBuffer viewed(ByteBuffer buffer) {
        String methodName = "viewedBuffer";
        Method[] methods = buffer.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                methodName = "attachment";
                break;
            }
        }

        ByteBuffer viewedBuffer = (ByteBuffer) invoke(buffer, methodName);
        if (viewedBuffer == null)
            return buffer;
        else
            return viewed(viewedBuffer);
    }
}
