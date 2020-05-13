public class MemStackAndHeap {
    public static void stackTest() {
        int a = 1;
        int b = 1;
        System.out.println("基本数据类型，线程的栈中存放基本数据类型变量，常量池中放变量的数据，由于栈数据可以共享，" +
                "不同的变量指向同一个数据" + (a == b)); // True

        String s1 = "1";
        String s2 = "1";
        System.out.println("String不是基本数据类型，栈中存放字面值引用，常量池中存放字面值对象，由于栈数据可以共享，" +
                "不同的引用指向同一个字面值" + (s1 == s2)); // True

        String ss1 = new String("1");
        String ss2 = new String("1");
        System.out.println("new的类对象在堆中开辟内存空间，栈中存放对象的句柄（即对象的引用变量），由于堆中的对象不可以共享，" +
                "栈中不同的对象句柄指向堆中不同的对象" + (ss1 == ss2)); // False

        System.out.println(s1 == ss1); // False

        /**
         * 但是通过 赋值操作符 就是实参传形参的原理
         * 1、基本数据类型和常量赋值是值赋值
         * 2、对象是引用地址赋值，不开辟新的空间
         */
        String s11 = "123";
        String s22 = s11;                  // 值复制，s1和s2存的是指向常量池的不同的地址
        System.out.println(s11 == s22);    // true

        String s111 = new String("123");
        String s222 = s111;                // 引用地址复制，s11和s22存的是相同的指向堆内存的地址，没有开辟新的空间
        System.out.println(s111 == s222);  // true

        System.out.println(s11 == s222);   // false

    }

    public static void stringEqualTest() {
        String ss3 = new String("2");
        String ss4 = new String("2");
        System.out.println("包装类型：不同的引用指向不同的堆对象，==表示引用对象相同返回True，否则false " + (ss3 == ss4)); // False

        String ss5 = new String("2");
        String ss6 = new String("2");
        System.out.println("包装类型：不同的堆对象的字符串字面值相等，equals表示对象字面值相同返回True " + (ss5.equals(ss6))); // True


    }

    public void objectEqualTest() {
        CustomObject obj1 = new CustomObject();
        CustomObject obj2 = new CustomObject();
        DecimalToBinary d = new DecimalToBinary();
        System.out.println("自定义Object：不同的引用指向不同的堆对象，==表示引用对象相同返回True，否则false " + (obj1 == obj2)); // False
        System.out.println("自定义Object：不同的堆对象的相等，equals跟==一样表示引用对象相同返回True，否则false " + (obj1.equals(obj2))); // False
    }

    class CustomObject {
        int a = 1;
        int b = 2;
    }


    public static void finalTest() {
        // 编译期能确定B1 = "b"，编译期确定的常量跟直接双引号定义的常量没有区别了，就能编译成b1 = "ab"，就会放入常量池;
        String a1 = "ab";
        final String B1 = "b";
        String b1 = "a" + B1;
        System.out.println((a1 == b1)); //result = true

        // 编译期不能确定bb = "b"，b = "a" + bb会用String的new方式开辟新的对象，字符串拼接是String对象的方法
        String a = "ab";
        String bb = "b";
        String b = "a" + bb;
        System.out.println((a == b)); //result = false

        // 编译期不能确定getBB() = "b"，b = "a" + B2会用String的new方式开辟新的对象，字符串拼接是String对象的方法
        String a2 = "ab";
        final String B2 = getBB();
        String b2 = "a" + B2;
        System.out.println((a2 == b2)); //result = false


        final int D = 1;
        final int E = 1;
        System.out.println((D == E)); // true

        int F1 = 2;
        final int F2 = 1;
        int F3 = 1 + F2;
        System.out.println((F1 == F3)); // true

        int F4 = 2;
        int F5 = 1;
        int F6 = 1 + F5;
        System.out.println((F4 == F6)); // true
    }

    private static String getBB() {
        return "b";
    }



    public static void main(String[] args) {
        /****************************************************************************************
         * 线程栈内存其优点是寄存速度快、仅次于寄存器，栈数据可以共享、缺点是数据的大小及生存周期是必须确定的，不够灵活。
         * 在栈内存中的变量，当该变量退出该作用域后，Java会自动释放掉为该变量所分配的内存空间，该内存空间可以立即被另作他用。
         * 对于共享的数据，当没有引用指向数据时，这个数据就会消失，而不会等待垃圾回收器来回收。
         * 栈中的变量指向堆内存中的变量，这就是Java中的指针
         * 线程私有
         ****************************************************************************************/


        /****************************************************************************************
         * 堆内存用来存放由new创建的对象和数组。（数组初始化可以不new，但是也是继承对象）
         * 堆的优势是可以动态地分配内存大小，生存期也不必事先告诉编译器，因为它是在运行时动态分配内存的，但缺点是，由于要在运行时动态 分配内存，存取速度较慢。
         * 在堆中分配的内存，由Java虚拟机的自动垃圾回收器来管理（GC）。
         * 数组和对象在没有引用变量指向它的时候，才变为垃圾，不能在被使用，但仍 然占据内存空间不放，在随后的一个不确定的时间被垃圾回收器收走（释放掉）。这也是 Java 比较占内存的原因。
         *
         * 在函数传递形参时，实参是堆内存时，实参会联动改变，实参是常量池和栈中时，实参不联动改变
         * class的成员变量对象在堆中
         * 线程共用
         ****************************************************************************************/

        /****************************************************************************************
         * 常量池在函数的method area中，在编译期就确定了，线程栈内存，堆内存都在运行期才开辟确定，不在method area里
         * 存放直接常量（string,integer和 floating point常量）和对其他类型，字段和方法的符号引用，代码等。
         * final修饰的变量在栈中，值都在常量池中
         * 线程共用
         ****************************************************************************************/

        /*****
         * 对于字符串，一种特殊包装类型
         * 其对象的引用都是存储在栈中的，如果是编译期已经创建好(直接用双引号定义的)的就存储在常量池中，如果是运行期（new出来的）才能确定的就存储在堆中。
         * 对于通过new产生一个字符串（假设为“china”）时，会先去常量池中查找是否已经有了“china”对象，如果没有则在常量池中创建一个此字符串对象，然后堆中再创建一个常量池中此”china”对象的拷贝对象。
         * 这也就是有道面试题：Strings=newString(“xyz”);产生几个对象？一个或两个，如果常量池中原来没有”xyz”,就是两个
         * 当然，直接用双引号定义的字符串（为“china”）的变量可以直接指向常量池的“china”对象
         */

        /********************************************************
         * 基本数据类型
         * int, short, long, byte, float, double, boolean, char
         * 对于基本数据类型，其对象值在常量池中，引用在线程栈内
         ********************************************************/
        MemStackAndHeap.stackTest();



        // 包装类型的 == 和 equals
        stringEqualTest();

        // 自定义object的 == 和 equals
        MemStackAndHeap memStackAndHeap = new MemStackAndHeap();
        memStackAndHeap.objectEqualTest();


        memStackAndHeap.finalTest();

        Object o1 = null;
        if (o1 == null) {
            System.out.println("== true");
        }

        int c = 666;
        Integer a = 666;   // 使用常量池中的对象
        Integer b = new Integer(666); // 创建新对象
        Integer d = new Integer(666); // 创建新对象
        if (a.equals(c)) {
            System.out.println("equals true");
        }
        if (b.equals(c)) {
            System.out.println("equals true");
        }
        if (a.equals(b)) {
            System.out.println("equals true");
        }
        if (d.equals(b)) {
            System.out.println("equals true");
        }

        System.out.println(a == c); // true  Integer和int比较时，自动拆箱后进行数值比较，但是a和c是不同的常量池对象
        System.out.println(b == c); // true  Integer和int比较时，自动拆箱后进行数值比较，但是b和c是不同的对象
        System.out.println(b == a); // false Integer直接比较，一个在常量池，一个在堆中，不同的Integer对象，返回false
        System.out.println(d == b); // false Integer直接比较，在堆中的不同的Integer对象，返回false

        Integer i11 = 333;
        Integer i22 = 333;
        System.out.println(i11 == i22);// 输出false  在超出-128-127时，Integer会在常量池中创建对象

        Integer i33 = 3;
        Integer i44 = 3;
        System.out.println(i33 == i44);// true 在-128-127范围内时，Integer会像int一样使用常量池的缓存对象，不会创建新的对象


        // 基本数据类型实参是值传递，String和包装类型传递的实参是地址，但是由于其引用指向的值是final的，函数内会新建对象，相当于值传递
        // 除了基本数据类型，String和包装类型外都是引用传递，改变实参对象的值，原值也改了

        Integer intA = new Integer(6);
        Integer intB  = IntegerTrans(intA);
        System.out.println(intA); // 6
        System.out.println(intA == intB);  // FALSE

        String sB = "6";
        String sBB = StringTrans(sB);
        System.out.println(sB);  // 6
        System.out.println(sB == sBB);  //  FALSE

        memStackAndHeap.compareObjectTrans();


        // 每一次循环都生成新的基本数据类型引用，在循环的每一次完后都会销毁这一次生成的引用对象
        // 在一次循环结束前，让对象test = null，会被gc立即回收
        for (int i = 0; i< 3; i++) {
//            System.out.println(testValue == testValue1);   // 报错testValue、testValue1不存在
            int testValue = i;
            int testValue1 = testValue;
        }


    }
    public static Integer IntegerTrans(Integer b) {
        b = 7;
        return b;
    }
    public static String StringTrans(String b) {
        b = "7";
        return b;
    }


    public void compareObjectTrans () {
        TestClass a = new TestClass(5);
        System.out.println(a.getValue());  // 5
        compareObjectTrans(a);
        System.out.println(a.getValue());  // 5
    }

    public void compareObjectTrans (TestClass a) {
        // 只要有new，就会重新生成对象和重新分配引用，原引用还在，指向原对象，重新生成的引用是另一个，指向新对象
        // Integer类(String类型和包装类型)的value字段是final的，一旦integer类(String类型和包装类型)创建之后他的值就不能被修改，在 index++ 的时候Integer(String类型和包装类型)是创建一个新的类
        // 在方法中一个引用指向了一个新的对象，新的引用指向新的对象，出了方法，原来的引用内容是不变
        TestClass b = new TestClass(10);
        a = b;
    }


    class TestClass {
        private int a;
        public TestClass(int a){
            super();
            this.a = a;
        }
        public int getValue() {
            return a;
        }
        public void setValue(int a) {
            this.a = a;
        }
    }

}
