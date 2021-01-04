Queue的一些常用实现类的操作，即应用场景

ConcurrentLinkedQueue:
    线程安全的队列 offer,add 添加。poll取出，peek查看。

BlockingQueue:
    阻塞的队列，put, task方法为阻塞的

PriorityQueue:
    具有优先级的队列，可以比较里边存储的内容

DelayQueue：
    延时队列，也是具有比较的队列，DelayQueue<E extends Delayed> 只能存放实现了Delayed接口的东西

SynchronousQueue
    可以同步交换内容的队列,类比Exchanger,两个线程都等着同步交换数据
    然后这个队列里边的容量为0
