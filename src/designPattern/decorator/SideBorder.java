package designPattern.decorator;

/**
 * 装饰器模式：继承一致性抽象父类，装饰器容器的一个具体实现类
 */
public class SideBorder extends Border {
    private char borderChar;
    public SideBorder(Display display, char borderChar) {
        super(display);
        this.borderChar = borderChar;
    }
    @Override
    public int getColumns() {
        return display.getColumns() + 2;
    }

    @Override
    public int getRows() {
        return display.getRows();
    }

    @Override
    public String getRowText(int row) {
        return borderChar + display.getRowText(row) + borderChar;
    }
}
