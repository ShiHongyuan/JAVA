package designPattern.bridge.function;

import designPattern.bridge.impl.DisplayImpl;

/**
 * 桥梁模式：操作实现类的功能，实现了一些基本功能，属于功能层次。
 */
public class Display {
    private DisplayImpl impl;
    public Display (DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}
