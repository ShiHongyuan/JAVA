package designPattern.templateMethod;

/**
 * 模板方法的具体类，具体实现方式是char
 */
public class CharDisplay extends AbstractDisplay{
    private char ch;
    public CharDisplay (char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
