public class MathClass {
    public static void main(String[] args) {

        /**
         * double  Math.ceil(double)  向上取整 整double
         * 参数返回都是double
         * */
        double d1 = Math.ceil(1);   // 实参整数隐式转换成double，可以的
        System.out.println(d1);     // 1.0
        double d2 = Math.ceil(2.1);
        System.out.println(d2);     // 3.0
        double d3 = Math.ceil(-2.6);
        System.out.println(d3);     // -2.0

        /**
         * double  Math.floor(double)   向下取整 整double
         * 参数返回都是double
         * */
        double d4 = Math.floor(1);   // 实参整数隐式转换成double，可以的
        System.out.println(d4);     // 1.0
        double d5 = Math.floor(2.6);
        System.out.println(d5);     // 2.0
        double d6 = Math.floor(-2.1);
        System.out.println(d6);     // -3.0

        /**
         * double  Math.rint(double)   四舍六入 五返回对应的偶数，double
         * 参数返回都是double
         * */
        double d7 = Math.rint(1);   // 实参整数隐式转换成double，可以的
        System.out.println(d7);     // 1.0
        double d8 = Math.rint(2.2);
        System.out.println(d8);     // 2.0
        double d9 = Math.rint(2.6);
        System.out.println(d9);     // 3.0
        double d10 = Math.rint(2.5);
        System.out.println(d10);    // 2.0
        double d11 = Math.rint(5.5);
        System.out.println(d11);    // 6.0
        double d12 = Math.rint(-2.1);
        System.out.println(d12);     // -2.0
        double d13 = Math.rint(-2.8);
        System.out.println(d13);     // -3.0
        double d14 = Math.rint(-6.5);
        System.out.println(d14);     // -6.0
        double d15 = Math.rint(-5.5);
        System.out.println(d15);     // -6.0

        /**
         * 1、int  Math.round(float)   float加0.5，返回一个向下取整的int
         * 2、long  Math.round(double)   double加0.5，返回一个向下取整的long
         * */
        int n1 = Math.round(1.5f);
        System.out.println(n1);     // 2
        int n2 = Math.round(1.8f);
        System.out.println(n2);     // 2
        int n3 = Math.round(2.3f);
        System.out.println(n3);     // 2
        int n4 = Math.round(-1.5f);
        System.out.println(n4);     // -1
        int n5 = Math.round(-1.8f);
        System.out.println(n5);     // -2
        int n6 = Math.round(-2.0f);
        System.out.println(n6);     // -2

        long l1 = Math.round(1.5);
        System.out.println(l1);     // 2
        long l2 = Math.round(1.8);
        System.out.println(l2);     // 2
        long l3 = Math.round(2.3);
        System.out.println(l3);     // 2
        long l4 = Math.round(-1.5);
        System.out.println(l4);     // -1
        long l5 = Math.round(-1.8);
        System.out.println(l5);     // -2
        long l6 = Math.round(-2.1);
        System.out.println(l6);     // -2

        /**
         * 1、int Math.max(int, int) | long Math.max(long, long) | float Math.max(float, float) | double Math.max(double, double)
         * 2、int Math.min(int, int) | long Math.min(long, long) | float Math.min(float, float) | double Math.min(double, double)
         * 3、int Math.abs(int) | long Math.abs(long) | float Math.abs(float) | double Math.abs(double)
         * */

        int max1 = Math.max(1, 2);
        System.out.println(max1);     // 2
        long max2 = Math.max(-1l, -2l);
        System.out.println(max2);     // -1
        float max3 = Math.max(-1.2f, -2.3f);
        System.out.println(max3);     // -1.2
        double max4 = Math.max(1.2, 2.3);
        System.out.println(max4);     // 2.3

        int min1 = Math.min(1, 2);
        System.out.println(min1);     // 1
        long min2 = Math.min(-1l, -2l);
        System.out.println(min2);     // -2
        float min3 = Math.min(-1.2f, -2.3f);
        System.out.println(min3);     // -2.3
        double min4 = Math.min(1.2, 2.3);
        System.out.println(min4);     // 1.2

        int abs1 = Math.abs(-1);
        System.out.println(abs1);     // 1
        long abs2 = Math.abs(-2l);
        System.out.println(abs2);     // 2
        float abs3 = Math.abs(-1.2f);
        System.out.println(abs3);     // 1.2
        double abs4 = Math.abs(-2.3);
        System.out.println(abs4);     // 2.3

        /**
         * 指数，乘方，除方等，参数和返回都是double
         * */
        double v1 = Math.exp(1);
        System.out.println(v1);    // e^1 = e 2.718281
        double v2 = Math.log(Math.E);
        System.out.println(v2);    // loge(Math.E) = 1.0
        double v3 = Math.log10(100.0);
        System.out.println(v3);    // log10(100) = 2.0
        double v4 = Math.pow(2.0, 3);
        System.out.println(v4);    // 2^3 = 8.0
        double v5 = Math.sqrt(36);
        System.out.println(v5);    // 36除方 = 6.0

        System.out.println("hhh");    // 36除方 = 6.0

    }
}
