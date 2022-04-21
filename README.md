# java-io-benchmark

## read

### FileChannel

```log
Benchmark                                   (segmentSize)  Mode  Samples     Score  Score error  Units
c.b.FileChannelBenchmark.fileChannelRead                32    ss        5  42021.843      631.741     ms
c.b.FileChannelBenchmark.fileChannelRead                64    ss        5  21474.078     1232.872     ms
c.b.FileChannelBenchmark.fileChannelRead               128    ss        5  10737.173      164.094     ms
c.b.FileChannelBenchmark.fileChannelRead               256    ss        5   5350.412       43.091     ms
c.b.FileChannelBenchmark.fileChannelRead               512    ss        5   2743.964       42.634     ms
c.b.FileChannelBenchmark.fileChannelRead              1024    ss        5   1435.439       41.348     ms
c.b.FileChannelBenchmark.fileChannelRead              2048    ss        5    787.058       19.498     ms
c.b.FileChannelBenchmark.fileChannelRead              4096    ss        5    453.183       18.551     ms
c.b.FileChannelBenchmark.fileChannelRead             16384    ss        5    339.873      290.020     ms
c.b.FileChannelBenchmark.fileChannelRead           1048576    ss        5    216.191       12.958     ms
c.b.FileChannelBenchmark.fileChannelRead         134217728    ss        5    470.404      160.043     ms
c.b.FileChannelBenchmark.fileChannelRead        1073741824    ss        5    856.187      609.363     ms
```

### MMap

```log
Benchmark                     (fileSize)  Mode  Samples    Score  Score error  Units
c.b.MMapBenchmark.mmapRead                32    ss        5  420.181       60.743     ms
c.b.MMapBenchmark.mmapRead                64    ss        5  357.807       45.590     ms
c.b.MMapBenchmark.mmapRead               128    ss        5  332.104       42.462     ms
c.b.MMapBenchmark.mmapRead               256    ss        5  309.210       31.657     ms
c.b.MMapBenchmark.mmapRead               512    ss        5  306.405       42.458     ms
c.b.MMapBenchmark.mmapRead              1024    ss        5  306.000       45.773     ms
c.b.MMapBenchmark.mmapRead              2048    ss        5  296.160       27.781     ms
c.b.MMapBenchmark.mmapRead              4096    ss        5  297.386       28.290     ms
c.b.MMapBenchmark.mmapRead             16384    ss        5  294.400       32.478     ms
c.b.MMapBenchmark.mmapRead         134217728    ss        5  450.094       38.710     ms
c.b.MMapBenchmark.mmapRead        1073741824    ss        5  442.948       40.804     ms
```

### MMap cached

```log
Benchmark                                 (fileSize)  Mode  Samples    Score  Score error  Units
c.b.MMapCachedBenchmark.mmapCachedRead                32    ss        5  128.882        2.990     ms
c.b.MMapCachedBenchmark.mmapCachedRead                64    ss        5   81.829        0.871     ms
c.b.MMapCachedBenchmark.mmapCachedRead               128    ss        5   64.512        3.651     ms
c.b.MMapCachedBenchmark.mmapCachedRead               256    ss        5   51.273        1.100     ms
c.b.MMapCachedBenchmark.mmapCachedRead               512    ss        5   48.098        3.244     ms
c.b.MMapCachedBenchmark.mmapCachedRead              1024    ss        5   48.341        6.587     ms
c.b.MMapCachedBenchmark.mmapCachedRead              2048    ss        5   50.250        6.973     ms
c.b.MMapCachedBenchmark.mmapCachedRead              4096    ss        5   57.099       20.123     ms
c.b.MMapCachedBenchmark.mmapCachedRead             16384    ss        5   50.485        9.763     ms
c.b.MMapCachedBenchmark.mmapCachedRead         134217728    ss        5   97.017       18.979     ms
c.b.MMapCachedBenchmark.mmapCachedRead        1073741824    ss        5   91.634        2.764     ms
```

