package designPattern.proxy;

/**
 * 只在必要时生成真正实例的代理模式：代理对象
 */
public class PrinterProxy implements Printable {
    private String name;
    private Printable real;
    private String classname;

    public PrinterProxy () {}
    public PrinterProxy (String name, String classname) {
        this.name = name;
        this.classname = classname;
    }

    // 添加 synchronized 关键字，是和 realize 的 synchronized 配合使用的。
    // 如果多线程一个在执行setPrinterName，一个在执行realize，setPrinterName 还没执行完，
    // 切换到realize线程，用原来名字生成了一个真正实例，在切换到setPrinterName线程，更改了代理的名字，
    // 就造成了代理和真正实例的名字不一致的现象。所以都用 synchronized，分开了设置名字时判断real字段的处理和生成real字段的处理。
    @Override
    public synchronized void setPrinterName(String name) {
        if (real != null) {
            real.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    // 只有在需要真正的实例执行 print时，代理才调用真正实例的方法 （如果真正的实例还未生成过，就先生成真正的实例）
    @Override
    public void print(String string) {
        realize();
        real.print(string);
    }

    // 如果真正的实例还未生成过，就先生成真正的实例
    // 添加 synchronized 关键字，是和 setPrinterName 的 synchronized 配合使用的。
    // 如果多线程一个在执行setPrinterName，一个在执行realize，setPrinterName 还没执行完，
    // 切换到realize线程，用原来名字生成了一个真正实例，在切换到setPrinterName线程，更改了代理的名字，
    // 就造成了代理和真正实例的名字不一致的现象。所以都用 synchronized，分开了设置名字时判断real字段的处理和生成real字段的处理。
    private synchronized void realize () {
        if (real == null) {
            try {
                // 代理就可以从具体实例 Printer中分离出来，作为独立的可复用组件使用，
                // 而且只要是实现了 Printable接口的具体实例都可以扮演代理背后真正实例的角色

                // 没有getConstructor()，只有 newInstance()在没有默认构造函数的情况下也会去调用无参构造函数。
                real = (Printable) Class.forName(classname).newInstance();
                real.setPrinterName(name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
