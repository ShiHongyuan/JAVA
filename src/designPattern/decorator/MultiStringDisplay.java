package designPattern.decorator;

import java.util.ArrayList;

/**
 * 装饰器模式：继承一致性抽象父类，最简单的单行字符串的显示，叶子的一个具体实现类
 */
public class MultiStringDisplay extends Display {
    private ArrayList strings = new ArrayList();
    private int columns = 0;

    public void add(String s) {
        strings.add(s);
        updateColumn(s);
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return strings.size();
    }

    @Override
    public String getRowText(int row) {
        if (!strings.isEmpty()) {
            return (String) strings.get(row);
        } else {
            return null;
        }
    }

    private void updateColumn(String s) {
        if (s.getBytes().length > columns) {
            columns = s.getBytes().length;
        }
        for (int i = 0; i < strings.size(); i++) {
            int fills = columns - ((String) strings.get(i)).getBytes().length;
            if (fills > 0) {
                strings.set(i, (strings.get(i) + spaces(fills)));
            }
        }
    }

    private String spaces(int count) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buffer.append(' ');
        }
        return buffer.toString();
    }
}
