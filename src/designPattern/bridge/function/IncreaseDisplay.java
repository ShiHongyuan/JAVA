package designPattern.bridge.function;

import designPattern.bridge.impl.DisplayImpl;

public class IncreaseDisplay extends CountDisplay{
    public IncreaseDisplay(DisplayImpl impl) {
        super(impl);
    }

    // step是每一层增加打印的字符数，level是要打印的层数
    public void increaseDisplay(int step, int level) {
        int count = 0;
        for (int i = 0; i < level; i++) {
            multiDisplay(count);
            count += step;
            System.out.println();
        }
    }




}
