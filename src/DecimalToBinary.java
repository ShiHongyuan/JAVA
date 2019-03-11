public class DecimalToBinary {
    public static int[] fun(int n) {
        int[] bits = new int[32];
        int i = 0;
        // �Ž�ȥ��ʱ���ǰ��ո�λ����ʼ����λ�ŵģ���2�ı�����˵����λ����Ҫ1����������˵����λ��Ҫ1��
        while (n>0) {
            bits[i++] = n%2;
            n/=2;
        }
        // ���������෴˳�������
        for (i=i-1; i>=0 ;i--) {
            System.out.printf("%d", bits[i]);
        }
        return bits;
    }

    public static int fun1(int n) {
        return Integer.bitCount(n);
    }

    public static int fun2(int n) {
        String s = Integer.toBinaryString(n);
        return s.replaceAll("0","").length();
    }

    public static int fun3(int n) {
        return (int)Integer.toBinaryString(n).chars().filter(c -> c == '1').count();
    }

    public static void main(String[] args) {
        DecimalToBinary.fun(1234);
        System.out.println();
        System.out.println(DecimalToBinary.fun1(1234));
        System.out.println(DecimalToBinary.fun2(1234));
        System.out.println(DecimalToBinary.fun3(1234));
    }

}

