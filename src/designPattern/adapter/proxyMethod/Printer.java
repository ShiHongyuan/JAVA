package designPattern.adapter.proxyMethod;

import designPattern.adapter.Banner;

/**
 * 委托被适配类的方式：
 * 适配器类：持有被适配类的实例对象作为字段
 *         继承了目标类 Print 的printWeak 和 printStrong方法，它们分别调用被适配类的实例对象的 showWithParen 和 showWithAster 方法
 */
public class Printer extends Print{
    private Banner banner;
    public Printer (String string) {
        banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
