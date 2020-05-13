package generic;

/**
 * 反射只能创建泛型类的唯一的一个Class对象，不能指定具体类型
 *      泛型对象在类型擦除后，被指定为不同类型的对象，运行时用反射获取的Class对象实际上是同一个（不带<>的普通类），其成员变量和方法是用Object替代的。
 *
 * 通过反射获取的Object对象在转换成具体对象时：
 *      如果不指定泛型类型，则默认为具体类型为Object的泛型对象，操作类型不受限制（可以操作所有引用类型）
 *      如果指定了具体的类型，则转换为指定类型的泛型对象，操作类型受限于指定的具体类型
 *
 * Class<T>本身就是一个泛型类，只是获取Class对象时没有指明具体类型，用Object替换类型参数，所以newInstance()才返回Object类型的对象
 * Class<T>的构造函数是private的，没有办法在创建Class实例的时候指定类型，Class.forName()方法里都是创建的Class<Object>
 *
 */

public class GenericAndReflect {

    static class Box<T>{
        private T t;

        public Box() { }

        public Box(T t) {
            this.t = t;
        }

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return this.t;
        }
    }

    public static void main(String[] args) throws Exception {
//        Class boxClass = Class.forName("generic.GenericAndReflect$Box<String>");//报错.ClassNotFoundException，因为泛型类运行时是加上只是一个经过类型擦除后的普通类，<类型>只在编译阶段有效，所以无效了就不认得了
        Class boxClass = Class.forName("generic.GenericAndReflect$Box");
        System.out.println(boxClass);//class generic.GenericAndReflect$Box

//        System.out.println(boxClass.getConstructor(Integer.class));//报错NoSuchMethodException: 因为Class对象里的泛型参数 T 在类型擦除后都被替换成了 Object
        System.out.println(boxClass.getConstructor(Object.class));//public generic.GenericAndReflect$Box(java.lang.Object)

        Object obj = boxClass.getConstructor().newInstance();
        System.out.println(obj);//generic.GenericAndReflect$Box@1d44bcfa

        //通过反射获取的Object对象在转换成具体对象时，如果不指定泛型类型，则默认为具体类型为Object的泛型对象，所以操作类型不受限制（可以操作所有引用类型）
        Box box = (Box)obj;

        box.set(1);
        System.out.println(box.get());//1

        box.set("shy");
        System.out.println(box.get());//shy


        //通过反射获取的Object对象在转换成具体对象时，如果指定了具体的类型，则转换为指定类型的泛型对象，操作类型受限于指定的具体类型
        Box<String> boxString1 = (Box)obj;
        Box<String> boxString2 = (Box<String>)obj;

//        boxString2.set(1);//T是String类型了，编译报错
//        boxString3.set(1);//T是String类型了，编译报错

        boxString1.set("hong");
        System.out.println(boxString1.get());//hong
        boxString2.set("yuan");
        System.out.println(boxString2.get());//yuan
        System.out.println(box.get());//yuan

        /**
         * 注意：泛型对象创建实例对象时，与普通对象有一点不一样
         *      根据多态性，普通对象的实际类型是由new 后面实际创建的对象确定的，即使前面声明为父类，创建的是子类，编译运行时都是按照子类的属性来的
         *      但是泛型对象编译阶段的类型检查是由前面声明时指定的类型确定的，只要后面new时加上了一个空的尖括号<>（jdk1，7后规定的），就表示编译阶段需要根据声明类型进行类型检查，而即使后面new时也添加了<具体类型>，但是编译时这个具体类型会被忽略，替换成空的<>，所以说即使都指定了类型，真正有用的也只有前面声明的类型
         */
        // 所以在声明时没有指定类型参数，只是在强制转换时指定了实际上无效的类型参数，这个对象的类型检查还是 Object，所以操作类型不受限制（可以操作所有引用类型）
        Box boxString3 = (Box<String>)obj;
        boxString3.set(2);
        System.out.println(boxString3.get());//2
        boxString3.set("shi");
        System.out.println(boxString3.get());//shi


        //注意：由一个反射对象转换成的对象实例，都是同一个引用，所以都是引用相等的
        System.out.println(box == obj);//true
        System.out.println(box == boxString1);//true
        System.out.println(box == boxString2);//true
        System.out.println(boxString2 == boxString3);//true








    }
}
