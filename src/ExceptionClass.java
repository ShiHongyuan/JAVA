import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionClass {
    public static void main(String[] args) {

        /** 浮点数的除法可以除以0
         * 整数的除法不能除�?0，抛出异�?
         * 两操作数只要有一个是浮点数，就会传换成浮点数的除�?
         * */
        System.out.println(2.0/0.0);    // Infinity
        System.out.println(2.0/0);      // Infinity 两操作数只要有一个是浮点数，就会传换成浮点数的除�?
        System.out.println(2/0.0);      // Infinity 两操作数只要有一个是浮点数，就会传换成浮点数的除�?

        System.out.println(0/0.0);      // NaN
        System.out.println(0.0/0);      // NaN
        System.out.println(0.0/0);      // NaN

//        System.out.println(0/0);        // 抛出异常：java.lang.ArithmeticException: / by zero
//        System.out.println(2/0);        // 抛出异常：java.lang.ArithmeticException: / by zero

        /**
         * 整数上溢，超过了int的最大�?�，没有抛异常，这是jvm的一个问�?
         * */
        System.out.println(Integer.MAX_VALUE + 1);   // -2147483648



        /**
         * 异常处理-try-catch
         * */

        /** 通过throw语句在try中直接抛出异常，catch捕获 */



        /** 在try中调用可能抛出异常的函数，间接抛出异常，catch捕获 */

        /** finally */

        /** 在catch中重新抛出异常：不想处理异常，想给调用�?�处理异�? */

        /** 获取异常信息 */

        /** 链式异常 */

        /** 异常分类 */

        /**
         * 1������쳣��������Ƶ� �߼�  ����class��file�Ҳ��������߼����󣩣�һ��Ҫ��������
         * ��1��ϵͳ����Error �� LinkageError��������һ����ı��������һ�������಻����  VirtualMachineError������쳣
         * ��2������ʱ�쳣��RuntimeException��ArithmeticException NullException IndexOutOfException IllegalArgumentException���ݷǷ�����
         *
         * 2���ؼ��쳣��ż���Ĵ��󣬲�һ��Ҫ������
         * ClassNotFoundException IOException  FileNotFoundException��� File���� �����ڵ����
         *
         * extends Throwable��
         * 1������Error extends Throwable
         * 2���쳣��ClassNotFoundException IOException RuntimeException extends Exception (Exception extends Throwable)
         */

        /**
         * ArithmeticException
         */

        /**
         * java.lang.Exception���췽��
         */
        // �޲ι���
        System.out.println( new ArithmeticException().getMessage() );             // null
        // �вι���
        System.out.println( new ArithmeticException("message").getMessage() );    // message
        // �ؼ��쳣���е�Ƕ�׹��췽��: ���԰�����һ���쳣�����쳣ջ�����firstջ����111
        new IOException("111", new ArithmeticException("222"));
        new Exception("111", new IOException("222"));
        new IOException("111", new Exception("222"));

        /**
         * �׳��쳣���ؼ��쳣 || ����쳣  ���������׳��쳣�������ߣ�ֻ�е����߲�֪��ʲô�쳣Ҫ��ʲô
         */
        // ͬһ���������׳��쳣
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

        // �����õķ����׳��쳣�����÷��Լ����������õķ����������ʽ�׳���Ҳ��ֱ���׳������÷�Ҫ���񣬷�����������׳���ֱ����û����������ֹ
        // �����õķ��������﷢���쳣����������ֹ
        try {
            quotient(1, 0);
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());    // ArithmeticException message
        }



        /**
         * �����쳣�� һ������Ҫ�׳�һ���ؼ��쳣������Ҫ�����쳣 throws
         *
         * �ؼ��쳣�������������������쳣�������߱��봦��������뱨��Ҫô��������Ҫô����
         * ����쳣�������������������쳣�������߲�һ��Ҫ����Ҳ������뱨��
         */
        // �׳�IOException��throws IOException
        try {
            filenotfound();
        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
            System.out.println(ex.getMessage());    // hhh.txt (No such file or directory)
        }

        /**
         * �����쳣�����ŷָ���������쳣�����벶����ÿ���ؼ��쳣������쳣���Բ�����ArrayIndexOutOfBoundsExceptionû����
         */
        try {
            multiexception();
        }
        catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException  ");
            for(StackTraceElement s : ex.getStackTrace()) {  // ����ջ������Ϣ������
                System.out.println(s.getFileName());         // �ļ���
                System.out.println(s.getClassName());        // ����
                System.out.println(s.getMethodName());       // ������
                System.out.println(s.getLineNumber());       // �к�
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();                      // ��ӡthrowable���󣬵���ջ������Ϣ
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());       // ���ش���������󴴽�ʱ��������Ϣ
        }
        // �����Exceptionһ��Ҫ��������棬��Ȼ���඼�����ˣ������ں��沶���û�������ˣ�����뱨��
        catch (Exception ex) {
            System.out.println(ex.toString());         // ���أ��쳣�����֣�getMessage()
        }

        /**
         * ��ʽ�쳣�����Ƕ�׵��õķ������ӷ�����catch�������׳����ӷ������쳣���󣬸��������Բ���2���쳣����
         */
        try {
            linkException();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());    // linkException exception
            ex.printStackTrace();    // �ȴ�ӡjava.lang.Exception���쳣ջ���ٴ�ӡjava.lang.ArithmeticException���쳣ջ
        }

