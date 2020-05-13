package concurrent;

import java.util.concurrent.*;

/**
 * 来一个请求，就交给一个线程处理，当前线程立马返回，降低响应时间，实现异步消息的发送
 *
 * 两种方式：
 *      1. 一个请求启动一个新的线程，线程处理完就退出
 *      2. 线程处理后不退出，一直等待，可以复用空闲线程
 *
 * 下面主要讲创建线程的方式。
 *
 *
 * 总结一下：
 *      java.util.concurrent.Executors类:
 *      创建ThreadFactory、Executor、ExecutorService、ScheduledExecutorService、Callable 实例的工具类。
 *          1. java.lang.Thread类: 最基本的创建、启动线程的类
 *          2. java.lang.Runnable接口: 表示线程所执行"工作"的接口
 *          3. java.util.concurrent.ThreadFactory接口: 将线程创建抽象化了的接口
 *          4. java.util.concurrent.Executor接口: 将线程创建和执行都抽象化了的接口
 *          5. java.util.concurrent.ExecutorService接口: 继承自Executor接口，将复用线程抽象化的接口
 *          6. java.util.concurrent.ScheduledExecutorService接口: 继承自Executor接口，将可复用并且可设定调度时间的线程抽象化的接口
 *
 *          以上4，5，6都有execute和submit方法执行runnable或者callable，
 *          但是执行execute()方法和submit()方法的区别是什么呢？
 *              1)execute() 方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；
 *              2)submit() 方法用于提交需要返回值的任务。线程池会返回一个Future类型的对象，通过这个Future对象可以判断任务是否执行成功，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用 get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。
 */
