# java-io-benchmark

## read

### FileChannel

```log
Benchmark                                   (segmentSize)  Mode  Samples     Score  Score error  Units
c.b.FileChannelBenchmark.fileChannelRead             1024    ss        5  1455.487       81.194     ms
c.b.FileChannelBenchmark.fileChannelRead             2048    ss        5   846.426       39.141     ms
c.b.FileChannelBenchmark.fileChannelRead             4096    ss        5   533.057       12.186     ms
c.b.FileChannelBenchmark.fileChannelRead            16384    ss        5   250.203       11.730     ms
c.b.FileChannelBenchmark.fileChannelRead          1048576    ss        5   148.131        4.530     ms
c.b.FileChannelBenchmark.fileChannelRead        134217728    ss        5   287.342       35.567     ms
c.b.FileChannelBenchmark.fileChannelRead       1073741824    ss        5   563.478      509.382     ms
```

### MMap

```log
Benchmark                     (fileSize)  Mode  Samples    Score  Score error  Units
c.b.MMapBenchmark.mmapRead            32    ss        5  380.573       53.623     ms
c.b.MMapBenchmark.mmapRead            64    ss        5  312.782        8.451     ms
c.b.MMapBenchmark.mmapRead           128    ss        5  295.384       23.400     ms
c.b.MMapBenchmark.mmapRead           256    ss        5  285.093       26.605     ms
c.b.MMapBenchmark.mmapRead           512    ss        5  281.447        8.600     ms
c.b.MMapBenchmark.mmapRead          1024    ss        5  279.240       12.995     ms
c.b.MMapBenchmark.mmapRead          2048    ss        5  280.221       21.057     ms
c.b.MMapBenchmark.mmapRead          4096    ss        5  285.388       28.296     ms
c.b.MMapBenchmark.mmapRead         16384    ss        5  281.584       10.312     ms
c.b.MMapBenchmark.mmapRead     134217728    ss        5  459.783       10.111     ms
c.b.MMapBenchmark.mmapRead    1073741824    ss        5  777.106      629.541     ms
```

### MMap cached

```log
Benchmark                                 (fileSize)  Mode  Samples    Score  Score error  Units
c.b.MMapCachedBenchmark.mmapCachedRead            32    ss        5  126.941        3.146     ms
c.b.MMapCachedBenchmark.mmapCachedRead            64    ss        5   77.011        0.857     ms
c.b.MMapCachedBenchmark.mmapCachedRead           128    ss        5   58.366        1.196     ms
c.b.MMapCachedBenchmark.mmapCachedRead           256    ss        5   51.082        1.035     ms
c.b.MMapCachedBenchmark.mmapCachedRead           512    ss        5   50.421        3.461     ms
c.b.MMapCachedBenchmark.mmapCachedRead          1024    ss        5   49.488        0.990     ms
c.b.MMapCachedBenchmark.mmapCachedRead          2048    ss        5   49.931        2.090     ms
c.b.MMapCachedBenchmark.mmapCachedRead          4096    ss        5   50.732        2.555     ms
c.b.MMapCachedBenchmark.mmapCachedRead         16384    ss        5   50.502        1.465     ms
c.b.MMapCachedBenchmark.mmapCachedRead     134217728    ss        5  121.873        4.407     ms
c.b.MMapCachedBenchmark.mmapCachedRead    1073741824    ss        5  394.772      426.710     ms
```