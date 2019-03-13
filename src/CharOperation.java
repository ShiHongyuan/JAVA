import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class CharOperation {
    public static void main(String[] args) {
        /**
         * char���ֽ���
         * 1�����룺��ʵ��char��String����ʱ���ڴ��ڲ�ʹ�õı��룬��ϵͳû��ָ�����뼯��ʱ��javaĬ���ǽ�utf-16��ͳһ���룩��Ϊ�ڴ���ַ��洢��ʽ�ģ�
         *    utf-16�����ֽڶ������룬�������Է����������������ԣ����Դ洢char��stringʱ�����ֺ�Ӣ����ĸ����2���ֽڡ�
         *
         * 2�����룺��Java�ж�ȡ��д���ַ��ļ�ʱ����ָ���ַ����뼯����ΪϵͳĬ�ϱ��뼯��ָ������Щ���뼯�������룬��ͬ����ᵼ���ַ���ռ�ֽ����ǲ�ͬ���������У�
         * (1) utf-8: Ӣ���ַ���ռ�ֽ���:1  �����ַ���ռ�ֽ���:3
         * (2) GBK: Ӣ���ַ���ռ�ֽ���:1  �����ַ���ռ�ֽ���:2
         * (3) ASCII��: ֻ�ܱ���Ӣ���ַ�����ռ�ֽ���:1
         */

        // ��ȡ����ϵͳ���õ�Ĭ�ϵı��뼯
        String defaultCharsetName = Charset.defaultCharset().displayName();
        System.out.println("defaultCharsetName:" + defaultCharsetName);    // GBK

        // 1��Unicode ͳһ����
        char uniCode = '\u6b22';   // ��
        char uniCode2 = '\u8FCE';  // ӭ
        char uniCode3 = '��';      // ��
        Character uniCode4 = new Character('\u6B22'); // ��
        char uniCode5 = '\u0041';  // A ������Ӣ����ĸ��GBK��Ӣ���ַ�ռ�ֽ�����1
        char uniCode6 = '\u00FF';
        char uniCode7 = '\u0101';

        System.out.println(String.valueOf(uniCode) + String.valueOf(uniCode2)); // ��ӭ
        System.out.println(String.valueOf('\u0000') + String.valueOf('\u00ff') + String.valueOf('\u0100') + String.valueOf('\u0101')); // ??��
//        System.out.println(Character.SIZE);
        System.out.println(String.valueOf(uniCode).getBytes().length);   // 2 String���ڲ��洢���ǰ��ַ��Ĵ洢���������ģ�����һ���ַ���String����ռ�õ��ֽ�����char��ͬ
        System.out.println(String.valueOf(uniCode3).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode4).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode5).getBytes().length);  // 1
        System.out.println(String.valueOf('\u00FF').getBytes().length);  // 1 ?
        System.out.println(String.valueOf('\u0100').getBytes().length);  // 1 ?
        System.out.println(String.valueOf('\u0101').getBytes().length);  // 2 ��


        char uniCode8 = 'A';
        System.out.println(String.valueOf(uniCode8).getBytes().length);  // 1 Ӣ����ĸ��GBK��Ӣ���ַ�ռ�ֽ�����1
        try {
            System.out.println(String.valueOf(uniCode).getBytes("UTF-8").length);  // 3 ���� utf-8: Ӣ���ַ���ռ�ֽ���:1  �����ַ���ռ�ֽ���:3
            System.out.println(String.valueOf(uniCode5).getBytes("UTF-8").length); // 1 Ӣ��

            System.out.println(String.valueOf(uniCode2).getBytes("utf-16").length); // 4 ����
            System.out.println(String.valueOf(uniCode8).getBytes("utf-16").length);// 4 Ӣ��

            System.out.println(String.valueOf(uniCode).getBytes("ASCII").length);  // 1 ���� ASCII ȡ�˵�λ��һ���ֽ�
            System.out.println(String.valueOf(uniCode6).getBytes("ASCII").length); // 1 Ӣ��
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        /**
         * char���������������������һ����������Ͳ���
         */
    }
}

