public class ProgramControl {
    public static void main(String[] args) {
        /** switch  ()内是一个能隐式转换成int，short，byte，char的表达式 case具有()的相同数据类型，不能有变量
         *  这里说的()和case相同类型，是case的值可以隐式转换成()的类型就算
         */
        switch (1) {
            case 1:
                System.out.println(1);   // 1
                break;
        }

        switch (1) {
            case (byte)1:
                System.out.println(1);   // 1
                break;
        }

        switch (1) {
            case (short)1:
                System.out.println(1);   // 1
                break;
        }

        // 在隐式转换里，如果已经声明了类型，大范围类型转小范围类型必须强制转，但是用直接量，根据所占字节数，如果在小范围内，就不用强制转

        // case的2会转换成short和switch里的(short)2)比较
        switch ((short)2) {
            case 2:
                System.out.println(2);   // 2
                break;
        }

        /**
        // 222222隐式转转成short超限了，会报错
        switch ((short)2) {
            case 222222:
                System.out.println(2);   // 2
                break;
        }
        **/

        // case的3会转换成byte和switch里的(byte)2)比较
        switch ((byte)3) {
            case 3:
                System.out.println(3);   // 3
                break;
        }

        /**
        // 300隐式转转成byte超限了，会报错
        switch ((byte)3) {
            case 300:
                System.out.println(3);   // 3
                break;
        }
        **/

        switch ('a') {
            case 'a':
                System.out.println('a');   // a
                break;
        }

        switch ((char)65) {
            case 'A':
                System.out.println('A');   // A
                break;
        }

        switch ('A') {
            case 65:
                System.out.println('A');   // A
                break;
        }

        int n = 65;
        switch (n) {
            case 'A':
                System.out.println(n);   // 65
                break;
        }

        // switch里不能是long，浮点，boolean 报错
//        switch (65L) {
//            case 65L:
//                System.out.println('A');   // A
//                break;
//        }

    }

}