public class ExecutorsUtilClass {
    public static void main (String[] args) {
        /**
         *  java.util.concurrent.Executors工具类，
         *          可以通过静态方法创建很多执行器
         *          execute(Runnable) 方法，执行Runnable的run方法
         *          submit(Callable<T>)方法，执行Callable<T>的call方法，并且返回Future<T>
         *          submit(Runnable)方法，执行Runnable的run方法，并且返回Future<T>
         *
         *  以下是可以由Executors创建的对象。
         */

        /**
         * java.util.concurrent.ThreadFactory接口 创建线程对象：
         *      用 threadFactory.newThread 获取线程对象，隐藏创建Thread的细节，
         *      因为 ThreadFactory 有不同的具体子类，用不同的 Factory 可以控制线程不同的创建细节
         *
         *      比用传统的new Thread创建对象要灵活，因为创建不同的线程对象，只需要替换ThreadFactory的具体类就可以，
         *      所以ThreadFactory具有组建的复用性。
         *
         */
        // ********* 最简单的一个ThreadFactory的实现
        new ThreadFactory()  {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        };

        // *********  用 java.util.concurrent.Executors类 获取最简单的默认设置的 ThreadFactory 接口的实例
        ThreadFactory threadFactory = Executors.defaultThreadFactory();// 单例？
        // threadFactory 只有一个以 Runnable 为参数，执行实例的newThread，创建线程对象
        threadFactory.newThread(new Runnable() { // 等同于new Thread(Runnable).start()
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Nice!");
                }
            }
        }
        // 这里是执行方法，先new后点；如果是强制转换，先点后()
        ).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Good!");
                }
            }
        };
        Thread thread = threadFactory.newThread(runnable);


        /**
         * java.util.concurrent.Executor接口 创建线程并开始执行线程
         *      用 executor.execute 执行新线程，隐藏创建并执行线程的细节，隐藏了线程的存在，
         *      是不是创建了一个Thread来执行的都不知道，更具有封装性
         *
         */
        // ********* 最简单的一个 executor.execute 方法的实现
        new Executor(){
            @Override
            public void execute (Runnable runnable) {
                new Thread(runnable).start();
            }
        };
        // ********* 实现了 Executor接口 方法的匿名实现类，并引用给executor声明
        final Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(runnable).start();
            }
        };
        // 以一个 Runnable 为参数，执行 execute方法，创建线程对象并执行线程
        executor.execute(runnable);

        /***********************  线程池（线程的执行器）  减少不断启动新线程的开销  ***************************/
        /**
         * 如果是固定线程数量的线程池，执行的请求超出了线程池的数量，发起请求的线程会等待wait，
         * 直到有空闲线程执行或者请求超时返回。相当于生产者消费者模式
         */


        /**
         * java.util.concurrent.ExecutorService接口 创建可复用线程池对象
         *
         *      以下实际上都是调用的 ThreadPoolExecutor类 的构造函数，都是ThreadPoolExecutor的实例。
         *      1. Executors.newCachedThreadPool(): 不用开发人员设定线程池初始化的线程数，只要有空闲线程就用空闲线程，没有就创建并启动新的线程。
         *         空闲线程会在缓存60s后自动终止。允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM
         *
         *      2. Executors.newFixedThreadPool(int): 需要在线程池初始化时设定线程数，在线程池初始化时一起创建，是固定运行的线程数。如果没有空闲的线程，就先把请求放到一个同步的等待队列中。
         *          允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
         *
         *      3. Executors.newSingleThreadExecutor(): 创建只有一个线程的线程池，请求来了如果没有空闲，就先把请求放到一个同步的没有长度限制的等待队列中。
         *          允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
         *
         *      因为是复用空闲线程，线程执行完一次后不会退出，会一直运行着，
         *      所以程序最后需要用ExecutorService接口的 shutdown 方法关闭所有已创建的线程。
         *
         *      isShutdown() 用于确认是否已经调用 shutdown方法
         *      isTerminated() 用于确认是否已经实际停止了
         *
         *      创建线程池时传入 ThreadFactory 参数，就是指定用这个线程工厂类来创建线程对象。
         *
         * java.util.concurrent.AbstractExecutorService类，是ExecutorService接口的默认实现类。
         * java.util.concurrent.ThreadPoolExecutor类，实现了ExecutorService的接口，也是AbstractExecutorService的子类
         *
         */

        // new一个ThreadPoolExecutor类的实例

        //《阿里巴巴Java开发手册》中强制线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
        // FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
        // CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。
        BlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>();
        runnables.add(runnable);
        runnables.add(runnable);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,5,10L,TimeUnit.SECONDS, runnables);

        /**
         * 执行器 声明的引用可以是接口ExecutorService，也可以是具体类ThreadPoolExecutor，
         * 执行器 只需要一个，可以重复使用，最终使用后再shutdown
         * 如果需要前面行完后，再执行后面，可以使用户Future模式，获取futureTask对象后，然后用get等待执行完。
         */

        // ******************  Executors.newCachedThreadPool
        // 使用 Executors类 获取最简单的缓存60s的 ExecutorService 接口的实例ThreadPoolExecutor，用接口引用
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 执行 executorService.execute 方法，以 Runnable为参数，创建或者执行线程
        executorService.execute(runnable);
        // 执行 shutdown 关闭 executorService所有已创建的线程。
        // 为了确保最后一定关闭，不消耗资源，一般用before/after方法
        try {
            executorService.execute(runnable);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable2");
                }
            });
        } finally {
            executorService.shutdown();
        }


        // ******************  Executors.newSingleThreadExecutor
        // 使用 Executors类 获取只有一个线程的线程池
        // 从多态性上看，ThreadPoolExecutor是AbstractExecutorService的子类，所以ThreadPoolExecutor的实例也是AbstractExecutorService的实例
        AbstractExecutorService abstractExecutorService = (AbstractExecutorService) Executors.newSingleThreadExecutor();
        // 执行 executorService.execute 方法，以 Runnable为参数，创建或者执行线程
        abstractExecutorService.execute(runnable);
        // 关闭线程池对象
        abstractExecutorService.shutdown();




        // ******************  Executors.newFixedThreadPool
        // 使用 Executors类 获取最简单的固定线程数的 ExecutorService 接口的实例ThreadPoolExecutor，用实例类引用 ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 执行 executorService1.execute 方法，以 Runnable为参数，创建或者执行线程
        threadPoolExecutor1.execute(runnable);
        // 执行 shutdown 关闭 executorService1所有已创建的线程。
        // 为了确保最后一定关闭，不消耗资源，一般用before/after方法
        try {
            threadPoolExecutor1.execute(runnable);
            threadPoolExecutor1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable22");
                }
            });
        } finally {
            threadPoolExecutor.shutdown();
        }



        /**
         * java.util.concurrent.ScheduledExecutorService接口  创建可复用线程池，并且可设定Runnable延迟执行的时间
         *      需要在线程池初始化时设定线程数，工作时一直保持着这个线程数。
         *
         *      线程池线程一直运行着，所以也需要在程序最后调用  shutdown 关闭所有线程。
         *
         *      创建线程池时传入 ThreadFactory 参数，就是指定用这个线程工厂类来创建线程对象。
         *
         * java.util.concurrent.ScheduledThreadPoolExecutor类：是ScheduledExecutorService接口的实现类
         *
         *
         */
        // 使用 Executors类 获取最简单的默认的 ScheduledExecutorService的实例ScheduledThreadPoolExecutor，并且指定线程池的线程数为 5 个线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)scheduledExecutorService;

        // 执行 scheduledExecutorService.schedule 方法，以 Runnable，延迟时间，延迟时间单位 为参数，创建或者执行线程
        // 请求到达5秒后再执行
        scheduledExecutorService.schedule(runnable, 5L, TimeUnit.SECONDS );

        // 执行 shutdown 关闭 scheduledExecutorService所有已创建的线程。
        // 为了确保最后一定关闭，不消耗资源，一般用before/after方法
        try {
            scheduledExecutorService.schedule(runnable, 5, TimeUnit.SECONDS );
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable2");
                }
            }, 3L, TimeUnit.SECONDS);
        } finally {
            scheduledExecutorService.shutdown();
        }





    }
}
