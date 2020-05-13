package designPattern.adapter;

/**
 * 被适配的类
 * 被适配的方法：showWithParen、showWithAster
 */
public class Banner {
    private String string;
    public Banner (String string) {
        this.string = string;
    }
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
