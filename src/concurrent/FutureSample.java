package concurrent;

import java.util.concurrent.*;

/**
 * Future模式：
 * 为每个请求开启一个任务线程（或者使用线程池里的工人线程），但是这个线程的执行参数 Runnable 变成了 FutureTask 或者其子类的对象，
 * 稍后可以在原调起线程使用 FutureTask 对象获取任务线程的执行结果。
 *
 * 不仅可以使用新线程来处理任务，提高效率，还能在原调起线程执行完后获取返回值。
 */


public class FutureSample {
    public static void main (String[] args) {
        /**
         * java.util.concurrent.Future<T> 接口 是ExecutorService.submit执行后的返回值
         *      有一个获取线程run方法返回值的接口：get()
         *      中止线程运行的接口：cancel()
         *
         * java.util.concurrent.ScheduledFuture<T>  接口 是ScheduledExecutorService.submit执行后的返回值
         *
         * java.util.concurrent.Callable<T> 接口
         *      有一个相当于Runnable接口的run方法的接口：call()
         *
         *      但是 call 方法有返回值（返回值类型是<T>），即任务线程的执行结果，并且会把结果设置给返回的FutureTask对象，以便FutureTask对象通过get()获取。
         *
         *      如果call执行时出错，会调用FutureTask对象的setException方法设置异常。
         *
         *
         *
         * java.util.concurrent.FutureTask<T> 实现了Future 接口和Runnable 接口的标准类
         *      创建FutureTask对象时，需要传入实现Callable接口的实现类对象。
         *      FutureTask对象需要实现Runnable 接口的run方法，但是run方法里是调用的Callable实现类的call方法
         *
         *      可以通过FutureTask对象的get()方法获取call执行的结果（<T>类型）：
         *          如果调用get的时候，还没有生成执行完的结果，FutureTask的get方法里会一直wait()，直到生成结果返回。
         *
         *          但是也有变种：
         *              或者使用Balking模式，没有生成结果就立刻返回false或者null，让主线程知道还没执行完，稍后一点再调用get获取。
         *              或者可以在任务执行的过程中反复设置返回值，反复获取返回值，比如一点一点获取图像数据。
         *
         *
         * 如果不用主线程自己稍后去调用get()获取任务执行的结果，也可以采用回调的方式，
         * 即任务执行完后，由执行任务的线程取调用主线程的方法，告诉主线程有返回结果了，并且把返回值传递给主线程。
         * 但是这样就需要主线程进行多线程的处理了（互斥处理？？），编写用于安全传递返回值的代码。
         */


        // 创建一个 FutureTask 对象，并且传入指定执行任务的Callable的实现类对象。执行器传入FutureTask 对象，结果就通过FutureTask 对象获取了。
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                return getName();
            }

            private String getName() {
                String name = null;
                try {
                    Thread.sleep(3000);
                    name = "shihongyuan";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return name;
            }
        });

        // 然后将 futureTask 作为新线程或者线程池的参数执行新线程，因为 FutureTask 是 Runnable 的实现类。
        new Thread(futureTask).start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(futureTask);

        // 向执行器传入Callable的实现类对象，并且返回Future<T>对象，通过这个Future对象获取执行的结果
        Future<String> futureTask0 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getName();
            }

            private String getName() {
                String name = null;
                try {
                    Thread.sleep(3000);
                    name = "shihongyuan";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return name;
            }
        });



        try {
            futureTask.get();//shihongyuan
            futureTask0.get();//shihongyuan
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /**
         * 执行器 引用可以是接口ExecutorService，也可以是具体类ThreadPoolExecutor，
         * 执行器 只需要一个，可以重复使用，最终使用后再shutdown
         * 如果需要前面行完后，再执行后面，可以使用户Future模式，获取futureTask对象后，然后用get等待执行完。
         */


        // 创建执行器
        ThreadPoolExecutor pools = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        // MyCallable 实现了 Callable<String>
        MyCallable myCallable = new MyCallable();

        // 传入 Callable 对象，返回Future<T>对象，通过Future<T>对象获取返回值
        Future<String> futureTask1 = pools.submit(myCallable);

        // 传入 Runnable 对象和返回值，直接通过result获取返回值，好像不对。。。。。。
        String result = null;
        Future<String> futureTask2 = pools.submit(new FutureTask(myCallable), result);


        // 传入 FutureTask 对象，通过FutureTask对象获取返回值
        FutureTask<String> futureTask3 = new FutureTask(myCallable);
        pools.submit(futureTask3);

        try {
            System.out.println( futureTask1.get() );//done
            System.out.println( result );//null
            System.out.println( futureTask2.get() );//null
            System.out.println( futureTask3.get() );//done
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pools.shutdown();
        }

    }

    /**
     * 工具类Executors可以实现Runnable对象和Callable对象之间的相互转换
     * Executors.callable（Runnable task）或Executors.callable（Runnable task，Object result）
     */

    /**
     * 执行execute()方法和submit()方法的区别是什么呢？
     *  1)execute() 方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；
     *  2)submit() 方法用于提交需要返回值的任务。线程池会返回一个Future类型的对象，通过这个Future对象可以判断任务是否执行成功，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用 get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。
     */

}

class MyCallable implements Callable<String> {

    @Override
    public String call() {
        return "done";
    }
}

