import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionClass {
    public static void main(String[] args) {

        /** 只要有一个操作数是浮点，算数就会变成浮点数计算
         * 浮点计算除数是0，不会异常
         * 整数计算除数是0，会报异常 java.lang.ArithmeticException
         * */
        System.out.println(2.0/0.0);    // Infinity
        System.out.println(2.0/0);      // Infinity 浮点计算除数是0，不会异常
        System.out.println(2/0.0);      // Infinity 浮点计算除数是0，不会异常

        System.out.println(0/0.0);      // NaN   浮点计算除数是0，不会异常
        System.out.println(0.0/0);      // NaN   浮点计算除数是0，不会异常
        System.out.println(0.0/0);      // NaN

//        System.out.println(0/0);        // 整数计算，报异常java.lang.ArithmeticException: / by zero
//        System.out.println(2/0);        // 整数计算，报异常java.lang.ArithmeticException: / by zero

        /**
         * 超过int的最大值，本来应该报上溢异常的，但是jvm不会报错，直接为负数的最小值，
         * */
        System.out.println(Integer.MAX_VALUE + 1);   // -2147483648


        /**
         * 1、免检异常：程序设计的 逻辑  错误（class，file找不到不叫逻辑错误），一定要纠正程序
         * （1）系统错误：Error ： LinkageError类依赖，一个类的编译造成另一个依赖类不兼容  VirtualMachineError虚拟机异常
         * （2）运行时异常：RuntimeException：ArithmeticException NullException IndexOutOfException IllegalArgumentException传递非法参数
         *
         * 2、必检异常：偶发的错误，不一定要纠正的
         * ClassNotFoundException IOException  FileNotFoundException针对 File对象 不存在的情况
         *
         * extends Throwable：
         * 1、错误：Error extends Throwable
         * 2、异常：ClassNotFoundException IOException RuntimeException extends Exception (Exception extends Throwable)
         */

        /**
         * ArithmeticException
         */

        /**
         * java.lang.Exception构造方法
         */
        // 无参构造
        System.out.println( new ArithmeticException().getMessage() );             // null
        // 有参构造
        System.out.println( new ArithmeticException("message").getMessage() );    // message
        // 必检异常独有的嵌套构造方法: 可以包含另一个异常，在异常栈里，但是first栈还是111
        new IOException("111", new ArithmeticException("222"));
        new Exception("111", new IOException("222"));
        new IOException("111", new Exception("222"));

        /**
         * 抛出异常：必检异常 || 免检异常  被调用者抛出异常给调用者，只有调用者才知道什么异常要做什么
         */
        // 同一个方法里抛出异常
        try {
            java.util.Scanner scanner = new Scanner(new File("hhh.txt"));
            System.out.println("file exits");
        }
        catch (FileNotFoundException ex) {
            System.out.println("file not found");   // file not found
        }

        try {
            java.util.Scanner scanner = new Scanner("testfile.txt");
            if (scanner.next() != "true") {
                InputMismatchException ex = new InputMismatchException("InputMismatchException message");
                throw ex;
            }
        }
        catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());    // InputMismatchException message

        }

        // 被调用的方法抛出异常，调用方自己处理，被调用的方法如果不显式抛出，也会直接抛出，调用方要捕获，否则就再向外抛出，直到都没处理，程序终止
        // 被调用的方法在哪里发生异常就在哪里终止
        try {
            quotient(1, 0);
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());    // ArithmeticException message
        }



        /**
         * 声明异常： 一个方法要抛出一个必检异常，必须要声明异常 throws
         *
         * 必检异常：被调用者有声明的异常，调用者必须处理，否则编译报错，要么再声明，要么捕获
         * 免检异常：被调用者有声明的异常，调用者不一定要处理，也不会编译报错
         */
        // 抛出IOException：throws IOException
        try {
            filenotfound();
        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
            System.out.println(ex.getMessage());    // hhh.txt (No such file or directory)
        }

        /**
         * 声明异常：逗号分隔声明多个异常，必须捕获处理每个必检异常，免检异常可以不处理ArrayIndexOutOfBoundsException没处理
         */
        try {
            multiexception();
        }
        catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException  ");
            for(StackTraceElement s : ex.getStackTrace()) {  // 返回栈跟踪信息的数组
                System.out.println(s.getFileName());         // 文件名
                System.out.println(s.getClassName());        // 类名
                System.out.println(s.getMethodName());       // 方法名
                System.out.println(s.getLineNumber());       // 行号
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();                      // 打印throwable对象，调用栈跟踪信息
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());       // 返回创建这个对象创建时的描述信息
        }
        // 父类的Exception一定要在子类后面，不然父类都捕获了，子类在后面捕获就没有意义了，会编译报错
        catch (Exception ex) {
            System.out.println(ex.toString());         // 返回：异常类名字：getMessage()
        }

        /**
         * 链式异常：多个嵌套调用的方法，子方法在catch里重新抛出子子方法的异常对象，父方法可以捕获2个异常对象
         */
        try {
            linkException();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());    // linkException exception
            ex.printStackTrace();    // 先打印java.lang.Exception的异常栈，再打印java.lang.ArithmeticException的异常栈
        }

