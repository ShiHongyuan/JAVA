package designPattern.visitor;

/**
 * 访问者器模式：被访问 Element的具体实现类中的其中一个，数据结构是不能遍历的叶子元素，调用accpet（Visitor v）直接被访问者访问并处理
 */
public class File extends Entry{
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
