/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.benchmark;

import com.benchmark.util.FileUtil;
import com.benchmark.model.MappedFile;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MMapBenchmark {

    @Param({"32", "64", "128", "256", "512", "1024", "2048", "4096", "16384", "134217728", "1073741824"})
    public int segmentSize;

    public MappedFile mappedFile;
    byte[] payload;

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
}
