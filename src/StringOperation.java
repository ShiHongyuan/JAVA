public class StringOperation {
    public static void main(String[] args) {
        /**
         * string���ַ�����������
         */
        // ����"", ����Ҳ���ᱨ��
        System.out.println("123".substring(0,0));

        // indexOf()
        System.out.println("".indexOf(""));   // 0
        System.out.println("ll".indexOf("")); // 0
        System.out.println("".indexOf("ll")); // -1
        System.out.println("hello".indexOf("ll"));   // 2

        // startsWith ��ƫ���� �� ����ƫ����
        System.out.println("".startsWith(""));                    // true
        System.out.println("ll".startsWith(""));                  // true
        System.out.println("ll".startsWith("", 0));   // true
        System.out.println("".startsWith("ll"));                  // false
        System.out.println("".startsWith("ll", -1));  // false
        System.out.println("hello".startsWith("ll"));             // false
        System.out.println("hello".startsWith("ll", 2));  // true

        System.out.println("1 ".charAt(1));   //' ' �հ��ַ�

        /**
         * string�����ӷ�����
         */
        // ����֮һ���ַ������������ӷ�����һ����ת�����ַ��������ӣ����������ǣ����Ǽӷ������
        System.out.println("string=" + 1 + 2);   //string=12  û�����ţ������Ҽ��㣬���������ӷ�
        System.out.println("string=" + (1 + 2)); //string=3   �����ţ�����������ģ�������������ӣ��ٺ��������

        /**
         * string��ת���ֺ�������
         */
        int n1 = Integer.parseInt("123");
        double n2 = Double.parseDouble("123");
        double n3 = Integer.valueOf("123");
        double n4 = Double.valueOf("123");

        System.out.println(n1); // 123
        System.out.println(n2); // 123.0
        System.out.println(n3); // 123.0
        System.out.println(n4); // 123.0



    }
}
