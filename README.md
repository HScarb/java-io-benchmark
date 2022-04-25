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

## Linux

```log
FileChannelBenchmark.asyncWrite                   16    ss    5  11976.409 ±  4284.231  ms/op
FileChannelBenchmark.asyncWrite                   32    ss    5   5337.194 ±   622.575  ms/op
FileChannelBenchmark.asyncWrite                   64    ss    5   2679.704 ±   180.923  ms/op
FileChannelBenchmark.asyncWrite                  128    ss    5   1356.835 ±   302.162  ms/op
FileChannelBenchmark.asyncWrite                  256    ss    5    697.288 ±   175.948  ms/op
FileChannelBenchmark.asyncWrite                  512    ss    5    350.964 ±    10.374  ms/op
FileChannelBenchmark.asyncWrite                 1024    ss    5    174.309 ±    15.108  ms/op
FileChannelBenchmark.asyncWrite                 2048    ss    5    100.223 ±     3.481  ms/op
FileChannelBenchmark.asyncWrite                 4096    ss    5     45.843 ±    12.230  ms/op
FileChannelBenchmark.asyncWrite                16384    ss    5     13.092 ±     1.682  ms/op
FileChannelBenchmark.asyncWrite                65536    ss    5      3.125 ±     0.682  ms/op
FileChannelBenchmark.asyncWrite              1048576    ss    5      2.560 ±     5.856  ms/op
FileChannelBenchmark.asyncWrite             16777216    ss    5     15.075 ±     3.003  ms/op
FileChannelBenchmark.asyncWrite            134217728    ss    5    128.474 ±    17.059  ms/op
FileChannelBenchmark.asyncWrite           1073741824    ss    5   8063.066 ±  6053.588  ms/op
FileChannelBenchmark.fileChannelRead              16    ss    5  46266.141 ± 16111.142  ms/op
FileChannelBenchmark.fileChannelRead              32    ss    5  22973.351 ±  4103.917  ms/op
FileChannelBenchmark.fileChannelRead              64    ss    5  13725.336 ±   781.304  ms/op
FileChannelBenchmark.fileChannelRead             128    ss    5   5827.248 ±   446.171  ms/op
FileChannelBenchmark.fileChannelRead             256    ss    5   3221.425 ±   217.890  ms/op
FileChannelBenchmark.fileChannelRead             512    ss    5   1793.952 ±   108.876  ms/op
FileChannelBenchmark.fileChannelRead            1024    ss    5   1100.519 ±   117.085  ms/op
FileChannelBenchmark.fileChannelRead            2048    ss    5    797.389 ±   107.032  ms/op
FileChannelBenchmark.fileChannelRead            4096    ss    5    616.463 ±    55.990  ms/op
FileChannelBenchmark.fileChannelRead           16384    ss    5    522.719 ±    22.762  ms/op
FileChannelBenchmark.fileChannelRead           65536    ss    5    497.268 ±    35.936  ms/op
FileChannelBenchmark.fileChannelRead         1048576    ss    5    619.240 ±   172.822  ms/op
FileChannelBenchmark.fileChannelRead        16777216    ss    5    871.867 ±    79.053  ms/op
FileChannelBenchmark.fileChannelRead       134217728    ss    5    938.884 ±    43.486  ms/op
FileChannelBenchmark.fileChannelRead      1073741824    ss    5   1580.910 ±   341.747  ms/op
FileChannelBenchmark.fileChannelWrite             16    ss    5   8127.386 ±   497.490  ms/op
FileChannelBenchmark.fileChannelWrite             32    ss    5   4102.832 ±   447.642  ms/op
FileChannelBenchmark.fileChannelWrite             64    ss    5   2056.092 ±   246.116  ms/op
FileChannelBenchmark.fileChannelWrite            128    ss    5   1008.342 ±    87.089  ms/op
FileChannelBenchmark.fileChannelWrite            256    ss    5    553.695 ±   388.407  ms/op
FileChannelBenchmark.fileChannelWrite            512    ss    5    367.028 ±    17.650  ms/op
FileChannelBenchmark.fileChannelWrite           1024    ss    5    189.730 ±    21.670  ms/op
FileChannelBenchmark.fileChannelWrite           2048    ss    5     93.619 ±     3.587  ms/op
FileChannelBenchmark.fileChannelWrite           4096    ss    5     45.237 ±     2.830  ms/op
FileChannelBenchmark.fileChannelWrite          16384    ss    5     11.417 ±     0.741  ms/op
FileChannelBenchmark.fileChannelWrite          65536    ss    5      3.820 ±     2.144  ms/op
FileChannelBenchmark.fileChannelWrite        1048576    ss    5      1.270 ±     0.716  ms/op
FileChannelBenchmark.fileChannelWrite       16777216    ss    5     15.478 ±     6.142  ms/op
FileChannelBenchmark.fileChannelWrite      134217728    ss    5    118.317 ±    17.653  ms/op
FileChannelBenchmark.fileChannelWrite     1073741824    ss    5   8318.853 ±  5163.644  ms/op
```

