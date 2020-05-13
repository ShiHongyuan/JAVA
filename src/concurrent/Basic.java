package concurrent;

import java.util.concurrent.TimeoutException;

/**
 * 进程和线程的区别：
 *      1. 是否共享内存
 *          进程的内存空间是彼此独立的，一个进程不可以擅自读写其他进程的内存。
 *          但是一个进程的线程之间共享内存，线程之间的通信可以自然简单的实现，即一个实例可以由多个线程读写，所以要做好互斥处理。
 *
 *      2. 上下文切换的繁重程度
 *          进程保存和恢复自身的状态（上下文信息）比较多，信息切换比较费时间。
 *          线程管理的上下文信息比进程少，所以线程的上下文切换比进程快。
 *
 *     多个线程共享进程的堆和方法区 (JDK1.8 之后的元空间)资源，但是每个线程有自己的程序计数器、虚拟机栈 和 本地方法栈。
 *     为什么线程独自有：
 *          程序计数器私有主要是为了线程切换后能恢复到正确的执行位置。
 *          为了保证线程中的局部变量不被别的线程访问到，虚拟机栈和本地方法栈是线程私有的。（在 HotSpot 虚拟机中两个栈合二为一）。
 *
 *     方法区主要用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
 *
 *
 *     java线程的生命周期和状态?
 *          new 一个 Thread，是进入创建状态，start后才是进入就绪状态。
 *          new：已经被创建，还未start
 *          runnable：java中把线程的就绪ready和运行running统称为运行中。（yield）
 *          blocked：阻塞状态，表示阻塞于锁（synchronized，Semaphore）
 *          waiting：等待状态，需要其他线程唤醒或者中断（wait，join）
 *          time_waiting：超时等待状态（sleep，wait(long)，join(long)），不需要其他线程，也能在指定时间后自行返回。
 *          terminated：终止状态，run方法执行完了。
 *
 *
 *     使用多线程可能带来什么问题?
 *          并发编程的目的就是为了能提高程序的执行效率提高程序运行速度，但是并发编程并不总是能提高程序运行速度的，
 *          而且并发编程可能会遇到很多问题，比如：编程容易出错、死锁、内存泄漏、上下文切换消耗、的问题。
 *
 *     上下文新切换？
 *          当一个线程的时间片用完的时候就会重新处于就绪状态让给其他线程使用，这个过程就属于一次上下文切换。
 *          当前任务在执行完 CPU 时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换会这个任务时，可以再加载这个任务的状态。任务从保存到再加载的过程就是一次上下文切换。
 *
 *          上下文切换对系统来说意味着消耗大量的 CPU 时间，事实上，可能是操作系统中时间消耗最大的操作。
 *          Linux 相比与其他操作系统（包括其他类 Unix 系统）的有一项优先就是，其上下文切换和模式切换的时间消耗非常少。
 *
 */

/**
 * jvm的重排序和可见性
 *      重排序reorder：jvm的实现有时为了优化，在没有依赖关系的方法里改变程序的执行顺序。有时可能会使没有受到线程保护的对象失去安全性。
 *                   比如说一个方法里是两个独立的变量的赋值，本来先执行哪个后执行哪个没有关系，
 *                   但是如果有另一个方法是读取这两个便利的值，那么读取方法在赋值过程中运行的话，先后赋值的差异就会让读取的方法存在多变性了。
 *
 *      可见性：除了线程栈的局部变量，方法的形参、catch块里的异常处理器的参数外，在共享内存（java堆）的对象都是共享对象，
 *             共享对象在每个线程里都有一个缓存（比如说线程的寄存器），某个线程修改后的数据在缓存里，读取也是读取的缓存里的数据，
 *             写入缓存不一定立刻能更新到共享内存里，读取缓存也不一定立刻能更新为共享内存的数据，所以一个线程修改后，其他线程不一定立刻能读取到正确的值。
 *
 *             一个线程写入的值能够被其他线程读到，就叫该数据对于其他线程是可见的。
 *
 *      synchronized：既可以处理线程互斥，也可以处理同步缓存（可见性）。
 *
 *                    处理线程互斥：
 *                      释放锁后，别的才能获取锁，即使在方法中发生了重排序，也不会对读方法产生影响。
 *                    处理同步缓存：
 *                      在unlock时，会把缓存的共享对象强制写入到共享内存中
 *                      在lock时，现有的缓存会失效，会把共享内存中的最新内容强制读取到缓存中。
 *                      保证了共享数据的可见性。
 *
 *                    避免共享数据受到重排序和可见性的影响。性能也会降低。
 *
 *
 *
 *      volatile：可以处理同步缓存（可见性和重排序），也可以对long和double进行原子操作。
 *                 处理同步缓存：
 *                      某个线程对volatile字段的写操作的结果对其他线程是立即可见的。
 *                      在volatile字段写入和读取的前后不会发生重排序。
 *                      比如说在volatile字段修改状态前，有其他字段的赋值，那么jvm在执行时会确保其他字段都被赋值完后，volatile字段才会被赋值。
 *
 *                 对long和double进行原子操作：
 *                      java规范不能保证long和double的赋值操作的原子性。
 *                      但是只要声明为volatile字段，就可以确保赋值操作的原子性。
 *
 *                      java.util.concurrent.atomic包是volatile字段的一种通用化形式。
 *
 *
 *               避免共享数据受到重排序和可见性的影响。虽然没用到synchronized，但是性能也和synchronized差不多。
 *
 *
 *
 *
 *      对象的构造函数：构造函数结束时能保证，构造函数里的赋值操作对其他线程都是可见的，
 *                   但是构造函数结束前，不能保证已经赋值的字段对其他线程都是可见的。
 *
 *      线程启动后的第一个操作：保证可见性，没有重排序。
 *      线程终止前的最后一个操作：保证可见性，没有重排序。
 *      中断后：检测中断的操作，抛出异常的操作，是保证可见性，没有重排序的。
 *
 *
 *      normal read/write：是对线程缓存的操作。
 *      synchronized read/write：是对共享内存的操作。
 *      volatile read/write：是对共享内存的操作。
 *      获取实例锁/释放实例锁：是对共享内存的操作。
 *
 *
 * 原子性：原子操作一个操作只允许一个线程完成，一个操作一旦开始，就不会被其他线程干扰。（多线程中原子操作不是不能被cpu或jvm调度，时间分片运行和就绪都是一直运行中）
 *          long和double的赋值和引用不是原子操作 —— volatile 关键字
 *          java.util.concurrent.atomic包：原子操作的类 AtomicInteger、AtomicLong、AtomicIntegerArray、AtomicLongArray
 *          （都封装了volatile功能）
 *
 * 可重入性（Reentrant）：已经获取读锁还未释放的线程，可以获取写锁；已经获取写锁还未释放的线程，也可以获取读锁。
 *
 *
 *
 * 说说 synchronized 关键字和 volatile 关键字的区别？
 *      1. volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。
 *      2. 多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞。
 *      3. volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized关键字解决的是资源的互斥。
 *      4. volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但是1.6后synchronized性能有了显著提升。
 */

