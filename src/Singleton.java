/**
 * 单例模式的好处：
 * 1、具有延迟初始化的好处
 * 2、由 JVM 提供了对线程安全的支持
 **/
public class Singleton {
    // 声明为 private 避免调用默认构造方法创建对象
    private Singleton() {
    }

    // 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
    // 当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // 只有第一次当调用 getUniqueInstance()方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载， 此时初始化 INSTANCE 实例
    // 第二次调用时，final的INSTANCE直接返回，所以 JVM 能确保 INSTANCE 只被实例化一次，即Singleton只能创建一个实例
    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }

}