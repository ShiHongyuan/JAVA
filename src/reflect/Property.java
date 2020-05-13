package reflect;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 当我们升级这个系统时，不要Student类，而需要新写一个Student2的类时，这时只需要更改pro.txt的文件内容就可以了。代码就一点不用改动，我们只需要将新类发送给客户端，并修改配置文件即可
 *
 * pro.txt
 *
 * className = reflect.Property$Student
 * methodName = show
 * 改为
 * className = reflect.Property$Student2
 * methodName = show
 *
 */
public class Property {
    public static void main(String[] args) throws Exception {
        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));//reflect.Property$Student
        System.out.println(stuClass);//class reflect.Property$Student
        //2获取show()方法
        Method method = stuClass.getMethod(getValue("methodName"));//show
        System.out.println(method);//public void reflect.Property$Student.show()
        //3.调用show()方法
        method.invoke(stuClass.getConstructor().newInstance());//更改配置文件前：is show Student  更改配置文件前：is show Student2

    }


    //此方法接收一个key，在配置文件中获取相应的value，key可能是className，也可能是methodName
    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        FileReader in = new FileReader("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/out/production/mygithub/reflect/pro.txt");//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }

    //内部类跟内部成员一样，静态方法里只能访问静态的内部成员，所以静态方法里只能创建静态的内部类，如果Student不是一个内部类，在外面，就可以访问了
    public static class Student {
        public Student(){}
        public void show(){
            System.out.println("is show Student");
        }
    }

    public static class Student2 {
        public Student2(){}
        public void show(){
            System.out.println("is show Student2");
        }
    }

}