/**
 * 线程互斥，保护临界区：
 *      synchronized关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。
 *
 *      获取的是监视器锁（monitor，monitor对象存在于每个Java对象的对象头中，当计数器为0则可以成功获取，获取后将锁计数器加1。释放后将锁计数器设为0。如果获取对象锁失败，那当前线程就要阻塞等待，直到锁被另外一个线程释放为止。）
 *      synchronized 修饰的非静态方法（锁是实例的锁）
 *      synchronized 代码块（锁是自定义对象或者类名）
 *              synchronized (this) {...}  synchronized (person) {...}
 *              synchronized (obj) {...} obj可以是本实例自己，也可以其他对象的实例
 *              synchronized (classname) {...} 获取的是对应类的锁
 *      synchronized 修饰静态方法（锁是对应类的锁）
 *
 *      尽量不要使用 synchronized(String a) 因为JVM中，字符串常量池具有缓存功能！
 *
 *      & before/after
 *              方法不用被synchronized修饰
 *              方法体内部：
 *                  第一行 before(显式获取锁或信号量) //获取锁不放在try里面，是因为万一在try里获取锁失败，finally也会执行释放锁，就不正确了。
 *                  try{
 *                      ... 执行具体的读或写互斥操作
 *                  } finally {
 *                      after(显式释放锁或信号量) //只要进入了try，表示就已经获取了锁，不管是return还是throw异常都会执行释放锁，不会死锁。
 *                  }
 *
 *
 *          synchronized是可重入锁。
 *
 *          JDK1.6 对synchronized锁的实现引入了大量的优化，如偏向锁、轻量级锁、自旋锁、适应性自旋锁、锁消除、锁粗化等技术来减少锁操作的开销。
 *          JDK1.6 以前，synchronized锁为 “重量级锁”，以前的java线程与操作系统线程关联，线程切换，需要操作系统切换用户态和内核态，开销很大。
 *
 *
 *
 *          synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，Semaphore(信号量)可以指定多个线程同时访问某个资源。
 */

/**
 * 线程使用信息的方式：
 *      1. 基于角色的方法，信息都保存在线程的实例中，比如说实现一个Thread的子类，就是实现了一个小角色。
 *      2. 基于任务的方式，信息保存在任务内，把任务传递给Thread执行，比如说实现了Runnable的类，就是实现了一个小任务。FutureTask也是一个基于任务的类，它实现了Runnable，并且拥有Callable
 */

/**
 * 请求任务交由线程处理：
 *      想要节省频繁启动新线程的时间，就使用线程池
 *      想要获得处理结果，就使用Future
 */
