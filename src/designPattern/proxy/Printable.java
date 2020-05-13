package designPattern.proxy;

/**
 * 只在必要时生成真正实例的代理模式：代理对象和真正对象共同的接口，
 *                           正因为它们都是这个接口的实现类，所以代理对象才可以代替真正对象执行一些方法。
 *
 *                           这个接口下可以实现很多个真正对象和一个代理对象，通过不同的真正对象的类名来创建代理对象，
 *                           同一个代理对象就可以代理很多个真正对象了，实现了代理对象的独立性和组件可复用性。
 */
public interface Printable {
    public abstract void setPrinterName (String name);
    public abstract String getPrinterName ();
    public abstract void print (String string);
}
