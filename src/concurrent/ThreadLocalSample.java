package concurrent;


/**
 * ThreadLocal<T>
 *      利用线程名（Thread.currentThread()的值）作为hash键，为每个线程提供局部存储空间，存储线程的私有对象
 *      ThreadLocal<T> 是泛型对象，T是保存的局部对象
 *
 *      所以 ThreadLocal 就是用来避免这多线程数据竞争的。
 */
public class ThreadLocalSample {
    /**
     * 加入多线程之后，每个线程内部就像主程序单线程模式一样使用对象及其方法。
     *
     * 被使用的对象内部持有一个 ThreadLocal 对象，ThreadLocal 有点像一个哈希表，key是当前线程的线程名，value是当前线程自己的局部对象。
     *
     * 如果不使用 ThreadLocal 来保存线程的局部对象，多线程都使用一个对象，就需要进行互斥处理，可能降低性能，而且还很容易出错（synchronized加的不全面，或者引发死锁等）。
     *
     * 如果现在手头有一个单线程运行的程序，要改为多线程，就用ThreadLocal模式，不需要修改现有的线程程序，也不需要修改被线程调用的对象的API。
     *
     * ThreadLocal模式就是：让被调用对象使用 ThreadLocal 对象获取与当前线程对应的局部对象，并且将处理委托给局部对象。
     *
     * 所以 ThreadLocal模式不能和一个请求创建一个线程的模式结合，是完全两种不同的思想。
     *
     * threadLocal.get() 获取当前线程对应的对象，如果不存在，就返回null
     *
     * threadLocal.set(obj); 设置当前线程对应的对象
     *
     * threadLocal.remove(); 删除与当前线程对应的值。
     */

    // 原线程ClientThread， 原被调用的对象ObjSample
    static class ClientThread extends Thread {
        private int value;
        public ClientThread(String name, int value) {
            super(name);
            this.value = value;
        }

        @Override
        public void run() {
            ObjSample objSample = new ObjSample();
            objSample.funSample(value);
        }
    }

    // 原被调用的对象ObjSample
    static class ObjSample {
        ThreadLocal<TSObjSample> threadLocal = new ThreadLocal();

        public void funSample(int value) {
            this.getTSObjSample().funSample(value);
        }

        private TSObjSample getTSObjSample() {
            TSObjSample tsObjSample = threadLocal.get();
            if (tsObjSample == null) {
                tsObjSample = new TSObjSample();
                threadLocal.set(tsObjSample);
            }
            return tsObjSample;
        }
    }

    // 保存的局部对象， TS：thread special
    // 虽然多线程同时修改value值，但是多线程并不是共享一个对象，而是各自私有一个实例，各自修改各自的，不会串
    static class TSObjSample {
        int value;
        public void funSample(int value) {
            this.value = value;
            System.out.println(Thread.currentThread().getName() + " : " + this.value);
            //Chris : 3
            //Bobby : 2
            //Alice : 1
        }
    }

    public static void main (String[] args) {
        // 接收请求，创建线程
        new ClientThread("Alice", 1).start();
        new ClientThread("Bobby", 2).start();
        new ClientThread("Chris", 3).start();
    }
}