```log
MMapBenchmark.asyncWrite             16    ss    5  14009.122 ± 27774.122  ms/op
MMapBenchmark.asyncWrite             32    ss    5  18982.398 ±  6797.047  ms/op
MMapBenchmark.asyncWrite             64    ss    5  18499.618 ±  5437.993  ms/op
MMapBenchmark.asyncWrite            128    ss    5  17280.314 ± 18939.977  ms/op
MMapBenchmark.asyncWrite            256    ss    5  15300.308 ± 21195.710  ms/op
MMapBenchmark.asyncWrite            512    ss    5  17308.137 ± 25387.147  ms/op
MMapBenchmark.asyncWrite           1024    ss    5  16209.571 ± 21159.145  ms/op
MMapBenchmark.asyncWrite           2048    ss    5  19797.237 ± 14210.544  ms/op
MMapBenchmark.asyncWrite           4096    ss    5  17282.586 ± 16802.597  ms/op
MMapBenchmark.asyncWrite          16384    ss    5  17772.545 ± 11133.162  ms/op
MMapBenchmark.asyncWrite          65536    ss    5  12197.140 ± 29527.846  ms/op
MMapBenchmark.asyncWrite        1048576    ss    5  19382.211 ± 13797.241  ms/op
MMapBenchmark.asyncWrite       16777216    ss    5  13967.879 ±  7347.735  ms/op
MMapBenchmark.asyncWrite      134217728    ss    5  15388.282 ± 24214.112  ms/op
MMapBenchmark.asyncWrite     1073741824    ss    5  15283.359 ± 19866.252  ms/op
MMapBenchmark.mmapRead               16    ss    5    960.889 ±   327.034  ms/op
MMapBenchmark.mmapRead               32    ss    5    672.689 ±    10.735  ms/op
MMapBenchmark.mmapRead               64    ss    5    557.385 ±    62.151  ms/op
MMapBenchmark.mmapRead              128    ss    5    517.442 ±   154.412  ms/op
MMapBenchmark.mmapRead              256    ss    5    475.793 ±    46.104  ms/op
MMapBenchmark.mmapRead              512    ss    5    475.182 ±   103.400  ms/op
MMapBenchmark.mmapRead             1024    ss    5    461.834 ±    24.256  ms/op
MMapBenchmark.mmapRead             2048    ss    5    457.317 ±    20.150  ms/op
MMapBenchmark.mmapRead             4096    ss    5    459.297 ±    20.524  ms/op
MMapBenchmark.mmapRead            16384    ss    5    473.530 ±    68.662  ms/op
MMapBenchmark.mmapRead            65536    ss    5    465.685 ±    33.021  ms/op
MMapBenchmark.mmapRead          1048576    ss    5    506.804 ±    39.126  ms/op
MMapBenchmark.mmapRead         16777216    ss    5    619.579 ±    71.868  ms/op
MMapBenchmark.mmapRead        134217728    ss    5    614.185 ±    61.867  ms/op
MMapBenchmark.mmapRead       1073741824    ss    5    605.256 ±    67.951  ms/op
MMapBenchmark.mmapWrite              16    ss    5   2107.910 ±  1952.881  ms/op
MMapBenchmark.mmapWrite              32    ss    5   1973.216 ±  3227.897  ms/op
MMapBenchmark.mmapWrite              64    ss    5   1549.422 ±  2134.173  ms/op
MMapBenchmark.mmapWrite             128    ss    5   1039.559 ±   167.262  ms/op
MMapBenchmark.mmapWrite             256    ss    5   1063.660 ±   360.001  ms/op
MMapBenchmark.mmapWrite             512    ss    5   1611.539 ±  2229.356  ms/op
MMapBenchmark.mmapWrite            1024    ss    5   1474.839 ±  1882.361  ms/op
MMapBenchmark.mmapWrite            2048    ss    5   1004.378 ±   169.871  ms/op
MMapBenchmark.mmapWrite            4096    ss    5   1047.274 ±   294.052  ms/op
MMapBenchmark.mmapWrite           16384    ss    5   1181.304 ±  1566.387  ms/op
MMapBenchmark.mmapWrite           65536    ss    5   1006.999 ±    76.038  ms/op
MMapBenchmark.mmapWrite         1048576    ss    5   1426.651 ±  3545.171  ms/op
MMapBenchmark.mmapWrite        16777216    ss    5   1205.563 ±   332.695  ms/op
MMapBenchmark.mmapWrite       134217728    ss    5   1361.559 ±   588.911  ms/op
MMapBenchmark.mmapWrite      1073741824    ss    5   5114.934 ±  5953.375  ms/op
```

