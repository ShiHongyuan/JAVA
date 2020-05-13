package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.Future;

/**
 * 线程同步机制：
 *      1. java.util.concurrent.Exchanger<E>类 : 让两个线程交换对象的同步机制。两个线程用同一个Exchanger实例，来安全的交换彼此的对象。对象可以是任何对象，数组啊，实例啊，
 *      2. java.util.concurrent.Semaphore类: 计数信号量。
 *      3. java.lang.Thread.join() 方法: 等待其他线程完成后才可以继续执行
 *      4. java.util.concurrent.CountDownLatch类: 倒计时器。让线程等待某个操作执行完指定次数的同步机制。
 *      5. java.util.concurrent.CyclicBarrier类: 循环栅栏。让多个线程在特定位置（屏障处）等待，全部到达后再继续执行任务的同步机制。
 *
 *      倒计时器和循环栅栏的区别？
 *          倒计时器只能使用一次，计数器不能被重复设置。
 *          但是循环栅栏可以循环设置当前等待的线程数，每次循环重新设置为0，达到指定数量时就打开屏障，直到退出循环。
 */


public class ThreadJoin {
    public static void main (String[] args) {
        /**
         * Thread实例的join方法，调用线程会等待指定线程终止后再继续执行
         */
        System.out.println(Thread.currentThread().getName() + ": Main start");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + ": t1 start");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + ": t1 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        try {
            t1.join(); // 开启其他线程t1后，等待t1执行完才能往下走
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Main end");

        /**
         * java.util.concurrent.CountDownLatch类 倒计时器
         *      一个计数器，每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。
         *
         *      应用：
         *          1. 一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
         *          2. 实现多个线程开始执行的并行性。一个共享的 CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1)，多个线程在开始执行任务前首先 coundownlatch.await()，当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。
         *          3.
         *
         *
         *
         *
         *
         */
        // 线程池的数量是3，工作任务的数据是5，所以countDownLatch的倒计数是5
        System.out.println(Thread.currentThread().getName() + ": Main start");
        ThreadPoolExecutor pools = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        // 创建一个countDownLatch对象，倒计数5，要调用5次countDown方法
        CountDownLatch countDownLatch = new CountDownLatch(5);

        // 传递同一个countDownLatch给其他线程们
        Future<String> futureTask1 = pools.submit(new MyTask1(countDownLatch, 1));
        Future<String> futureTask2 = pools.submit(new MyTask1(countDownLatch, 2));
        Future<String> futureTask3 = pools.submit(new MyTask1(countDownLatch, 3));
        Future<String> futureTask4 = pools.submit(new MyTask1(countDownLatch, 4));

        FutureTask<String> futureTask5 = new FutureTask(new MyTask1(countDownLatch, 5));
        pools.submit(futureTask5);



        // 主线程调用 countDownLatch.await，会让主线程等待这个countDownLatch对象在其他线程里倒计数为0，然后再继续执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": Main end");


        /**
         * java.util.concurrent.CyclicBarrier类 循环栅栏
         *      一个阶段任务屏障，每个阶段任务必须等待拥有cyclicBarrier实例的所有线程都执行完后，才能一起执行下一个阶段任务
         *      比CountDownLatch类粒度更细，CountDownLatch是等待所有线程执行完，CyclicBarrier是等待所有这一阶段执行完。
         *
         */


        /**
         * 执行器只需要一个，可以重复使用，最终使用后再shutdown
         * 如果需要前面执行完后，再执行后面，可以传递futureTask给执行器执行，然后用get等待执行完。
         */

        // task没有执行完的时候，get会调用wait等待，直到执行完被唤醒，返回值
        try {
            System.out.println( futureTask1.get() );
            System.out.println( futureTask2.get() );
            System.out.println( futureTask3.get() );
            System.out.println( futureTask4.get() );
            System.out.println( futureTask5.get() );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + ": Main start");

        // 创建一个cyclicBarrier对象，使线程每一个阶段都步调一致
        // 3 是同步的线程的个数，一般要与线程池的线程数相等，如果线程池少于执行要的线程，cyclicBarrier会在第一阶段一直等待，占有线程但是不释放，其他显得任务就进不来
        // 可以传入Runnable 实例，每次阶段性屏障解除后，run方法都会在对应的线程里被执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": CyclicBarrier action!");
            }
        });

        // 创建一个countDownLatch对象，倒计数3，要调用5次countDown方法
        countDownLatch = new CountDownLatch(3);


        // 传递同一个cyclicBarrier，和countDownLatch给其他线程们
        for (int i = 0; i < 3; i++) {
            pools.execute(new MyTask2(cyclicBarrier, countDownLatch, i));
        }

        // 主线程调用 countDownLatch.await，会让主线程等待这个countDownLatch对象在其他线程里倒计数为0，然后再继续执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pools.shutdown();
        }

        pools.shutdown();

        System.out.println(Thread.currentThread().getName() + ": Main end");






    }
}

/** java.util.concurrent.CountDownLatch类 **/
class MyTask1 implements Callable<String> {
    private final CountDownLatch countDownLatch;
    private final int item;


    public MyTask1(CountDownLatch countDownLatch, int item) {
        this.countDownLatch = countDownLatch;
        this.item = item;

    }

    @Override
    public String call() {
        doTask();
        // 减少countDownLatch对象的倒计数，每执行完一个线程就减少一个倒计数
        countDownLatch.countDown();
        return "done";
    }

    private void doTask() {
        System.out.println(Thread.currentThread().getName() + ": MyTask1-" + item + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": MyTask1-" + item + " end");
    }
}


/** java.util.concurrent.CyclicBarrier类 **/

class MyTask2 implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch countDownLatch;
    private final int item;


    public MyTask2(CyclicBarrier cyclicBarrierm, CountDownLatch countDownLatch, int item) {
        this.cyclicBarrier = cyclicBarrierm;
        this.countDownLatch = countDownLatch;
        this.item = item;

    }

    @Override
    public void run() {
        // 每个线程的任务分为两个阶段任务
        for (int i = 0; i < 2; i++) {
            // 执行一个阶段任务
            doPhase(i);

            try {
                // 每个阶段任务必须等待拥有cyclicBarrier实例的所有线程都执行完后，才能一起执行下一个阶段任务
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        // 减少countDownLatch对象的倒计数，每执行完一个线程就减少一个倒计数
        countDownLatch.countDown();
    }

    private void doPhase(int number) {
        System.out.println(Thread.currentThread().getName() + ": MyTask2-" + item + " Phase-" + number + " start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": MyTask2-" + item + " Phase-" + number + " end");
    }
}