import java.util.ArrayList;
import java.util.Date;

public class InterfaceDefine {
    public static void main(String[] args) {
        /**
         * ���й�ͬ�ĸ�Object
         * �ӿ�û�й�ͬ�ĸ�
         * һ����ʵ����һ���ӿڣ������ڸýӿ��������ĸ���
         */

        /**
         * java.lang.Comparable<T>  �ȽϽӿ�
         * public int compareTo(T o);
         */


        /**
         * java.io.Serializable  �����л�������Խӿ�
         * ���紫��Ͷ�д�ļ���ʱ��
         */

        /**
         * java.lang.Cloneable  �ɿ�¡������Խӿ�
         * ���ƶ���
         * protected native Object clone() throws CloneNotSupportedException;  native˵������javaд�ģ����Ǻ���api����JVM����ƽ̨ʵ�ֵ�
         * ʵ�ֽӿڵ��������д�÷��������Ұѿ��������η���Ϊpublic������ʵ�����ò���
         */
        // Date ArrayList Calendar��ʵ����Cloneable�ӿ�
        java.util.Date date = new Date();
        Date date1 = date;
        Date date2 = (Date) date.clone();
        java.util.ArrayList list = new ArrayList();
        ArrayList list1 = list;
        ArrayList list2 = (ArrayList)list.clone();

        System.out.println(date1 == date);       // true      ָ������һ��
        System.out.println(date2 == date);       // false     ���ò�һ����ֻ�Ǹ�������
        System.out.println(date2.equals(date));  // true      �������

        int[] a = new int[]{1,2};
        int[] a1 = a.clone();
        System.out.println(a == a1);            // false
        System.out.println(a.equals(a1));       // false  �������Object��ԭʼequals�������õ��Ǿ������ return (this == obj);
        a1[0] = 3;
        System.out.println(a[0]);               // 1      clone����ԭ���󣬲���ı�ԭ����
        System.out.println(a1[0]);              // 3

        // �Զ�����ʵ�ֽӿ�
        InterfaceDefine outterClass =  new InterfaceDefine();
        MyInterface myInterface = outterClass.new MyInterface(2);
        MyInterface myInterface1 = outterClass.new MyInterface(3);

        System.out.println(myInterface.compareTo(myInterface1));                      // -1
        try {
            MyInterface copy = (MyInterface)myInterface.clone();
            System.out.println(copy == myInterface);                                  // false
            /** cloneʵ�ֵ���ǳ���ƣ��������������������ͬ�����ã������Ҫ�Լ���дcloneʵ�֣����������������Ҳ���´����� **/
            System.out.println(copy.obj == myInterface.obj);                          // true
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }

        MyInterface2 myInterface2 = outterClass.new MyInterface2(2);
        MyInterface2 myInterface3 = outterClass.new MyInterface2(3);

        System.out.println(myInterface2.compareTo(myInterface3));                     // -1
        try {
            System.out.println( (MyInterface2)myInterface2.clone() == myInterface2);  // false
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    class MyInterface implements Cloneable, Comparable<MyInterface>{
        int value;
        MyInterface2 obj = new MyInterface2(6);

        public MyInterface(int value){
            this.value = value;
        }

        public Object clone() throws CloneNotSupportedException {
            /** ��JVM����ƽ̨ʵ�ֵģ�����ֻ����ø����super.clone() **/
            return super.clone();
        }

        @Override
        public int compareTo(MyInterface o) {
            if (o.value < value) return 1;
            else if (o.value > value) return -1;
            else return 0;
        }
    }

    class MyInterface2 implements Cloneable, Comparable{
        int value;

        public MyInterface2(int value){
            this.value = value;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof MyInterface2) {
                if (((MyInterface2)o).value < value) return 1;
                else if (((MyInterface2)o).value > value) return -1;
                else return 0;
            }
            return 0;
        }
    }



}
