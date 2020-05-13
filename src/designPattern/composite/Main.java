package designPattern.composite;

/**
 * 容器模式：叶子和容器的一致性，叶子和容器都继承自抽象类Entry，都是Entry，所以是一致的，容器添加的对象也是Entry，所以容器既可以添加Entry，也可以作为Entry被添加
 *
 *        容器模式就是使容器和原始内容具有一致性，就可以创建出递归结构的对象了，适用于程序中具有递归数据结构的对象，
 *        比如说文件系统。
 */
public class Main {
    public static void main(String[] args) {
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

        rootdir.printList();


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

        rootdir.printList();

        System.out.println("file=" + file.getAbsoluteName());
        System.out.println("directory=" + yuki.getAbsoluteName());
    }
}
