package designPattern.decorator;

/**
 * 装饰器模式：继承一致性抽象父类，装饰器容器的一个具体实现类
 */
public class UpDownBorder extends Border {
    private char upDownChar;
    public UpDownBorder(Display display, char upDownChar) {
        super(display);
        this.upDownChar = upDownChar;
    }
    @Override
    public int getColumns() {
        return display.getColumns();
    }

    @Override
    public int getRows() {
        return display.getRows() + 2;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0 || row == getRows() -1) {
            return makeLine(upDownChar, getColumns());
        } else {
            return display.getRowText(row - 1);
        }
    }

    private String makeLine(char ch, int count) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
