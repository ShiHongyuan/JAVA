package concurrent;

/**
 * Double-Check Lock ：双重检验锁方式，通常用语实现单例模式
 */
public class DoubleCheckLock {

}

class Singleton {

    /**
     * instance 采用 volatile 关键字修饰也是很有必要的。
     *
     * instance = new Singleton(); 这段代码其实是分为三步执行：
     *  1. 为 instance 分配内存空间
     *  2. 初始化 instance
     *  3. 将 instance 指向分配的内存地址
     *
     *  但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
     *  指令重排在单线程环境下不会出先问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
     *  例如，线程 T1 执行了 1 和 3，此时 T2 调用 getInstance() 后发现 instance 不为空，
     *  因此返回 instance，但此时 instance 还未被初始化。
     *
     *  使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     *
     */



    private volatile static Singleton instance; //

    private Singleton() {
    }

    public static Singleton getInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (instance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
