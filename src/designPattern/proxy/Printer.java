package designPattern.proxy;

/**
 * 只在必要时生成真正实例的代理模式：被代理对象代理的背后的真正实例对象
 */
public class Printer implements Printable {
    private String name;
    public Printer() {
        heavyJob("Printer 的实例生成中");
    }
    public Printer(String name) {
        this.name = name;
        heavyJob("Printer 的实例生成中（" + name + ")");
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    // 打印带打印机名字的文字
    @Override
    public void print (String string) {
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }

    private void heavyJob (String msg) {
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.print(".");
        }
        System.out.println("结束。");
    }
}
