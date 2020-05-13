package designPattern.singleton;

public class Singleton2 {
    private static Singleton2 singleton2 = null;
    private Singleton2() {
        System.out.println("生成了一个实例");
        // 特意放慢 new Singleton2(); 的过程，多线程可能会造成 getInstance 生成不同的实例
        slowdown();
    }

    // 不加 synchronized，多线程可能会造成 getInstance 生成不同的实例
    // 加了 synchronized，防止多线程生成不同的实例
    public synchronized static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }

    private void slowdown() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
