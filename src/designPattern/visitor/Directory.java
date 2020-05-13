package designPattern.visitor;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * 访问者器模式：被访问 Element的具体实现类中的其中一个，其具有遍历的数据结构，会被访问者遍历元素并调用 accpet 再不断被访问
 */
public class Directory extends Entry {
    private String name;
    private ArrayList directory = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        directory.add(entry);
        return entry;
    }

    public Iterator iterator() {
        return directory.iterator();
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
