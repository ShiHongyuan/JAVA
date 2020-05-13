package designPattern.bridge.function;

import designPattern.bridge.impl.DisplayImpl;

/**
 * 桥梁模式：在基本操作功能上，新增加了一个循环打印指定次数的功能，属于功能层次。
 */
public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    // 增加新功能，循环显示指定的times次
    public void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
