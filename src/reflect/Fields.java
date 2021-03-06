package reflect;

import reflect.Reflect.Student;

import java.lang.reflect.Field;

/**
 * 通过反射获取成员变量并调用   java.lang.reflect.Field;
 *
    1. 获取成员变量
        1) 批量的方法：
            Field[] getFields()：通过Class对象获取所有的"公有字段"
            Field[] getDeclaredFields()：获取所有字段，包括：私有、受保护、默认、公有；

        2) 获取单个的方法：
            public Field getField(String fieldName)：获取某个指定的公有public字段；
            public Field getDeclaredField(String fieldName)：获取某个指定的字段，可以是私有的，受保护、默认、公有；
  
    2. 设置字段的值：
         Field --> public void set(Object obj, Object value);  ：set是Field 类的方法
                  参数说明：
                  1) obj：要设置的字段所在的对象； 注意根据多态性，参数类型是Object ，所以传参可以是父类Object 对象，也可以是实际Student对象
                  2) value：要为字段设置的值；

        如果返回的这个Field 是一个私有private、受保护protected、默认package，反正无法访问的，就需要首先
            field.setAccessible(true);//暴力反射，解除私有限定
        再设置field ，注意注意obj对象可以是Object，也可以是实际的子类
            field.set(obj,"18888889999");

            Student stu = (Student)obj;
            field.set(stu,"123456789");
  
 *
 * */
public class Fields {
    public static void main(String[] args) throws Exception {
        //1.获取Class对象
        Class stuClass = Class.forName("reflect.Reflect$Student");
        //2.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = stuClass.getFields();
        for(Field f : fieldArray){
            System.out.println(f);//public java.lang.String reflect.Reflect$Student.name
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
            //public java.lang.String reflect.Reflect$Student.name
            //protected int reflect.Reflect$Student.age
            //char reflect.Reflect$Student.sex
            //private java.lang.String reflect.Reflect$Student.phoneNum
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field field = stuClass.getField("name");
        System.out.println(field);//public java.lang.String reflect.Reflect$Student.name
        //获取一个对象
        Object obj = stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        field.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        Student stu = (Student)obj;
        System.out.println("验证姓名：" + stu.name);//验证姓名：刘德华
        field.set(stu, "石鸿瑗");//注意obj对象可以是Object，也可以是实际的子类
        System.out.println("验证姓名：" + stu.name);//验证姓名：石鸿瑗


        System.out.println("**************获取私有字段****并调用********************************");
        field = stuClass.getDeclaredField("phoneNum");
        System.out.println(field);//private java.lang.String reflect.Reflect$Student.phoneNum
        field.setAccessible(true);//暴力反射，解除私有限定
        field.set(obj, "18888889999");
//        System.out.println("验证电话：" + stu.phoneNum);//注意phoneNum是私有变量，实例不能获取到，上面是通过反射的方法才能暴力访问私有成员，不是反射的方法就不行了
        System.out.println("验证电话：" + stu); //"验证电话：Student [name=石鸿瑗, age=0, sex= , phoneNum=18888889999]"
        System.out.println("验证电话：" + stu.toString()); //"验证电话：Student [name=石鸿瑗, age=0, sex= , phoneNum=18888889999]"
    }
}
