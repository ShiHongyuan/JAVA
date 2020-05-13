package designPattern.bridge.function;

import designPattern.bridge.impl.DisplayImpl;

import java.util.Random;

/**
 * 桥梁模式：在基本操作功能上，新增加了一个循环打印随机次数的功能，属于功能层次。
 */
public class RandomDisplay extends Display {
    public RandomDisplay(DisplayImpl impl) {
        super(impl);
    }

    // 增加新功能，循环显示随机的 [0~times) 次
    public void randomDisplay(int times) {
        int time = new Random().nextInt(times); // 获取 [0~times) 的随机整数
        open();
        for (int i = 0; i < time; i++) {
            print();
        }
        close();
    }
}
