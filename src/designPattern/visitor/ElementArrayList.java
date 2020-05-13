package designPattern.visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class ElementArrayList extends ArrayList implements Element {

    @Override
    public void accept(Visitor v) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            ((Element) it.next()).accept(v);
        }

    }
}
