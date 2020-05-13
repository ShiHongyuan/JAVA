package designPattern.templateMethod;

/**
 * 模板方法的具体类，具体实现方式是String
 */
public class StringDisplay extends AbstractDisplay{
    private String string;
    private int width;
    public StringDisplay(String string) {
        this.string = string;
        // 汉字三个byte为一个汉字，如果是string.length()，一个长度为一个汉字
        this.width = string.getBytes().length;
    }


    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|" + string +"|");
    }

    @Override
    public void close() {
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
