package designPattern.singleton;

/**
 * 单例
 */
public class Singleton {
    // static 的 singleton，在类加载的时候只生成一次实例，后面不会生成新的实例了，所以是单例
    private static Singleton singleton = new Singleton();

    // 构造函数是private的，外部类创建不了实例
    private Singleton() {
        System.out.println("生成了一个实例");
    }

    // public 的 getInstance，让其他类能获取到该类唯一的实例
    public static Singleton getInstance() {
        return singleton;
    }
}
