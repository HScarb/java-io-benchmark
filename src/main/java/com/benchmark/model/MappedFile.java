package com.benchmark.model;

import com.benchmark.util.CLibrary;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.nio.ch.DirectBuffer;

public class MappedFile {
    public static final int OS_PAGE_SIZE = 1024 * 4;

    private String fileName;
    private int fileSize;

    private File file;
    private FileChannel fileChannel;
    private RandomAccessFile randomAccessFile;
    private MappedByteBuffer mappedByteBuffer;

    public MappedFile() {
    }

    public MappedFile(String fileName, int fileSize, boolean mapped) throws IOException {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.file = new File(fileName);

        ensureDirOK(this.file.getParent());

        boolean ok = false;

        try {
            this.randomAccessFile = new RandomAccessFile(this.file, "rw");
            randomAccessFile.setLength(fileSize);
            this.fileChannel = randomAccessFile.getChannel();
            if (mapped) {
                this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
            }
            ok = true;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to create file " + this.fileName + e);
            throw e;
        } catch (IOException e) {
            System.out.println("Failed to map file " + this.fileName + e);
            throw e;
        } finally {
            if (!ok && this.fileChannel != null) {
                this.fileChannel.close();
            }
        }
    }

    public static void ensureDirOK(final String dirName) {
        if (dirName != null) {
            if (dirName.contains(File.separator)) {
                String[] dirs = dirName.trim().split(File.separator);
                for (String dir : dirs) {
                    createDirIfNotExist(dir);
                }
            } else {
                createDirIfNotExist(dirName);
            }
        }
    }

    public boolean destroy() {
        if (this.mappedByteBuffer != null) {
            clean(this.mappedByteBuffer);
        }
        try {
            this.fileChannel.close();
            return this.file.delete();
        } catch (Exception e) {
            System.out.println("Close file channel " + this.fileName + " failed. " + e);
        }
        return false;
    }

    private static void createDirIfNotExist(String dirName) {
        File f = new File(dirName);
        if (!f.exists()) {
            boolean result = f.mkdirs();
            System.out.println(dirName + " mkdir " + (result ? "OK" : "Failed"));
        }
    }

    public static void clean(final ByteBuffer buffer) {
        if (buffer == null || !buffer.isDirect() || buffer.capacity() == 0)
            return;
        invoke(invoke(viewed(buffer), "cleaner"), "clean");
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
        for (Method method : methods) {
            if (method.getName().equals("attachment")) {
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

    public void warmMappedFile() {
        // Flush page size when the disk in warming state
        int pages = 1024 / 4 * 16;

//        long beginTime = System.currentTimeMillis();
        ByteBuffer byteBuffer = this.mappedByteBuffer.slice();
        int flush = 0;
//        long time = System.currentTimeMillis();
        for (int i = 0, j = 0; i < this.fileSize; i += MappedFile.OS_PAGE_SIZE, j++) {
            byteBuffer.put(i, (byte) 0);
            // force flush
            if ((i / OS_PAGE_SIZE) - (flush / OS_PAGE_SIZE) >= pages) {
                flush = i;
                mappedByteBuffer.force();
            }

            // prevent gc
            if (j % 1000 == 0) {
//                System.out.printf("j=%s, costTime=%s%n", j, System.currentTimeMillis() - time);
//                time = System.currentTimeMillis();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted" + e);
                }
            }
        }

        // Force flush when prepare load finished
        mappedByteBuffer.force();

        this.mlock();
    }

    private void mlock() {
        final long beginTime = System.currentTimeMillis();
        final long address = ((DirectBuffer) (this.mappedByteBuffer)).address();
        Pointer pointer = new Pointer(address);
        {
            int ret = CLibrary.INSTANCE.mlock(pointer, new NativeLong(this.fileSize));
            System.out.printf("mlock %s %s %s ret = %d time consuming = %d%n", address, this.fileName, this.fileSize, ret, System.currentTimeMillis() - beginTime);
        }

        {
            int ret = CLibrary.INSTANCE.madvise(pointer, new NativeLong(this.fileSize), CLibrary.MADV_WILLNEED);
            System.out.printf("madvise %s %s %s ret = %d time consuming = %d%n", address, this.fileName, this.fileSize, ret, System.currentTimeMillis() - beginTime);
        }
    }

    // ====== getter/setter ====== //

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public File getFile() {
        return file;
    }

    public RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public FileChannel getFileChannel() {
        return fileChannel;
    }

    public MappedByteBuffer getMappedByteBuffer() {
        return mappedByteBuffer;
    }
}
