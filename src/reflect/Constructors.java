package reflect;

import reflect.Reflect.Student;

import java.lang.reflect.Constructor;

/**
 * 通过反射获取构造方法  java.lang.reflect.Constructor;
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
  
 1. 获取构造方法：
      1) 批量的方法：
            public Constructor[] getConstructors()：获取所有公有的public构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有private、受保护protected、默认package、公有public)

      2) 获取单个的方法，并调用：
            public Constructor getConstructor(Class... parameterTypes):  获取某个指定参数的公有的public构造方法，指定参数是参数类型的Class对象，无参的构造方法不传参，或者传null；
            public Constructor getDeclaredConstructor(Class... parameterTypes): 获取某个指定参数的构造方法，可以是私有的，受保护、默认、公有，指定参数是参数类型的Class对象，无参的构造方法不传参，或者传null；
      
 2. 调用构造方法：
            Constructor-->constructor.newInstance(Object... initargs)：newInstance是 Constructor类的方法（管理构造函数的类）

            如果返回的这个constructor是一个私有private、受保护protected、默认package，反正无法访问的，就需要首先
                constructor.setAccessible(true);//暴力访问(忽略掉访问修饰符)
            再：
                Object obj = constructor.newInstance(构造方法参数);
            再要获取对象实例的话：
                Student stu = (Student)obj;


 注意！！！：通过反射方法获取的构造函数，必须是类中显式定义了自己的构造函数，默认的无参的构造函数只在创建实例的时候有效，获取构造函数的反射方法找不到，所以没有显式定义就会报错

 **/

public class Constructors {
    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class stuClass = Class.forName("reflect.Reflect$Student");


        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = stuClass.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
            //public reflect.Reflect$Student(java.lang.String,int)
            //public reflect.Reflect$Student()
            //public reflect.Reflect$Student(char)
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = stuClass.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
            //private reflect.Reflect$Student(int)
            //protected reflect.Reflect$Student(boolean)
            //public reflect.Reflect$Student(java.lang.String,int)
            //reflect.Reflect$Student(java.lang.String)
            //public reflect.Reflect$Student()
            //public reflect.Reflect$Student(char)
        }

        System.out.println("*****************获取公有、无参的构造方法，并调用********************************");
        Constructor con = stuClass.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型的Class对象，切记是Class对象
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println(con);
        //调用构造方法
        Object obj = con.newInstance(); //注意newInstance返回的是Object，但是实际对象是Student
        System.out.println("obj = " + obj);
//        Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = stuClass.getDeclaredConstructor(char.class);//这里需要的是一个参数的类型的Class对象，切记是Class对象
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');

        //注意newInstance返回的是Object，但是实际对象是Student，多态中动态调用Student的toString方法
        System.out.println(obj.toString()); //"Student [name=null, age=0, sex=男, phoneNum=null]"
        System.out.println(((Student)obj).toString());//"Student [name=null, age=0, sex=男, phoneNum=null]"
        ((Student) obj).print();//"这是在调用Student的对象实例的成员方法"


        /**
         * Class对象只有一个，但是通过反射创建的实例是不一样的
         * */
        Object obj1 = stuClass.getConstructor().newInstance();
        Object obj2 = stuClass.getConstructor().newInstance();
        System.out.println("obj1 == obj2: " + (obj1 == obj2)); //false
        System.out.println("student1 == student2: " + (((Student)obj1) == ((Student)obj2))); //false


    }

}