//        /**
//         * finally: 和try是一对，不管有没有捕获异常都会执行
//         */
//
//        // 没有异常，顺序执行
//        try {
//            quotient(1,1);
//        }
//        catch (ArithmeticException ex) {
//            ex.getMessage();
//        }
//        finally {
//            System.out.println("没有发生异常也会执行");
//        }
//
//
//        // 捕获异常，顺序执行
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            System.out.println("先执行捕获的，再执行finally");
//        }
//        finally {
//            System.out.println("捕获到异常finally也要会执行");
//        }
//
//
//        // 捕获抛出异常，先执行finally，再抛出异常给调用者
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            throw new ArithmeticException("throw again");   // 在catch中重新抛出异常
//
//        }
//        finally {
//            System.out.println("在异常抛出前执行");
//        }
//
//        // 抛出异常，先执行finally，再抛出异常给调用者
//        try {
//            quotient(1,0);
//        }
//        finally {
//            System.out.println("没有捕获异常也会执行");    // 先执行，再抛出异常给调用者
//        }
//
//        // 前面有return，先执行finally，后return
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            return;
//        }
//        finally {
//            System.out.println("return也要执行1");
//        }
//
//        try {
//            return;
//        }
//        finally {
//            System.out.println("return也要执行2");
//        }


        /**
         * 自定制异常类，最好是继承必检异常，这样符合自定制异常类为强制处理异常而生
         */
        try {
            testMyException();
        }
        catch (myException ex) {
            System.out.println(ex.getMessage());   // myException message
        }
    }

    public static class myException extends Exception {
        public myException() {
            super("myException message");
        }
    }
    public static void testMyException() throws myException{  // myException是继承自必检异常，所以也是必检异常，一个方法要抛出一个必检异常，必须要声明异常
        if(true) {
            throw new myException();   // 一个方法要抛出一个必检异常，必须要声明异常
        }
    }


    public static int quotient(int n1, int n2) {
        if(n2 == 0) {
            throw new ArithmeticException("ArithmeticException message");
        }
        return n1/n2;
    }

    public static void filenotfound() throws FileNotFoundException{
        java.util.Scanner scanner = new Scanner(new File("hhh.txt"));  // 给main声明了FileNotFoundException
        System.out.println("throws 了，还会继续吗？不会！");
    }

    public static void multiexception() throws FileNotFoundException, ClassNotFoundException, IOException, ArrayIndexOutOfBoundsException {
        java.util.Scanner scanner = new Scanner(new File("hhh.txt"));
        scanner.next();
    }

    public static void linkException() throws Exception { // Exception本身包含必检异常，所以一个方法要抛出一个必检异常，必须要声明异常
        try {
            quotient(1,0);
        }
        catch (ArithmeticException ex) {
            throw new Exception("linkException exception", ex);
        }
    }

}
