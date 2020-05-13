package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 死锁 & 活锁：
 *      死锁：多个线程相互持有对方想要的锁，而互相等待进入死锁状态。
 *      活锁：线程一直在运行，但是可能在一个死循环里，程序没有实质性的进展。
 *
 *      破坏其中任意一个条件，避免死锁？
 *          1. 互斥条件：该资源任意一个时刻只由一个线程占用。
 *          这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
 *
 *          2. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 *          一次性申请所有的资源。或者释放占有的资源后，再申请下一个资源。
 *
 *          3. 不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 *          占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
 *
 *          4. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 *          靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。
 *
 *
 *
 * 闭锁：只发生一次状态改变，一旦改变，不能再变回去，一旦锁上，再也不会释放的状态。
 *      比如说CountDownLatch.countDown()方法就是闭锁机制。
 *
 * 可重入锁：自己可以再次获取自己的内部锁。
 *         比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。
 *
 *         比如用一个标志量state=false表示锁，就不可重入
 *         可重入的实现：
 *              比如用标志量和实例标志，state=false，obj = this; 判断时state==true || obj == this可获取到锁，就是可重入的。
 *              或者重复获取锁时，state累加，但要注意，获取多少次就要释放多么次，这样才能保证state能回到 0。
 *
 *         Synchronized 和 ReenTrantLock 都是可重入锁。
 *
 * 物理锁 & 逻辑锁
 *      物理锁：Java编程规范规定的，jvm实现的锁，比如说 synchronized 获取的实例的锁
 *      逻辑锁：为了实现多线程模式，开发人员自己设计的一种结构。
 *             比如说可重入锁，读写锁：读-读不冲突，读-写冲突，写-写冲突，如果全部用synchronized修饰，会读-读也冲突，浪费时间。
 *
 *
 *
 *  synchronized 和 ReentrantLock 的区别?
 *      1. 两者都是可重入锁
 *      2. synchronized是物理锁，由jvm底层实现。
 *         ReentrantLock是逻辑锁，ReentrantLock 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock() 方法配合 try/finally 语句块来完成）。
 *
 *
 * 乐观锁 & 悲观锁
 *      乐观锁：
 *          每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据。否则提交被驳回。
 *
 *          乐观锁一般会使用版本号机制（version），或者CAS算法（compare and swap（比较与交换））实现。
 *          在Java中java.util.concurrent.atomic包下面的原子变量类就是使用了乐观锁的一种实现方式CAS实现的。
 *
 *          乐观锁适用于写比较少的情况下（多读场景），即冲突真的很少发生的时候，这样可以省去了锁的开销，加大了系统的整个吞吐量。
 *
 *      悲观锁：
 *          每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁。
 *
 *          传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。
 *          Java中synchronized和ReentrantLock等独占锁就是悲观锁思想的实现。
 *
 *          一般多写的场景下用悲观锁就比较合适。
 *
 *
 * CAS算法（compare and swap（比较与交换））：
 *      无锁算法。无锁编程，所以也叫非阻塞同步（Non-blocking Synchronization）。
 *      CAS算法涉及到三个操作数：
 *          需要读写的内存值 V
 *          进行比较的值（最初获取的值） A
 *          拟写入的新值 B
 *
 *
 *      当且仅当 V 的值等于 A时（说明这个过程V没有被其他线程改过），CAS通过原子方式用新值B来更新V的值，否则不会执行任何操作（乐观锁嘛，比人已经改过了，我就不改了）。
 *      比较和替换是一个原子操作，一般情况下是一个自旋操作，即不断的重试。
 *
 *
 *      CAS ABA 问题：
 *      我最初读到的是A，其他线程修改了B，又改回了A，我比较时，A=A，认为没有被其他线程改过，我就改了。
 *      但其实，中间已经被改过，我不能改了。
 *
 */
public class ConcurrentLock {
    public static void main (String[] args) {
        /**
         * 读写锁：读写模式：读-读不冲突，读-写冲突，写-写冲突
         *      锁内部需要记录正在读的线程个数，正在写的线程个数，通过对个数的判断，决定是wait，还是执行读或者写
         *
         *      线程读的时候，写的线程要等待；线程写的时候，读和写的线程都要等待；但是读线程可以有多个，相比于读写都synchronized，
         *      既确保了安全性，又提高了程序的性能。
         *
         *      java.util.concurrent.locks 包
         *          java.util.concurrent.locks.Lock：与synchronized的锁具有不同结构的锁的接口
         *
         *
         *          读写锁的接口：java.util.concurrent.locks.ReadWriteLock接口
         *          可重入锁的接口： java.util.concurrent.locks.ReentrantLock接口
         *          可重入锁的高级功能：①等待可中断；②可实现公平锁；③可实现选择性通知（锁可以绑定多个条件）④ 性能已不是选择标准
         *
         *
         *          读写锁的可重入锁的具体实现类：java.util.concurrent.locks.ReentrantReadWriteLock 类
         *              1. 可设置获取锁的公平性，如果设置获取锁的顺序是公平的，那么等待时间越久的线程将优先获取锁。
         *              2. 可重入（Reentrant）：已经获取读锁还未释放的线程，可以获取写锁；已经获取写锁还未释放的线程，也可以获取读锁。
         *              3. 锁降级：因为可重入获取两个锁后，必须先释放写锁，再释放读锁。
         *
         *
         */
        // java.util.concurrent.locks.ReentrantReadWriteLock 可重入读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);// 线程获取锁的公平性为true
        Lock readLock = readWriteLock.readLock(); //得到读写锁的读锁
        Lock writeLock = readWriteLock.writeLock(); //得到读写锁的写锁


        // 利用读锁读数据
        class ReadThread {
            public String read() {
                readLock.lock(); // before获取锁放到try的前面，是为了万一在try里获取锁里失败了，也会执行after释放锁，就不对了。
                                 // 放在外面，如果失败了，就不会进入try就不会实行释放锁，如果进入try，就一定已经成功获取了锁。
                try {
                    String s = null;//如果不初始化，也不会默认null，会报错
                    // 读具体数据
                    return s;
                } finally {
                    readLock.unlock(); // return前也会执行释放锁
                }
            }
        }

        // 利用写锁读数据
        class WriteThread {
            public void write(String s) {
                readLock.lock();
                try {
                    // 写具体数据
                } finally {
                    readLock.unlock(); // 返回前一定会执行释放锁
                }
            }
        }


        // concurrent 包的读写锁实现原理
        class ReadWriteLock {
            int readingReaders = 0; // 正在读的线程个数
            int writingWriters = 0; // 正在写的线程个数

            public synchronized void readLock() throws InterruptedException {
                while(writingWriters > 0) {
                    wait();
                }
                readingReaders ++;
            }

            public synchronized void readUnLock() {
                readingReaders --;
                notifyAll();
            }

            public synchronized void writeLock() throws InterruptedException {
                while(writingWriters > 0 || readingReaders > 0) {
                    wait();
                }
                writingWriters ++;
            }

            public synchronized void writeUnLock() {
                writingWriters --;
                notifyAll();
            }
        }

    }
}
