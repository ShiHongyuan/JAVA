package designPattern.composite;

/**
 * 容器模式：叶子和容器的一致性，叶子和容器都继承自抽象类Entry，都是Entry，所以是一致的，容器添加的对象也是Entry，所以容器既可以添加Entry，也可以作为Entry被添加
 */
public abstract class Entry {
    protected Entry parent;

    public abstract String getName();
    public abstract int getSize();


    // 让抽象方法实现为抛出异常，如果子类没有重写 add 方法，则会抛出异常
    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }

    public void printList() {
        printList("");
    }

    protected abstract void printList(String prefix);

    public String getAbsoluteName() {
        StringBuffer buffer = new StringBuffer();

        Entry entry = this;
        do {
            buffer.insert(0, "/" + entry.getName());
            entry = entry.parent;
        } while (entry != null);

        return buffer.toString();
    }

}
