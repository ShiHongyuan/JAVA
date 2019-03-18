public class MemStackAndHeap {
    public static void stackTest() {
        int a = 1;
        int b = 1;
        System.out.println("�����������ͣ��̵߳�ջ�д�Ż����������ͱ������������зű��������ݣ�����ջ���ݿ��Թ���" +
                "��ͬ�ı���ָ��ͬһ������" + (a == b)); // True

        String s1 = "1";
        String s2 = "1";
        System.out.println("String���ǻ����������ͣ�ջ�д������ֵ���ã��������д������ֵ��������ջ���ݿ��Թ���" +
                "��ͬ������ָ��ͬһ������ֵ" + (s1 == s2)); // True

        String ss1 = new String("1");
        String ss2 = new String("1");
        System.out.println("new��������ڶ��п����ڴ�ռ䣬ջ�д�Ŷ���ľ��������������ñ����������ڶ��еĶ��󲻿��Թ���" +
                "ջ�в�ͬ�Ķ�����ָ����в�ͬ�Ķ���" + (ss1 == ss2)); // False

        System.out.println(s1 == ss1); // False

        /**
         * ����ͨ�� ��ֵ������ ����ʵ�δ��βε�ԭ��
         * 1�������������ͺͳ�����ֵ��ֵ��ֵ
         * 2�����������õ�ַ��ֵ���������µĿռ�
         */
        String s11 = "123";
        String s22 = s11;                  // ֵ���ƣ�s1��s2�����ָ�����صĲ�ͬ�ĵ�ַ
        System.out.println(s11 == s22);    // true

        String s111 = new String("123");
        String s222 = s111;                // ���õ�ַ���ƣ�s11��s22�������ͬ��ָ����ڴ�ĵ�ַ��û�п����µĿռ�
        System.out.println(s111 == s222);  // true

        System.out.println(s11 == s222);   // false

    }

    public static void stringEqualTest() {
        String ss3 = new String("2");
        String ss4 = new String("2");
        System.out.println("��װ���ͣ���ͬ������ָ��ͬ�ĶѶ���==��ʾ���ö�����ͬ����True������false " + (ss3 == ss4)); // False

        String ss5 = new String("2");
        String ss6 = new String("2");
        System.out.println("��װ���ͣ���ͬ�ĶѶ�����ַ�������ֵ��ȣ�equals��ʾ��������ֵ��ͬ����True " + (ss5.equals(ss6))); // True


    }

    public void objectEqualTest() {
        CustomObject obj1 = new CustomObject();
        CustomObject obj2 = new CustomObject();
        DecimalToBinary d = new DecimalToBinary();
        System.out.println("�Զ���Object����ͬ������ָ��ͬ�ĶѶ���==��ʾ���ö�����ͬ����True������false " + (obj1 == obj2)); // False
        System.out.println("�Զ���Object����ͬ�ĶѶ������ȣ�equals��==һ����ʾ���ö�����ͬ����True������false " + (obj1.equals(obj2))); // False
    }

    class CustomObject {
        int a = 1;
        int b = 2;
    }


    public static void finalTest() {
        // ��������ȷ��B1 = "b"��������ȷ���ĳ�����ֱ��˫���Ŷ���ĳ���û�������ˣ����ܱ����b1 = "ab"���ͻ���볣����;
        String a1 = "ab";
        final String B1 = "b";
        String b1 = "a" + B1;
        System.out.println((a1 == b1)); //result = true

        // �����ڲ���ȷ��bb = "b"��b = "a" + bb����String��new��ʽ�����µĶ����ַ���ƴ����String����ķ���
        String a = "ab";
        String bb = "b";
        String b = "a" + bb;
        System.out.println((a == b)); //result = false

        // �����ڲ���ȷ��getBB() = "b"��b = "a" + B2����String��new��ʽ�����µĶ����ַ���ƴ����String����ķ���
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
         * �߳�ջ�ڴ����ŵ��ǼĴ��ٶȿ졢�����ڼĴ�����ջ���ݿ��Թ���ȱ�������ݵĴ�С�����������Ǳ���ȷ���ģ�������
         * ��ջ�ڴ��еı��������ñ����˳����������Java���Զ��ͷŵ�Ϊ�ñ�����������ڴ�ռ䣬���ڴ�ռ�����������������á�
         * ���ڹ�������ݣ���û������ָ������ʱ��������ݾͻ���ʧ��������ȴ����������������ա�
         * ջ�еı���ָ����ڴ��еı����������Java�е�ָ��
         * �߳�˽��
         ****************************************************************************************/


        /****************************************************************************************
         * ���ڴ����������new�����Ķ�������顣�������ʼ�����Բ�new������Ҳ�Ǽ̳ж���
         * �ѵ������ǿ��Զ�̬�ط����ڴ��С��������Ҳ�������ȸ��߱���������Ϊ����������ʱ��̬�����ڴ�ģ���ȱ���ǣ�����Ҫ������ʱ��̬ �����ڴ棬��ȡ�ٶȽ�����
         * �ڶ��з�����ڴ棬��Java��������Զ�����������������GC����
         * ����Ͷ�����û�����ñ���ָ������ʱ�򣬲ű�Ϊ�����������ڱ�ʹ�ã����� Ȼռ���ڴ�ռ䲻�ţ�������һ����ȷ����ʱ�䱻�������������ߣ��ͷŵ�������Ҳ�� Java �Ƚ�ռ�ڴ��ԭ��
         *
         * �ں��������β�ʱ��ʵ���Ƕ��ڴ�ʱ��ʵ�λ������ı䣬ʵ���ǳ����غ�ջ��ʱ��ʵ�β������ı�
         * class�ĳ�Ա���������ڶ���
         * �̹߳���
         ****************************************************************************************/

        /****************************************************************************************
         * �������ں�����method area�У��ڱ����ھ�ȷ���ˣ��߳�ջ�ڴ棬���ڴ涼�������ڲſ���ȷ��������method area��
         * ���ֱ�ӳ�����string,integer�� floating point�������Ͷ��������ͣ��ֶκͷ����ķ������ã�����ȡ�
         * final���εı�����ջ�У�ֵ���ڳ�������
         * �̹߳���
         ****************************************************************************************/

        /*****
         * �����ַ�����һ�������װ����
         * ���������ö��Ǵ洢��ջ�еģ�����Ǳ������Ѿ�������(ֱ����˫���Ŷ����)�ľʹ洢�ڳ������У�����������ڣ�new�����ģ�����ȷ���ľʹ洢�ڶ��С�
         * ����ͨ��new����һ���ַ���������Ϊ��china����ʱ������ȥ�������в����Ƿ��Ѿ����ˡ�china���������û�����ڳ������д���һ�����ַ�������Ȼ������ٴ���һ���������дˡ�china������Ŀ�������
         * ��Ҳ�����е������⣺Strings=newString(��xyz��);������������һ���������������������ԭ��û�С�xyz��,��������
         * ��Ȼ��ֱ����˫���Ŷ�����ַ�����Ϊ��china�����ı�������ֱ��ָ�����صġ�china������
         */

        /********************************************************
         * ������������
         * int, short, long, byte, float, double, boolean, char
         * ���ڻ����������ͣ������ֵ�ڳ������У��������߳�ջ��
         ********************************************************/
        MemStackAndHeap.stackTest();



        // ��װ���͵� == �� equals
        stringEqualTest();

        // �Զ���object�� == �� equals
        MemStackAndHeap memStackAndHeap = new MemStackAndHeap();
        memStackAndHeap.objectEqualTest();


        memStackAndHeap.finalTest();

        Object o1 = null;
        if (o1 == null) {
            System.out.println("== true");
        }

        int c = 666;
        Integer a = 666;   // ʹ�ó������еĶ���
        Integer b = new Integer(666); // �����¶���
        Integer d = new Integer(666); // �����¶���
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

        System.out.println(a == c); // true  Integer��int�Ƚ�ʱ���Զ�����������ֵ�Ƚϣ�����a��c�ǲ�ͬ�ĳ����ض���
        System.out.println(b == c); // true  Integer��int�Ƚ�ʱ���Զ�����������ֵ�Ƚϣ�����b��c�ǲ�ͬ�Ķ���
        System.out.println(b == a); // false Integerֱ�ӱȽϣ�һ���ڳ����أ�һ���ڶ��У���ͬ��Integer���󣬷���false
        System.out.println(d == b); // false Integerֱ�ӱȽϣ��ڶ��еĲ�ͬ��Integer���󣬷���false

        Integer i11 = 333;
        Integer i22 = 333;
        System.out.println(i11 == i22);// ���false  �ڳ���-128-127ʱ��Integer���ڳ������д�������

        Integer i33 = 3;
        Integer i44 = 3;
        System.out.println(i33 == i44);// true ��-128-127��Χ��ʱ��Integer����intһ��ʹ�ó����صĻ�����󣬲��ᴴ���µĶ���


        // ������������ʵ����ֵ���ݣ�String�Ͱ�װ���ʹ��ݵ�ʵ���ǵ�ַ����������������ָ���ֵ��final�ģ������ڻ��½������൱��ֵ����
        // ���˻����������ͣ�String�Ͱ�װ�����ⶼ�����ô��ݣ��ı�ʵ�ζ����ֵ��ԭֵҲ����

        Integer intA = new Integer(6);
        Integer intB  = IntegerTrans(intA);
        System.out.println(intA); // 6
        System.out.println(intA == intB);  // FALSE

        String sB = "6";
        String sBB = StringTrans(sB);
        System.out.println(sB);  // 6
        System.out.println(sB == sBB);  //  FALSE

        memStackAndHeap.compareObjectTrans();


        // ÿһ��ѭ���������µĻ��������������ã���ѭ����ÿһ����󶼻�������һ�����ɵ����ö���
        // ��һ��ѭ������ǰ���ö���test = null���ᱻgc��������
        for (int i = 0; i< 3; i++) {
//            System.out.println(testValue == testValue1);   // ����testValue��testValue1������
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
        // ֻҪ��new���ͻ��������ɶ�������·������ã�ԭ���û��ڣ�ָ��ԭ�����������ɵ���������һ����ָ���¶���
        // Integer��(String���ͺͰ�װ����)��value�ֶ���final�ģ�һ��integer��(String���ͺͰ�װ����)����֮������ֵ�Ͳ��ܱ��޸ģ��� index++ ��ʱ��Integer(String���ͺͰ�װ����)�Ǵ���һ���µ���
        // �ڷ�����һ������ָ����һ���µĶ����µ�����ָ���µĶ��󣬳��˷�����ԭ�������������ǲ���
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
