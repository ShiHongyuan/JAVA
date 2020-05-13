package reflect;

import java.lang.reflect.Method;

/**
 * 获取成员方法并调用
  
    1. 获取成员方法
        1) 批量的方法：
            public Method[] getMethods()：获取所有"公有方法"；（包含了父类的方法也包含Object类）
            public Method[] getDeclaredMethods()：获取所有的成员方法，包括私有的(不包括继承的)

        2) 获取单个的方法：
            public Method getMethod(String name,Class<?>... parameterTypes): 获取某个指定方法名和方法参数的公有方法，方法参数是参数类型的Class对象，无参的方法不传参，或者传null ；
                public Method getDeclaredMethod(String name,Class<?>... parameterTypes)：获取某个指定方法名和方法参数的方法，可以是私有的、受保护、默认、公有，方法参数是参数类型的Class对象，无参的方法不传参，或者传null；
            
    2. 调用方法：
         Method --> public Object invoke(Object obj, Object... args)：invoke是Method 类的方法
                  参数说明：
                  obj：要调用方法的对象； 注意根据多态性，参数类型是Object ，所以传参可以是父类Object 对象，也可以是实际Student对象
                  args：调用方式时所传递的实参； args的参数类型是Object ，但是实参是实际对象
                  Object ：调用方法的返回值；返回值的类型是Object ，可以强制转换成实际Student对象

        如果返回的这个Method 是一个私有private、受保护protected、默认package，反正无法访问的方法，就需要首先
            method.setAccessible(true);//解除私有限定
        再调用method ，注意注意obj对象可以是Object，也可以是实际的子类
            method.invoke(obj,"刘德华");//第一个参数是被调用的对象，第二个参数是被调用对象的方法的参数
        如果被调用的方法有返回值，返回Object对象
            Object result=method.invoke(obj,20);
        方法的返回值可以强转为实际对象
            String res = (String)result;
 *
 * */

public class Methods {
    public static void main(String[] args) throws Exception {
        //1.获取Class对象
        Class stuClass = Class.forName("reflect.Reflect$Student");

        //2.获取所有公有方法
        System.out.println("***************获取自己和继承的所有”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for(Method m : methodArray){
            System.out.println(m);
            //太多了
        }
        System.out.println("***************获取自己所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for(Method m : methodArray){
            System.out.println(m);
            //public java.lang.String reflect.Reflect$Student.toString()
            //public void reflect.Reflect$Student.print()
            //private java.lang.String reflect.Reflect$Student.show4(int)
            //public void reflect.Reflect$Student.show1(java.lang.String)
            //protected void reflect.Reflect$Student.show2()
            //void reflect.Reflect$Student.show3()
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method method = stuClass.getMethod("show1", String.class);
        System.out.println(method);//public void reflect.Reflect$Student.show1(java.lang.String)
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        method.invoke(obj, "刘德华");//调用了公有、无参构造方法执行了。。。

        System.out.println("***************获取默认的show3()方法*******************");
        method = stuClass.getDeclaredMethod("show3",null);
        System.out.println(method);//void reflect.Reflect$Student.show3()
        method.invoke(obj);//调用了：默认的，无参的show3()

        System.out.println("***************获取私有的show4()方法******************");
        method = stuClass.getDeclaredMethod("show4", String.class, int.class);
        System.out.println(method);//private java.lang.String reflect.Reflect$Student.show4(int)
        method.setAccessible(true);//解除私有限定

        Object result = method.invoke(obj, "刘德华", 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        Object result1 = method.invoke(obj, new Object[]{"刘德华", 20});//方法有多个实参，也可以把参数放在Object数组里传递，传参的时候会自动拆分成2个实参
        System.out.println("返回值：" + result);//返回值：调用了，私有的，并且有返回值的，int参数的show4(): name = 刘德华, age = 20
        String res = (String)result;
        System.out.println("返回值：" + res);//返回值：调用了，私有的，并且有返回值的，int参数的show4(): name = 刘德华, age = 20

    }
}
