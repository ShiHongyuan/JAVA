public class NumberOperation {
    public static void main (String[] args) {
        /**�Զ�װ����Զ�����**/
        int x = new Integer(3); System.out.println(x);     // 3
        // �����ȽϺ������ʱ����int��Integer���Զ�����
        System.out.println(new Integer(3) + new Integer(3));  // int 3 + int 3 = 6
        System.out.println(new Integer(2) < new Integer(3));  // int 2 < int 3 = true

        /**��װ�� java.lang.*
         * java.lang.Character Boolean implements java.lang.Comparable extends java.lang.Object
         * java.lang.Integer Short Long Double Float Byte implements java.lang.Comparable extends java.lang.Number
         * ����д��Object�� toString() �� equals() ����
         * ��ʵ����Comparable�ӿڵ�compareTo()����
         */


        /** �ӿ� java.lang.Comparable **/
        // public int compareTo(Object)

        /** ������ java.lang.Number **/
        // byte byteValue() �Ѽ̳� Number �İ�װ������ת���� ������������byte
        // int intValue() �Ѽ̳� Number �İ�װ������ת���� ������������int
        // short shortValue() �Ѽ̳� Number �İ�װ������ת���� ������������short
        // long longValue() �Ѽ̳� Number �İ�װ������ת���� ������������long
        // float floatValue() �Ѽ̳� Number �İ�װ������ת���� ������������float
        // double doubleValue() �Ѽ̳� Number �İ�װ������ת���� ������������double

        /** ��װ��û���޲ι��췽����������ڲ�ֵ���ɱ䣬����װ���ʵ�����ɱ� **/
//        Integer integer = new Integer();   // ���뱨��û���޲ι��췽��


        /** ��װ��ʵ�δ��ݵ�Ҳ������ֵ�������ں����ڲ����new Integer()��ı�����ֵ������=6Ҳ��ı�����ֵ�����������������ͬ�����Զ�����һ�����ƴ��εĽ�� **/
        Integer integer1 = 5;
        Integer integer2 = 5;
        System.out.println(integer1 == integer2);   // true  ָ��ͬһ�����������Ա���������ֵ��ͬ

        Integer integer11 = new Integer(5);
        Integer integer22 = new Integer(5);
        System.out.println(integer11 == integer22); // false


        /** ��װ�඼��д��Object�� toString() �� equals() ���� **/

        /** ��װ�඼ʵ����Comparable�ӿڵ�compareTo()���� **/




        /** java.lang.Integer **/
        // ���췽��
        Integer integerValue1 = new Integer(2);  // 2
        Integer integerValue2 = new Integer("2");   // 2

        // �̳�Number�ķ���



        // ��չ��������̬����
        System.out.println(Integer.MAX_VALUE);
        System.out.println(integerValue1.MIN_VALUE);

        // ��չ���ĸ���̬����
        // ת����Integer�ķ���
        Integer integerTemp = Integer.valueOf(1);
        System.out.println(integerTemp);

        integerTemp =  Integer.valueOf("1");
        System.out.println(integerTemp);

        integerTemp = Integer.valueOf("1.23");
        System.out.println(integerTemp);

        integerTemp = Integer.valueOf("3", 2);
        System.out.println(integerTemp);

        // ת����int�ķ���
        int intTemp = Integer.valueOf(1);
        System.out.println(integerTemp);

        intTemp =  Integer.valueOf("1");
        System.out.println(integerTemp);

        intTemp = Integer.valueOf("1.23");
        System.out.println(integerTemp);

        intTemp = Integer.valueOf("3", 2);
        System.out.println(integerTemp);


    }
}
