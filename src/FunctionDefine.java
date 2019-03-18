public class FunctionDefine {
    public static void main(String[] args) {
        /**
         * if 有esle，就不一定需要最后的return，没有else，就一定需要最后的return，不然编译报错
         * */
        testIF(2);

        /**
         * 实参传形参，只要隐式转换能得到，就类型兼容，就可以传递
         * */
//        testParameter(1.0, 2, '\u0001');      // 1.0 double隐式转换int 报错
//        testParameter(1, 2, 65);              // 65 int隐式转换char 报错
        testParameter(1, 2, '\u0001');  // 2  int隐式转换double 成功调用

        /**
         * 方法重载，形参顺序不一样，是对的，但是调用时都可以隐式转换，就会报错
         * */
//        testOverload(1, 2); // 报错，不知道要调用的是哪一个

    }

    // 没有else，没有最后的return，报错
//    private int testIF(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        else if (n == 2) {
//            return 2;
//        }
//    }

    // 有else，没有最后的return，成功
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