## write

### FileChannel

```log
Benchmark                                    (segmentSize)  Mode  Samples     Score  Score error  Units
c.b.FileChannelBenchmark.fileChannelWrite               32    ss        5  1277.926       54.299     ms
c.b.FileChannelBenchmark.fileChannelWrite               64    ss        5   634.020       19.249     ms
c.b.FileChannelBenchmark.fileChannelWrite              128    ss        5   383.857       12.895     ms
c.b.FileChannelBenchmark.fileChannelWrite              512    ss        5    98.382       35.046     ms
c.b.FileChannelBenchmark.fileChannelWrite             1024    ss        5    47.002        0.839     ms
c.b.FileChannelBenchmark.fileChannelWrite             2048    ss        5    24.169        2.524     ms
c.b.FileChannelBenchmark.fileChannelWrite             4096    ss        5    13.199        4.893     ms
c.b.FileChannelBenchmark.fileChannelWrite            16384    ss        5     3.453        0.123     ms
c.b.FileChannelBenchmark.fileChannelWrite          1048576    ss        5     0.699        0.724     ms
c.b.FileChannelBenchmark.fileChannelWrite        134217728    ss        5    90.528        7.505     ms
c.b.FileChannelBenchmark.fileChannelWrite       1073741824    ss        5  1119.451     1789.653     ms
```

### MMap

```log
Benchmark                      (segmentSize)  Mode  Samples    Score  Score error  Units
c.b.MMapBenchmark.mmapWrite               32    ss        5  406.538       45.634     ms
c.b.MMapBenchmark.mmapWrite               64    ss        5  354.243       50.682     ms
c.b.MMapBenchmark.mmapWrite              128    ss        5  337.641       37.541     ms
c.b.MMapBenchmark.mmapWrite              256    ss        5  376.163      358.727     ms
c.b.MMapBenchmark.mmapWrite              512    ss        5  310.441       32.717     ms
c.b.MMapBenchmark.mmapWrite             1024    ss        5  304.727       31.829     ms
c.b.MMapBenchmark.mmapWrite             2048    ss        5  307.475       58.492     ms
c.b.MMapBenchmark.mmapWrite             4096    ss        5  310.233       98.914     ms
c.b.MMapBenchmark.mmapWrite            16384    ss        5  301.317       24.313     ms
c.b.MMapBenchmark.mmapWrite        134217728    ss        5  355.191       20.205     ms
c.b.MMapBenchmark.mmapWrite       1073741824    ss        5  371.321       60.793     ms
```

### MMap cached

```log
Benchmark                                  (segmentSize)  Mode  Samples    Score  Score error  Units
c.b.MMapCachedBenchmark.mmapCachedWrite               32    ss        5  132.634        4.847     ms
c.b.MMapCachedBenchmark.mmapCachedWrite               64    ss        5   86.732        5.444     ms
c.b.MMapCachedBenchmark.mmapCachedWrite              128    ss        5   77.455       12.864     ms
c.b.MMapCachedBenchmark.mmapCachedWrite              256    ss        5   70.291       15.657     ms
c.b.MMapCachedBenchmark.mmapCachedWrite              512    ss        5   65.480        2.197     ms
c.b.MMapCachedBenchmark.mmapCachedWrite             1024    ss        5   71.017       15.219     ms
c.b.MMapCachedBenchmark.mmapCachedWrite             2048    ss        5   71.232        9.462     ms
c.b.MMapCachedBenchmark.mmapCachedWrite             4096    ss        5   66.395        4.098     ms
c.b.MMapCachedBenchmark.mmapCachedWrite            16384    ss        5   78.177       10.696     ms
c.b.MMapCachedBenchmark.mmapCachedWrite        134217728    ss        5  108.193       45.649     ms
c.b.MMapCachedBenchmark.mmapCachedWrite       1073741824    ss        5   99.173       17.277     ms
```