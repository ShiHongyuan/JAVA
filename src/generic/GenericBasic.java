package generic;

/**
 * 泛型（指定类型参数（或者叫 泛型参数），指定后就叫参数化类型）：通过指定类型参数，让单个声明能够指定一组类或接口或方法
 *      Java泛型方法：使用单个方法声明，来指定一组相关方法
 *      Java泛型类：使用单个类声明，来指定一组相关类
 *      Java泛型接口：使用单个类声明，来指定一组相关接口
 *
 *
 * 注意：类型参数只能是引用类型，而不能是基本数据类型
 *
 *
 * 常用的类型参数名称列表:
 *      E - 元素，主要由Java集合(Collections)框架使用。
 *
 *      K - 键，主要用于表示映射中的键的参数类型。
 *      V - 值，主要用于表示映射中的值的参数类型。
 *
 *      N - 数字，主要用于表示数字。
 *
 *      T - 类型，主要用于表示第一类通用型参数。
 *      S - 类型，主要用于表示第二类通用类型参数。
 *      U - 类型，主要用于表示第三类通用类型参数。
 *      V - 类型，主要用于表示第四个通用类型参数。
 *
 *
 *
 * 类型检查和类型擦除（只针对泛型类和泛型接口在被泛型类实现时）：
 *      类型检查：
 *          泛型只在编译阶段有效，在对象进入和离开方法的边界处添加类型检查和类型转换的方法
 *              如果没有指定具体类型，实际上是指定的Object，编译时T替换成Object，可以操作所有引用类型。
 *              如果指定了类型参数为具体通用类型，编译的时候T替换成指定的具体类型，对参数进行类型检查，或者对参数和返回值强制转换类型为指定类型。
 *
 *      类型擦除：
 *          在编译之后程序会采取去泛型化的措施，也就是类型擦除。
 *          类型擦除后，被指定为不同类型的对象，运行时用反射获取的Class对象实际上是同一个（不带<>的普通类），其成员变量和方法是用Object替代的。
 *
 *          但是怎么区分指定为不同类型的对象呢，是在实例对象内部，将参数和返回值类型强制转换成了指定的具体类型。所以具体类型只与实例有关，如果是static类型的成员，由于是共享的，与实例无关，所以static与泛型参数 T 不能共存
 *
 *
 *      所以我们在创建实例对象时，必须指定类型（不指定也是默认的Object类型），不然怎么在编译阶段进行类型检查呢，是吧？
 *
 *      进行泛型检查和泛型擦除的对象：
 *      1、声明为泛型的类名和接口名，类型擦除后，类名或接口名后没有<>了，跟
 *      2、声明为泛型的类或者接口定义中涉及泛型类型参数的成员变量和成员方法（涉及泛型类型参数的成员方法只要没有声明泛型方法，就不是泛型方法，它只是使用了泛型类或泛型接口声明的泛型参数，要相同），类型擦除后 T 替换成Object
 *      3、没有声明为泛型的泛型方法，注意：泛型方法和泛型类和泛型接口不一样，没有类型擦除
 *
 *      由于类型擦除，在一个路径下定义的同名的类或接口，即使有泛型类也有普通类，泛型类也有不同的泛型参数，但是
 *
 * 注意：泛型对象创建实例对象时，与普通对象有一点不一样
 *      根据多态性，普通对象的实际类型是由new 后面实际创建的对象确定的，即使前面声明为父类，创建的是子类，编译运行时都是按照子类的属性来的
 *      但是泛型对象编译阶段的类型检查是由前面声明时指定的类型确定的，只要后面new时加上了一个空的尖括号<>（jdk1，7后规定的），就表示编译阶段需要根据声明类型进行类型检查，而即使后面new时也添加了<具体类型>，但是编译时这个具体类型会被忽略，替换成空的<>，所以说即使都指定了类型，真正有用的也只有前面声明的类型
 *
 */
