public class FunctionDefine {
    public static void main(String[] args) {
        /**
         * if ��esle���Ͳ�һ����Ҫ����return��û��else����һ����Ҫ����return����Ȼ���뱨��
         * */
        testIF(2);

        /**
         * ʵ�δ��βΣ�ֻҪ��ʽת���ܵõ��������ͼ��ݣ��Ϳ��Դ���
         * */
//        testParameter(1.0, 2, '\u0001');      // 1.0 double��ʽת��int ����
//        testParameter(1, 2, 65);              // 65 int��ʽת��char ����
        testParameter(1, 2, '\u0001');  // 2  int��ʽת��double �ɹ�����

        /**
         * �������أ��β�˳��һ�����ǶԵģ����ǵ���ʱ��������ʽת�����ͻᱨ��
         * */
//        testOverload(1, 2); // ������֪��Ҫ���õ�����һ��

    }

    // û��else��û������return������
//    private int testIF(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        else if (n == 2) {
//            return 2;
//        }
//    }

    // ��else��û������return���ɹ�
    private static int testIF(int n) {
        if (n == 1) {
            return 1;
        }
        else {
            return 2;
        }
    }

    private static void testParameter(int n, double d, char c) {
        return;
    }

    private static void testOverload(int n, double d) {
        return;
    }

    private static void testOverload(double d, int n) {
        return;
    }
}