```log
MMapWarmedBenchmark.mmapCachedRead              16    ss    5   480.702 ±    49.716  ms/op
MMapWarmedBenchmark.mmapCachedRead              32    ss    5   271.249 ±    16.354  ms/op
MMapWarmedBenchmark.mmapCachedRead              64    ss    5   210.144 ±    26.359  ms/op
MMapWarmedBenchmark.mmapCachedRead             128    ss    5   169.390 ±    23.999  ms/op
MMapWarmedBenchmark.mmapCachedRead             256    ss    5   146.115 ±    11.670  ms/op
MMapWarmedBenchmark.mmapCachedRead             512    ss    5   138.865 ±     8.342  ms/op
MMapWarmedBenchmark.mmapCachedRead            1024    ss    5   132.409 ±    18.004  ms/op
MMapWarmedBenchmark.mmapCachedRead            2048    ss    5   134.538 ±    13.373  ms/op
MMapWarmedBenchmark.mmapCachedRead            4096    ss    5   127.908 ±    17.390  ms/op
MMapWarmedBenchmark.mmapCachedRead           16384    ss    5   127.968 ±    10.847  ms/op
MMapWarmedBenchmark.mmapCachedRead           65536    ss    5   128.882 ±    16.758  ms/op
MMapWarmedBenchmark.mmapCachedRead         1048576    ss    5   147.295 ±    24.043  ms/op
MMapWarmedBenchmark.mmapCachedRead        16777216    ss    5   207.680 ±    12.400  ms/op
MMapWarmedBenchmark.mmapCachedRead       134217728    ss    5   237.415 ±    20.014  ms/op
MMapWarmedBenchmark.mmapCachedRead      1073741824    ss    5   237.511 ±    31.162  ms/op
MMapWarmedBenchmark.mmapCachedWrite             16    ss    5  1012.771 ±    99.980  ms/op
MMapWarmedBenchmark.mmapCachedWrite             32    ss    5  1171.615 ±  1496.865  ms/op
MMapWarmedBenchmark.mmapCachedWrite             64    ss    5  1245.785 ±  3183.682  ms/op
MMapWarmedBenchmark.mmapCachedWrite            128    ss    5   896.826 ±   434.413  ms/op
MMapWarmedBenchmark.mmapCachedWrite            256    ss    5  1103.318 ±  1480.200  ms/op
MMapWarmedBenchmark.mmapCachedWrite            512    ss    5   921.574 ±   516.375  ms/op
MMapWarmedBenchmark.mmapCachedWrite           1024    ss    5   936.219 ±   313.758  ms/op
MMapWarmedBenchmark.mmapCachedWrite           2048    ss    5  1069.277 ±  1657.546  ms/op
MMapWarmedBenchmark.mmapCachedWrite           4096    ss    5  1052.353 ±  1224.634  ms/op
MMapWarmedBenchmark.mmapCachedWrite          16384    ss    5   997.852 ±  1262.600  ms/op
MMapWarmedBenchmark.mmapCachedWrite          65536    ss    5   844.095 ±    51.094  ms/op
MMapWarmedBenchmark.mmapCachedWrite        1048576    ss    5   861.713 ±    76.768  ms/op
MMapWarmedBenchmark.mmapCachedWrite       16777216    ss    5  1026.836 ±   347.612  ms/op
MMapWarmedBenchmark.mmapCachedWrite      134217728    ss    5  2852.539 ±  2078.701  ms/op
MMapWarmedBenchmark.mmapCachedWrite     1073741824    ss    5  8758.532 ± 10942.719  ms/op
```
