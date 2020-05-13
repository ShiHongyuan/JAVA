package designPattern.flyweight;

import java.util.HashMap;

/**
 * 为了轻量级共享对象模式：让被共享的对象实现共享的方法，使用者从工厂类获取被共享的对象
 */
public class BigCharFactory {
    private HashMap pool = new HashMap();
    private static BigCharFactory singleton = new BigCharFactory();
    private BigCharFactory () {

    }

    public static BigCharFactory getInstance () {
        return singleton;
    }

    // 外部使用者获取共享对象调用的方法
    // 如果不加 synchronized 关键字，可能多线程的情况下，会多个线程都生成相同的 BigChar，虽然 pool里只会放最后生成的 BigChar，
    // 也不会对程序的运行结果产生什么影响，但是会浪费java堆资源一会。
    public synchronized BigChar getChar (char charname) {
        BigChar bigChar = (BigChar) pool.get(charname);
        if (bigChar == null) {
            bigChar = new BigChar(charname);
            pool.put(charname, bigChar);
        }
        return bigChar;
    }
}