public class Basic {
    public static void main (String[] args) {

        /**
         * 基本包装类型，String，BigDecimal，BigInteger 都是不可变类型 immutable。
         * StringBuffer 是可变类型 mutable
         */
        // String的replace方法，并不会修改原来的String，只是会创建一个新的String返回
        String s1 = "shihongyuan";
        String s2 = s1.replace('s','y');
        if (s1 == s2 || s1.equals(s2)) {
            System.out.println("true");
        } else {
            System.out.println("false");//false
        }

        StringBuffer sb1 = new StringBuffer("shihongyuan");
        StringBuffer sb2 = sb1.replace(0,1, "y");

        if (sb1 == sb2) {
            System.out.println("true");//true
        } else {
            System.out.println("false");
        }

        /**
         * 线程暂停：只有wait线程会进等待队列，是不运行的，不会浪费java虚拟机的处理时间，但是wait会释放锁和获取锁。
         *
         *          其他的都会一直被调度检查条件是否满足，算是一直在运行的，会浪费java虚拟机的处理时间，
         *          但是如果在 synchronized 里调用，都不会释放锁，暂停运行，别的线程也拿不到互斥锁。
         *
         * synchronized, wait, notify/notifyAll
         *      synchronized {
         *          while(!ready) {
         *              wait();
         *          }
         *          满足的处理
         *      }
         *      synchronized {
         *          ready = true;
         *          notifyAll();
         *      }
         *
         *      放入实例的线程等待队列，线程是停止运行的（所谓的运行是在分时被java虚拟机或者cpu调度的），不会浪费java虚拟机的处理时间。
         *              一个线程调用 某个实例的 synchronized 方法，获取其实例锁 ——> 条件不满足，调用 wait(); ——> 当前线程放入被调用方法所属实例的线程等待队列中，释放被调用方法所属实例锁
         *              ——> 另一个线程调用 同一个实例的 另一个 synchronized 方法获取其实例锁 ——> 条件满足，调用 notifyAll(); ——> synchronized 方法方程释放实例锁
         *              ——> 该实例的线程等待队列中线程进入可运行状态（能够被jvm调度，会浪费jvm的处理时间），等待获取实例锁，继续判断条件满足，就执行下面的代码。
         *
         *      wait 被唤醒的方法：
         *          1. notify/notifyAll
         *          2. 等待的线程被调用interrupt
         *          3. wait设置超时，时间到了以上两种情况都未发生，就超时了。
         *
         *      wait(时间)超时：
         *      wait()或者wait(0) 表示没有超时时间
         *      如果调用wait(1000) 表示有超时时间。超时后，线程会被唤醒，首先获取锁，再执行wait后面的代码，跟notify/notifyAll一样。
         *      不会自己抛出TimeOutException，除非是自己抛出的。
         *
         *      处理 wait 超时常见的方法：判断超时后，要么抛出异常，要么return方法的返回值为 false 或者 null。
         *
         *
         * Thread.yield
         *      不满足条件，会暂时不执行了，但是会不停地被调度检查条件是否满足，一旦满足，就会继续执行。
         *      所以yield后也是一直在运行的（多线程中能够被分时调度就是在运行），会浪费java虚拟机的处理时间。
         *      Thread.yield 不在 synchronized里，也是不会释放锁，但是信号字段 ready必须声明为 volatile。
         *
         *      {
         *          while(!ready) {
         *              Thread.yield();
         *          }
         *          满足的处理
         *      }
         *      {
         *          ready=true; // 不用唤醒，线程自己被调度然后检查条件
         *      }
         *
         *
         * 互斥：被synchronized修饰的方法或者代码块
         *      如果没有拿到锁，线程会被阻塞，但是会不停地被调度检查是否可以拿到锁，一旦拿到，就会继续执行。
         *      所以阻塞的等待也是一直在运行的，会浪费java虚拟机的处理时间。
         *
         *
         * Thread.sleep：在指定时间内暂停
         *      每当被调度前，检查一下时间满足不，不满足就略过不运行，所以线程也是一直在运行的。
         *
         *      如果 Thread.sleep 在 synchronized里，调用Thread.sleep 时，是不会释放锁的，所以注意不要在synchronized里造成无限循环。
         *
         *
         *
         *
         * interrupt方法：
         *      线程调用interrupt方法，只对线程中关注中断状态的 Thread.sleep、wait、join 有效。
         *      其他情况不会关注Interrupt信号，除非在线程运行过程中自己检测 isInterrupted方法或者 interrupted方法，然后自己抛出异常。
         */

        // wait超时，线程被唤醒后，行为跟 notify/notifyAll唤醒后一样，不会自己抛出 TimeoutException
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Basic basicObj = new Basic();
                    boolean ready = false;
                    long timeout = 1000;
                    long start = System.currentTimeMillis();
                    int round = 0;
                    while (!ready) {
                        round++;
                        long now = System.currentTimeMillis();
                        long rest = timeout - (now - start);
                        if (rest <= 0) {
                            throw new TimeoutException("round = " + round + ", now - start = " + (now - start) + ", timeout = " + timeout);
                            // round = 2, now - start = 1004, timeout = 1000
                        }

                        try {
                            synchronized (basicObj) {
                                basicObj.wait(timeout);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IllegalMonitorStateException e) { // 非 synchronized 方法里调用，抛出的异常
                            e.printStackTrace();}
                    }
                } catch (TimeoutException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();



    }


}