public class GenericBasic {
    public static void main(String[] args) {

        /******************  没有使用泛型，使用Object的参数 & 没有指定类型参数的泛型 & 指定了具体类型的泛型 ******************/

        // ********************  没有使用泛型，使用Object的参数  ************************
        //在没有泛型的时候，希望类或者方法或者接口有通用性，需要把参数定义为Object类型，这样可以接受所有引用类型
        //但是当返回具体类型来使用的时候，必须将其“强制”转换为具体类型或者接口，然后才可以调用具体类型上的方法
        BoxObject boxObject = new BoxObject();
        boxObject.set(new Integer(1));
        Integer i = (Integer)boxObject.get();
        System.out.println(i.intValue());//1

        boxObject.set(new String("shy"));
        String s = (String)boxObject.get();
        System.out.println(s.toCharArray());//['s','h','y']

        boxObject.set('女');//这不是基本数据类型的char，而是隐式转换成了包装类型Charactor
        char c = (char)boxObject.get();
        System.out.println(c);//女


        // ********************  没有指定类型参数的泛型  ************************
        //当创建泛型对象不使用尖括号指定类型的时候，默认会使用Object类型，可以操作所有引用类型，所以在获取具体类型时也需要强制转换
        Box rawBox = new Box();
        rawBox.set(new Integer(1));
        Integer ii = (Integer)rawBox.get();
        System.out.println(ii.doubleValue());//1

        rawBox.set(new String("shy"));
        String ss = (String)rawBox.get();
        System.out.println(ss.toUpperCase());//SHY

        rawBox.set('女');//这不是基本数据类型的char，而是隐式转换成了包装类型Charactor
        char cc = (char)rawBox.get();
        System.out.println(cc);//女


        //泛型在指定了类型的时候，T在编译阶段被替换成指定的类型，返回类型也是指定的具体类型，不需要强转来调用原对象的方法。
        //注意：类型参数只能是引用类型，而不能是基本数据类型
        Box<Integer> integerBox = new Box<>(); // jdk1.7开始使用类型推断运算符<>，后面只需加上空的<>，就表示编译阶段要进行指定类型的检查
        Box<String> stringBox = new Box<>();

        integerBox.set(new Integer(2));
//        integerBox.set(new String("shy"));//编译不通过

        stringBox.set(new String("shy"));
//        stringBox.set(new Integer(6));//编译不通过

        System.out.printf("Integer Value :%f\n", integerBox.get().doubleValue());//Integer Value :2.0
        System.out.printf("String Value :%s\n", stringBox.get().toUpperCase());//String Value :SHY



        /****************** 指定类型的对象和不指定类型的对象 相互转换 ************************/

        // ********************  指定类型的泛型对象 -> 没有指定类型的泛型对象  *********************

        //改了rawBox的引用，rawBox引用的对象变成了integerBox，修改rawBox，就相当于修改integerBox
        //但是由于rawBox本身的声明没有指定类型，编译时类型检查为Object，所以rawBox只是改变了引用指向的内存区域，但是还是能操作Object的各种子类型
//        rawBox = integerBox;
//        System.out.println(rawBox.get());//2 变成了integerBox的值
//
//        rawBox.set(new Integer(3));
//        System.out.println(rawBox.get());//3
//        System.out.println(integerBox.get());//3
//
//        rawBox.set(new String("shy1"));
//        System.out.println(rawBox.get());//shy1
//        System.out.println(integerBox.get());//shy1



        // ********************  没有指定类型的泛型对象 -> 指定类型的泛型对象  ********************

        //改了integerBox的引用，integerBox引用的对象变成了rawBox，修改integerBox，就相当于修改rawBox
        //但是由于integerBox本身的声明时指定了具体类型为Integer，编译时类型检查为Integer，所以integerBox只是改变了引用指向的内存区域，但是操作还是受限于Integer类型
        integerBox = rawBox;
        System.out.println(integerBox.get());//1 变成了rawBox的值

        integerBox.set(new Integer(5));
        System.out.println(integerBox.get());//5
        System.out.println(rawBox.get());//5


//        integerBox.set(new String("shy2"));//编译时类型检查是Integer，不通过
        rawBox.set(new String("shy2"));
        System.out.println(integerBox.get());//shy2
        System.out.println(rawBox.get());//shy2

    }


    /******************  没有使用泛型，使用Object的参数 & 没有指定类型参数的泛型 & 指定了具体类型的泛型 ******************/

    //没有泛型的解决方案，泛型 T 为Object
    static class BoxObject{
        private Object t;

        public BoxObject() { }

        public BoxObject(Object t) {
            this.t = t;
        }

        public void set(Object t) {
            this.t = t;
        }

        public Object get() {
            return this.t;
        }
    }


    //自定义一个泛型类
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
}
