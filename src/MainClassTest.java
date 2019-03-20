public class MainClassTest {
    public static void main(String[] args) {
        /** 向main传递参数
         * 在命令行调用main时，传递字符串，main接收到会转换为字符串数组存在args里，命令行以空格区分不同的字符串对象
         * java MainClassTest abc 627 "shi hongyuan"
         * 传递的数值也会转换成字符串对象
         * 传递的字符串不需要双引号，但是如果有空格，需要双引号，不然命令行会认为是两个字符串对象
         */

        /**
         * 如果参数是*，必须用双引号括住 "*"，不然意思是代表当前目录下的所有文件
         * java MainClassTest "*"  args[0] = "*"
         * java MainClassTest *  args[0] = 文件名1  args[1] = 文件名2 args[2] = 文件名3 。。。。。。
         */


        /**
         * main不传参，args不是null，会创建一个空数组，new String[0]，args.length = 0
         */

        System.out.println(args.length);           // 0

        /**
         * 退出程序 System.exit(0)
         */
//        System.exit(0);  // 退出码是0   Process finished with exit code 0
        System.exit(1);  // 退出码是1   Process finished with exit code 1

    }
}
