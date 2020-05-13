package reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 通过反射越过泛型检查
 *
 * 例如：有一个String泛型的集合，怎样能向这个集合中添加一个Integer类型的值？
 *
 * 泛型（generic.Generic）用在编译期，编译器会检查方法的参数是不是符合指定的泛型，
 * 但是用反射调用方法，参数的类型不容易被检查出来，而编译过后运行时泛型擦除（消失掉）了，
 * 所以运行时加入的不符合泛型的参数不会被报错，可以正常执行完，所以是可以通过反射越过泛型检查的。
 * */

public class Generic {
    public static void main(String[] args) throws Exception{
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");
//        strList.add(100);  //原本直接add 100 过不了编译阶段的泛型检查，如果是IDE，IDE的语法检查都过不了，所以会报错

        //  strList.add(100);
        //获取ArrayList的Class对象，反向的调用add()方法，添加数据
        Class listClass = strList.getClass(); //得到 strList 对象的字节码 对象
        //获取add()方法
        Method m = listClass.getMethod("add", Object.class);
        //调用add()方法
        m.invoke(strList, 100);

        //遍历集合
        for(Object obj : strList){
            System.out.println(obj);
            System.out.println(obj instanceof String);
            System.out.println(obj instanceof Integer);
            //aaa  true  false
            //bbb  true  false
            //100  false true
        }
    }
}