//        /**
//         * finally: ��try��һ�ԣ�������û�в����쳣����ִ��
//         */
//
//        // û���쳣��˳��ִ��
//        try {
//            quotient(1,1);
//        }
//        catch (ArithmeticException ex) {
//            ex.getMessage();
//        }
//        finally {
//            System.out.println("û�з����쳣Ҳ��ִ��");
//        }
//
//
//        // �����쳣��˳��ִ��
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            System.out.println("��ִ�в���ģ���ִ��finally");
//        }
//        finally {
//            System.out.println("�����쳣finallyҲҪ��ִ��");
//        }
//
//
//        // �����׳��쳣����ִ��finally�����׳��쳣��������
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            throw new ArithmeticException("throw again");   // ��catch�������׳��쳣
//
//        }
//        finally {
//            System.out.println("���쳣�׳�ǰִ��");
//        }
//
//        // �׳��쳣����ִ��finally�����׳��쳣��������
//        try {
//            quotient(1,0);
//        }
//        finally {
//            System.out.println("û�в����쳣Ҳ��ִ��");    // ��ִ�У����׳��쳣��������
//        }
//
//        // ǰ����return����ִ��finally����return
//        try {
//            quotient(1,0);
//        }
//        catch (ArithmeticException ex) {
//            return;
//        }
//        finally {
//            System.out.println("returnҲҪִ��1");
//        }
//
//        try {
//            return;
//        }
//        finally {
//            System.out.println("returnҲҪִ��2");
//        }


        /**
         * �Զ����쳣�࣬����Ǽ̳бؼ��쳣�����������Զ����쳣��Ϊǿ�ƴ����쳣����
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
    public static void testMyException() throws myException{  // myException�Ǽ̳��Աؼ��쳣������Ҳ�Ǳؼ��쳣��һ������Ҫ�׳�һ���ؼ��쳣������Ҫ�����쳣
        if(true) {
            throw new myException();   // һ������Ҫ�׳�һ���ؼ��쳣������Ҫ�����쳣
        }
    }


    public static int quotient(int n1, int n2) {
        if(n2 == 0) {
            throw new ArithmeticException("ArithmeticException message");
        }
        return n1/n2;
    }

    public static void filenotfound() throws FileNotFoundException{
        java.util.Scanner scanner = new Scanner(new File("hhh.txt"));  // ��main������FileNotFoundException
        System.out.println("throws �ˣ���������𣿲��ᣡ");
    }

    public static void multiexception() throws FileNotFoundException, ClassNotFoundException, IOException, ArrayIndexOutOfBoundsException {
        java.util.Scanner scanner = new Scanner(new File("hhh.txt"));
        scanner.next();
    }

    public static void linkException() throws Exception { // Exception��������ؼ��쳣������һ������Ҫ�׳�һ���ؼ��쳣������Ҫ�����쳣
        try {
            quotient(1,0);
        }
        catch (ArithmeticException ex) {
            throw new Exception("linkException exception", ex);
        }
    }





}
