package designPattern.facade.pagemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * 简单窗口模式：封装内部处理集合类，为一个对外接口API的facade类，对外提供就一个API供调用，makeWelcomePage，供外部生成一个欢迎页面用
 */
public class PageMaker {
    private PageMaker(){}
    public static void makeWelcomePage(String mailaddr, String filename) {
        try {
            Properties mailprop = Database.getProperties("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/out/production/mygithub/designPattern/facade/pagemaker/maildata");
            String username = mailprop.getProperty(mailaddr);
            HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
            writer.title("Welcome to " + username + " 's page!");
            writer.paragraph(" 欢迎来到 " + username + " 的主页");
            writer.paragraph(" 等着你的邮件哦！");
            writer.mailto(mailaddr, username);
            writer.close();
            System.out.println(filename + " is created for " + mailaddr + " (" + username + ") ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeLinkPage(String filename) {
        try {
            Properties mailprop = Database.getProperties("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/out/production/mygithub/designPattern/facade/pagemaker/maildata");
            HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
            writer.title("Link Page");
            Set<String> names = mailprop.stringPropertyNames();
            for (String name : names) {
                writer.mailto(name, mailprop.getProperty(name));
            }
            writer.close();
            System.out.println(filename + " is created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
