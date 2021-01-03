测试 Vector, ArrayList, SynchronizedList , CopyOnWriteArrayList
在并发的情况下的，写入/读取 速度。

ArrayList
    线程不安全
Vector
    添加测试完毕，共耗时：53ms，数组元素：1000000个
    读取测试完毕，共耗时：470ms
    添加测试完毕，共耗时：69ms，数组元素：1000000个
    读取测试完毕，共耗时：354ms
    添加测试完毕，共耗时：51ms，数组元素：1000000个
    读取测试完毕，共耗时：394ms
SynchronizedList
    添加测试完毕，共耗时：54ms，数组元素：1000000个
    读取测试完毕，共耗时：338ms
    添加测试完毕，共耗时：54ms，数组元素：1000000个
    读取测试完毕，共耗时：401ms
    添加测试完毕，共耗时：55ms，数组元素：1000000个
    读取测试完毕，共耗时：451ms
CopyOnWriteArrayList
    翻车
