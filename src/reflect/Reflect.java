package reflect;

/**
 * 通过反射获取Class对象的三种方式
 * 1 Object ——> getClass();  因为Object类中的getClass方法、因为所有类都继承Object类。从而调用Object类来获取
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 *
 * 三种方式常用第三种，第一种对象都有了还要反射干什么。第二种需要导入类的包，依赖太强，不导包就抛编译错误。
 * 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
 *
 * 通过反射创建实例时，如果没有先调用getConstructor()，只有 Class.forName("...").newInstance() 时，在类没有默认构造函数的情况下也会去调用类中定义的无参构造函数。
 *
 */
public class Reflect {
        int a = 5;
        Inner inner = new Reflect.Inner();//不管是静态内部类还是非静态内部类，创建一个内部类的方法都是一样的，都不能通过外部内的实例创建，只是静态方法里只能创建静态内部类
        public static void main(String[] args) {
            //第一种方式获取Class对象
            Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
            Class stuClass1 = stu1.getClass();//获取Class对象
            System.out.println(stuClass1.getName()); // Reflect$Student

            //第二种方式获取Class对象
            Class stuClass2 = Student.class;
            System.out.println(stuClass1 == stuClass2);//true 判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

            //第三种方式获取Class对象
            try {
                Class stuClass3 = Class.forName("Reflect$Student");//注意此字符串必须是带包名的类路径，包名.类名，外部类$内部类
                System.out.println(stuClass1 == stuClass3);//true 判断三种方式是否获取的是同一个Class对象

                Class InnerClass = Class.forName("Reflect$Inner");// 注意不是外部类的静态变量的内部类，也可以通过Reflect$Inner获取
                System.out.println(InnerClass.getName()); // Reflect$Inner

                /**
                 * 内部类和属性获取测试，与反射无关
                 * */
                Student student = new Reflect.Student(); //不管是静态内部类还是非静态内部类，创建一个内部类的方法都是一样的，都不能通过外部内的实例创建，只是静态方法里只能创建静态内部类
                int b = new Reflect().a; //但是获取一个类的非静态属性，必须通过创建类的实例后获取





            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        static class Student {
            //**************成员变量***************//
            public String name;
            protected int age;
            char sex;
            private String phoneNum;

            //---------------构造方法-------------------
            //（默认的构造方法 package，同一个包）
            Student(String name) {
                this.name=name;
                System.out.println("(默认)的构造方法 name = " + name);
            }

            //无参构造方法
            public Student() {
                System.out.println("调用了公有、无参构造方法执行了。。。");
            }

            //有一个参数的构造方法
            public Student(char sex) {
                this.sex=sex;
                System.out.println("性别：" + sex);
            }

            //有多个参数的构造方法
            public Student(String name ,int age) {
                this.name=name;
                this.age=age;
                System.out.println("姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
            }

            //受保护的构造方法
            protected Student(boolean n) {
                System.out.println("受保护的构造方法 n = " + n);
            }

            //私有构造方法
            private Student(int age) {
                this.age=age;
                System.out.println("私有的构造方法   年龄："+ age);
            }

            public void print() {
                System.out.println("这是在调用Student的对象实例的成员方法");
            }


            @Override //注意加不加@Override关键字，都一样，实际对象是Student的实例调用toString方法或者直接打印对象的时候，都是先调用Student自己的toString方法
            public String toString() {
                return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                        + ", phoneNum=" + phoneNum + "]";
            }

            //**************成员方法***************//
            public void show1(String s){
                System.out.println("调用了：公有的，String参数的show1(): s = " + s);
            }
            protected void show2(){
                System.out.println("调用了：受保护的，无参的show2()");
            }
            void show3(){
                System.out.println("调用了：默认的，无参的show3()");
            }
            private String show4(String name, int age){
                System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): name = " + name + ", age = " + age);
                return "调用了，私有的，并且有返回值的，int参数的show4(): name = " + name + ", age = " + age;
            }

            //************** main 方法***************//
            public static void main(String[] args) {
                System.out.println("Student的main方法执行了。。。");
            }

        }

        class Inner {
        }
}
