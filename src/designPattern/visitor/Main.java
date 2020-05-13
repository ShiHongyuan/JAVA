package designPattern.visitor;


import java.util.Iterator;

/**
 * 访问者模式：访问数据结构并对数据结构的元素进行处理的访问者模式。
 *
 *          对扩展示开放的，对修改是关闭的，就是说在扩展类的时候要在不用修改现有类的前提下取扩展类，
 *          就是说如果把数据结构和它的处理类分来，对同一个数据结构有很多种处理方法，每一种处理方法都是一个类的话，即使增加处理类，也不会修改已有的数据结构。
 *          这样就提高了不同的被处理类和处理类的组件独立性和可复用性。
 *
 *          怎样将被处理类（数据结构类）和处理类分开呢？
 *
 *          被处理类们有一个共同的接口 Element，里面定义的accept(Visitor v)方法接收任意继承于Visitor接口的处理类实例，
 *          在方法里利用处理类实例调用其 v.visit(this) 方法，处理自己。
 *
 *          处理类们有一个共同的接口 Visitor，里面定义了接收各种被处理类实例作为实参的 visit(Element e)方法，它会去处理传进来的被处理类，至于怎么处理就是看具体的处理类怎么实现的了。
 *          如果传进来的被处理类是一个叶子性质的元素，在visit 内部直接对元素进行处理，
 *          如果传进来的被处理类是一个具有遍历性质的数据结构，在visit 内部需要通过被处理类的 Iterator 获取每一个元素并调用元素的 accept方法，里面再调用元素的 visit方法进行处理。
 *
 *          所以被处理的数据结构的实现类和处理实现类，是相互调用的关系。
 *
 */
public class Main {
    public static void main (String[] args) {
        try {

            System.out.println("Making root entries...");
            Directory rootdir = new Directory("root");
            Directory bindir = new Directory("bin");
            Directory tmpdir = new Directory("tmp");
            Directory usrdir = new Directory("usr");
            rootdir.add(bindir);
            rootdir.add(tmpdir);
            rootdir.add(usrdir);
            bindir.add(new File("vi", 10000));
            bindir.add(new File("latex", 20000));

            // 通过ListVictor遍历输出 Directory 包含的目录和文件路径
            rootdir.accept(new ListVisitor());

            System.out.println("");


            System.out.println("Making user entries...");
            Directory yuki = new Directory("yuki");
            Directory hanako = new Directory("hanako");
            Directory tomura = new Directory("tomura");
            usrdir.add(yuki);
            usrdir.add(hanako);
            usrdir.add(tomura);
            yuki.add(new File("diary.html", 100));
            File file = new File("Composite.java", 200);
            yuki.add(file);
            hanako.add(new File("memo.tex", 300));
            tomura.add(new File("game.doc", 400));
            tomura.add(new File("junk.mail", 500));

            rootdir.accept(new ListVisitor());

            // 通过SizeVictor获取 Directory的大小
            SizeVisitor sizeVisitor = new SizeVisitor();
            rootdir.accept(sizeVisitor);
            System.out.println(sizeVisitor.getSize());

            // 测试 ElementArrayList
            ElementArrayList list = new ElementArrayList();
            list.add(bindir);
            list.add(tmpdir);
            list.add(new File("etc.html", 1234));

            list.accept(new ListVisitor());


            // 测试 FileFoundVisitor
            FileFoundVisitor fileFoundVisitor = new FileFoundVisitor(".java");
            rootdir.accept(fileFoundVisitor);
            Iterator it = fileFoundVisitor.getFoundFiles();
            while (it.hasNext()) {
                System.out.println("FileFoundVisitor: " + ((File)it.next()).getName());
            }

        } catch (FileTreatmentException e) {
            e.printStackTrace();
        }
    }
}
