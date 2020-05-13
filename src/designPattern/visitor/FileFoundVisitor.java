package designPattern.visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class FileFoundVisitor extends Visitor {
    private String suffix;
    private ArrayList foundFiles = new ArrayList();

    public FileFoundVisitor (String suffix) {
        this.suffix = suffix;
    }

    public Iterator getFoundFiles () {
        return foundFiles.iterator();
    }

    @Override
    public void visit(File file) {
        if (file.getName().endsWith(suffix)) {
            foundFiles.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            ((Element)it.next()).accept(this);
        }
    }
}
