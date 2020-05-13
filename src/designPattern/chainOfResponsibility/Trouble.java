package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：问题具体实现类
 */
public class Trouble {
    private int number;
    public Trouble(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    @Override
    public String toString() {
        return "[Trouble " + number + "]";
    }
}
