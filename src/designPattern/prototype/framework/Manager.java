package designPattern.prototype.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型模式的使用者
 * register：注册后面可能需要复制的原型实例
 * create：通过原型名获取原型实例并复制一个相同的实例返回
 */
public class Manager {
    private Map<String, Product> showcase = new HashMap<>();

    // 把可能后面会需要复制的实例通过名字注册到 Manager上
    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }
    // 需要复制的时候，通过名字在 Manager上 找到相应的注册的实例，复制后返回
    public Product create(String protoname) {
        Product p = showcase.get(protoname);
        return p.createClone();
    }
}
