package concurrent;

/**
 * Thread是执行工作的人，Runnable是要被执行的工作内容
 * Thread 也实现了 Runnable 接口，也实现了 Runnable的 run方法，但是方法体的实现是空的，需要 Thread的子类重写 run方法。
 *
 * Thread 的静态方法：Thread.sleep(100); 暂停当前线程
 *                  Thread.currentThread() 当前线程的实例对象
 *                  Thread.currentThread().getName() 当前线程实例的名字
 *                  Thread.yield(); 调度线程暂时从运行状态为就绪状态，不获取锁。
 *                  Thread.getState(); 获取线程的状态：java中线程状态有6个 （创建、运行中、阻塞、等待、超时等待、终止）
 *                  Thread.interrupted(); 获取线程的中断状态，只有2个，中断和非中断，并清除中断状态。
 *                  Thread.holdsLock(Object obj); 判断当前线程是否持有obj的实例锁。
 *
 * Thread实例的方法（t为某一个线程实例）：t.join(); 当前线程（调用方法的线程）等待指定的线程结束后再执行下面的。
 *                                 t.isAlive(); 指定线程是否已经终止。
 *                                 t.interrupt(); 中断指定线程。
 *                                 t.isInterrupted(); 获取指定线程的中断状态，不改变中断状态。
 *
 *                                 t.isDaemon(); 指定线程是否是守护线程。
 *                                 t.setDaemon(true); 设置指定线程为守护线程，如果该线程已经启动，则抛出异常IllegalThreadStateException。
 *                                 t.setDaemon(false); 取消指定线程为守护线程。
 *
 *                                 t.getId(); 指定线程的ID，运行时唯一，终止后可重复使用。
 *                                 t.getName(); 指定线程的名字。
 *                                 t.setName(String); 设置指定线程的名字。
 *                                 t.getPriority(); 指定线程的优先级。
 *                                 t.setPriority(int); 设置指定线程的优先级。
 *
 *                                 t.start(); 开启指定线程，如果已开启，会抛出异常IllegalThreadStateException。
 *                                 t.stop(); 终止指定线程，已经不推荐使用。
 *
 *
 *
 * Thread 创建：
 *      1. 第一种方式：继承Thread 的子类，重写run 方法
 *      2. 第一种方式：创建 Thread及其子类时，构造函数中传入实现Runnable接口的实现类，该实现类重写了run 方法
 *
 * 线程启动：
 *      thread的 start方法，直接调用run方法，并不能开启新线程，必须调用start方法，才会开启新线程，并且由start方法去调用run方法。
 *
 *
 * 实现Runnable接口和Callable接口的区别？
 *      两者都可以让线程执行任务，但是两者的区别在于 Runnable 接口不会返回结果但是 Callable 接口可以返回结果。
 */
public class ThreadSample {
    public static void main (String[] args) {

        /**
         * 创建线程对象
         */
        // ********** 1. 第一种方式：继承Thread 的子类，重写run 方法
        // Thread的子类是匿名类的方式
        new Thread() {
            @Override
            public void run() {

            }
        }.start();
        // 先继承子类，再创建子类对象的方式

        // ********** 2. 第一种方式：创建 Thread及其子类时，构造函数中传入实现Runnable接口的实现类，该实现类重写了run 方法
        // Runnable的实现类是匿名类的方式
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        // 先实现Runnable，再作为参数的方式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        new Thread(runnable).start();


        /**
         * start方法：
         *      一个线程对象的实例只能调用一次 start 方法。
         *      Thread类（及其子类）调用了start后，实例的状态会变为 "start终止" 状态。即Thread.getState() 的值不为 Thread.State.NEW 了。
         *      当再次对同一个实例调用 start方法时，为防止线程再次启动，线程状态不为 Thread.State.NEW 时，会直接返回，并抛出 IllegalThreadStateException 异常。
         */
        // 第二次调用start，会抛异常
        Thread thread = new Thread();
        thread.start();
        try{
            thread.start();
        } catch (IllegalThreadStateException  e) {
            e.printStackTrace(); // 抛出了异常 java.lang.IllegalThreadStateException
        }

        /**
         * 线程当前的状态枚举变量
         */
        thread.getState();

    }
}
