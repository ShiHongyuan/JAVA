import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleFloatIntLongShortOperation {
    public static void main(String[] args) {

        float f = (float)1.3; // ��Ĳ�����ʽת����С�ģ�1.3Ĭ����double��������ʽת����float
        float f1 = 1.3f;      // 1.3fĬ����float,����ת��

        // ���;�û��Ĭ�ϵ����ͣ�ֻ�и�����Ĭ����double
        long l = 1;
        short s = 2;
        byte b = 3;

        int i = 0;
        double d = i; // ��ʽת��
        System.out.println(d == i);  // true  i��ʽת����double
        System.out.println(d == 0);  // true  0��ʽת����double

        int i1 =1;
        double d1 = i1; // ��ʽת��
        System.out.println(d1 == i1);  // true  i1��ʽת����double
        System.out.println(d1 == 1);  // true   1��ʽת����double

        double dd = 1.9;
        float ff = 1.9f;
        System.out.println((int)dd); // 1  ����תint��ֱ��ȥ��С������
        System.out.println((int)ff); // 1  ����תint��ֱ��ȥ��С������


        System.out.println(2.00-1.10); // 0.8999999999999999   double - double������0.1�洢����ȷ�����Խ������ȷ��ѭ�������ֱ����������
        System.out.println(1 - 0.9);   // 0.09999999999999998  double - double������0.9�洢����ȷ�����Խ������ȷ
        System.out.println(1.00 - 0.00); // 1.0  double - double�������������ִ洢�Ǿ�ȷ�ģ�0.0���޽ӽ���0�����Խ���������ȽϾ�ȷ������ʵ���ϴ洢��Ҳ���Ǿ�ȷ�ģ���Ϊ0����ȷ

        System.out.println(1f - 0.9f);     // 0.100000024  float-float���õ���Ҳ��float���洢0.9Ҳ����ȷ�������ø���
        System.out.println(2.00f - 1.10f); // 0.9          float-float���õ���Ҳ��float���洢0.1Ҳ����ȷ


        System.out.println(2.00-1.10);   // 0.8999999999999999
        System.out.println(2.0 - 1.10f); // 0.8999999761581421  double-double��1.10f��ʽת����double������1.10f������ǲ���ȷ�Ĵ洢��
                                         // ת����doubleʱ����1.10����double�Ĵ洢�ֲ�һ���ˣ����Խ��Ҳ��һ����

        double d2 = 0.9;
        float f2 = 0.9f;
        System.out.println(d2 == f2);  // false float����ʽת����double��Ƚϣ���������float�洢��0.9����ȷ��ת����doubleʱ��
                                       // ��0.9����double�Ĵ洢Ҳ��һ�����Ƚ��Ƕ����ƵıȽϣ����Բ���
        System.out.println(d2 < f2);   // false  ��Ϊf2��ʽת����double�������ֵ��0.8999999761581421��double����0.9�Ķ�����ֵ��0.8999999999999999
        System.out.println(d2 > f2);   // true   ͬ������f2=0.8999999761581421��d2=0.8999999999999999 f2 < d2

        double d3 = 1;
        float f3 = 1f;
        System.out.println(d3 == f3);  // true  float����ʽת����double��Ƚϣ�����float��double�������Ĵ洢����һ���ģ���ȷ�ģ��������

        System.out.println(0.9f);         // 0.9
        System.out.println(0.9d);         // 0.9
        System.out.println((double)0.9f); // 0.8999999761581421  ͬ����floatת����double��ֵ�Ͳ�һ����


        // ��������������������
        System.out.println(5.5%10); // 5.5


        /** ���Ͽ�֪��float��double���ʺ����ڸ������ļ���ͱȽϣ�һ����BigDecimal
         * float��double������Ϊ���ڹ�����ֵ��Χ���ṩ��Ϊ��ȷ�Ŀ��ٽ��Ƽ����������Ƶġ�Ȼ��������û���ṩ��ȫ��ȷ�Ľ�������Բ�Ӧ�ñ�����Ҫ��ȷ����ĳ���
         * �ڴ��������ҵ�����У�float��double���ʺ����ڼ���ͱȽϣ�һ����BigDecimal�����о�ȷ����
         *
         * java.math.BigDecima
         */

        /**
         * BigDecimal���췽��
         */
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal bString = new BigDecimal("2.3");
        BigDecimal bDouble = new BigDecimal(2.3);

        System.out.println("bigDecimal=" + bigDecimal); // 2 ��int��ʾ��ʽת����BigDecimal
        System.out.println("bDouble=" + bDouble);       // 2.3 ��String��ʾ��ʽת����BigDecimal ǿ�ҽ���ʹ��
        System.out.println("bString=" + bString);       // 2.2999999999 ��double��ʾ��ʽת��ΪBigDecimal *������ʹ��
                                                        // ��Ϊdouble����ȷ������ȥ�Ĳ�һ����2.3��bDouble�Ͳ�һ����2.3

        // ������double��Ϊ����Դʱ�İ취
        BigDecimal bDouble1 = BigDecimal.valueOf(2.3);
        BigDecimal bDouble2 = new BigDecimal(Double.toString(2.3));

        System.out.println("bDouble1=" + bDouble1);     // 2.3
        System.out.println("bDouble2=" + bDouble2);     // 2.3


        double test = 2.3;
        BigDecimal bDouble3 = BigDecimal.valueOf(test);
        BigDecimal bDouble4 = new BigDecimal(Double.toString(test));

        System.out.println("bDouble3=" + bDouble3);     // 2.3
        System.out.println("bDouble4=" + bDouble4);     // 2.3

        /**
         * BigDecimal�Ӽ��˳�����
         */
        BigDecimal bigDecimal1 = new BigDecimal("4.5");
        BigDecimal bigDecimal2 = new BigDecimal("1.5");
        System.out.println("a + b =" + bigDecimal1.add(bigDecimal2));      // 6.0
        System.out.println("a - b =" + bigDecimal1.subtract(bigDecimal2)); // 3.0
        System.out.println("a * b =" + bigDecimal1.multiply(bigDecimal2)); // 6.75
        System.out.println("a / b =" + bigDecimal1.divide(bigDecimal2));   // 3

        // ���˳���ʵ���ն����ص���һ���µ�BigDecimal������ΪBigInteger��BigDecimal���ǲ��ɱ�ģ�immutable���ģ��ڽ���ÿһ������ʱ���������һ���µĶ���
        System.out.println(bigDecimal1.add(bigDecimal2)); // 6.0
        System.out.println(bigDecimal1); // 4.5 bigDecimal1��bigDecimal2����û�б��
        System.out.println(bigDecimal2); // 1.5 bigDecimal1��bigDecimal2����û�б��

        /** ע�� ��������divide������������public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
         *  �ڶ���������ʾС�������λ����������������ʾ����ģʽ��
         *
         *  java.math.RoundingMode
         *
         *  RoundingMode.CEILING  //�������������
         *  RoundingMode.FLOOR    //�����������
         *
         *  RoundingMode.DOWN     //��0��������
         *  RoundingMode.UP       //��Զ��0�ķ�������
         *
         *  RoundingMode.HALF_DOWN    //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,�������������������, ����1.55 ����һλС�����Ϊ1.5
         *
         *  RoundingMode.HALF_EVEN    //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,������������������λ����������ʹ��ROUND_HALF_UP�������ż����ʹ��ROUND_HALF_DOWN
         *
         *  RoundingMode.HALF_UP      //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,�������������������, 1.55����һλС�����Ϊ1.6����������������
         *
         *  RoundingMode.UNNECESSARY    //�������Ǿ�ȷ�ģ�����Ҫ����ģʽ��Ĭ��ģʽ
         */

        // divide��RoundingModeĬ����ROUND_UNNECESSARY��scaleĬ����1λС���������ܳ������ͳ�����������������С�������ԣ���Ҳ��һ��BigDecimal
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.5")));   // 3
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("3")));     // 1.5
        // System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3")));   // ����������ûָ��RoundingMode���ͻᱨ��
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3"), RoundingMode.HALF_UP)); // 3.5 ��������ָ����RoundingMode���������룬scaleĬ�ϱ���һλС��
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3"), 2, RoundingMode.HALF_UP)); // 3.46��ָ��scale����2λС��


        /**
         * BigDecimal�ضϡ��������룬setScale������С��λ�ǲ���ȱʡ�ģ�RoundingModeȱʡ��UNNECESSARY
         */
        BigDecimal bigDecimal3 = new BigDecimal("3.265");
        // �ضϳ�4λС��
        System.out.println(bigDecimal3.setScale(4)); // 3.2650  RoundingModeȱʡĬ����UNNECESSARY
        // �ضϳ�2λС����RoundingModeȱʡUNNECESSARY���ᱨ��
        // System.out.println(bigDecimal3.setScale(2)); // ��������2λ�������룬�ᱨ��
        // �ضϳ�2λС����������������
        System.out.println(bigDecimal3.setScale(2, RoundingMode.HALF_UP)); // 3.27 ָ����RoundingMode������2λ��������

    }
}
