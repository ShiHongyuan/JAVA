package designPattern.builder;

/**
 * 建造者模式：组装一个复杂的实例，组装的方法在Builder抽象类中定义，为了不同的目的，继承抽象类的具体子类实现的方法不同。
 *          但是建造者角色，持有 Builder实例，利用委托的办法，组合Builder中的复杂方法，向外部提供一个简单生成复杂实例的接口API，
 *          即 construct 方法，但是并不关心传递给建造者持有的 Builder实例具体是哪一个，因为调用的都是uilder抽象类中的方法，
 *          而外部使用者也只知道用建造者的 construct方法来创建复杂实例，也不知道 Builder的存在，
 *          所以分离了使用者、建造者、Builder的具体实现，所以它们都可以作为组件使用和被替换。
 */
public class main {
    public static void main (String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(0);
        }
        if(args[0].equals("plain")) {
            TextBuilder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            // 监工委托的具体建造者是什么，这里具体建造的就是什么
            director.construct();
            String result = textBuilder.getResult();
            System.out.println(result);
        }
        else if (args[0].equals("html")) {
            HtmlBuilder htmlBuilder = new HtmlBuilder();
            Director director = new Director(htmlBuilder);
            // 监工委托的具体建造者是什么，这里具体建造的就是什么
            director.construct();
            String fileanme = htmlBuilder.getFilename();
            // 在 mygithub/JAVA文件夹里
            System.out.println(fileanme + "文件编写完成。");
        } else {
            usage();
            System.exit(0);
        }
    }
    public static void usage() {
        System.out.println("Usage: java Main plain      编写纯文本文档");
        System.out.println("Usage: java Main html       编写 HTML文档");
    }
}
