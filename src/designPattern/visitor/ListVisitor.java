package designPattern.visitor;

import java.util.Iterator;

/**
 * 访问者器模式：访问者 Visitor的具体实现类，实现了访问叶子元素的方法，也实现了访问数据结构是遍历性质的被访问者的方法
 */
public class ListVisitor extends Visitor {
    private String currentdir = "";
    @Override
    public void visit(File file) {
        System.out.println(currentdir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.accept(this);
        }
        currentdir = savedir;
    }
}
