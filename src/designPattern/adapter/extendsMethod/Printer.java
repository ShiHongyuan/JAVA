package designPattern.adapter.extendsMethod;

import designPattern.adapter.Banner;

/**
 * 继承被适配类的方式：
 * 适配器类：继承目标接口和被适配器类
 *         实现了目标接口 Print的 printWeak 和 printStrong方法
 *         继承了被适配器类 Banner的 showWithParen 和 showWithAster 方法
 */
public class Printer extends Banner implements Print{
    public Printer(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
