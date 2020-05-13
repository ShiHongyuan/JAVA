package designPattern.proxy;

/**
 * 只在必要时生成真正实例的代理模式：代理对象不是真正的实例对象，只是真正实例的代理。
 *                            代理是指代替别人进行工作的人。
 *
 *                            代理背后真正实例可能创建很耗费资源，创建很费力气，
 *                            所以能够由代理代劳的就让代理做了，除非真的有什么请求必须由真正实例自己处理，
 *                            代理再创建真正实例去处理。
 *
 *                            传递代理背后真正实例的类名给代理对象，代理对象在需要的时候再根据类名创建相应的实例，
 *                            代理就可以从具体实例 Printer中分离出来，作为独立的可复用组件使用，
 *                            而且只要是实现了 Printable 接口的具体实例都可以扮演代理背后的真正实例的角色。
 *
 *                            代理对象和真正对象实现共同的一个接口，这个接口下可以实现很多个真正对象和一个代理对象，
 *                            通过不同的真正对象的类名来创建代理对象，同一个代理对象就可以代理很多个真正对象了，
 *                            实现了代理对象的独立性和组件可复用性。
 */
public class Main {
    public static void main (String[] args) {
        Printable p = new PrinterProxy("Alice", "designPattern.proxy.Printer");

        // 这里执行的是代理对象的方法
        System.out.println("打印机现在的名字是 " + p.getPrinterName() + "。");
        p.setPrinterName("Bob");
        System.out.println("打印机现在的名字是 " + p.getPrinterName() + "。");

        // 这里执行的是生成真正的实例对象，调用真正实例的方法
        p.print("Hello, world.");
    }
}
