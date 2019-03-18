public class ArrayClass {
    public static void main(String[] args) {
        /**
         * 声明数组变量,没有分配空间，只有数组的引用，数组是null
         * */
        int[] a1; // 推荐使用
        int a2[];

        /**
         * 用new创建数组，初始化默认值，必须指定大小[n]，才能分配空间，空间分配后就不能更改
         * */
        // 初始化数组的大小后，元素被赋予默认值
        // int,long,short 默认值是0
        int[] n = new int[10];
        System.out.println(n[9]);  // 0
        long[] l = new long[10];
        System.out.println(l[9]);  // 0
        short[] s = new short[10];
        System.out.println(s[9]);  // 0

        // float,double默认值是0.0
        float[] f = new float[10];  // 0.0
        System.out.println(f[9]);
        double[] d = new double[10]; // 0.0
        System.out.println(f[9]);

        // char默认值是'\u0000'
        char[] c = new char[10];
        System.out.println(c[9]);    //  

        // boolean默认值是false
        boolean[] b = new boolean[10];
        System.out.println(b[9]);    // false

        // byte默认值是false
        byte[] by = new byte[10];
        System.out.println(by[9]);    // 0

        // 对象的默认值是null
        String[] ss = new String[10];
        System.out.println(ss[9]);    // null

        /**
         * new创建数组的同时初始化
         * */
        int[] a3 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        /**
         * 数组的简单的，不用new的初始化语法
         * */
        int[] a4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // new 可以声明和初始化分开
        int[] a5;
        a5 = new int[10]; // 必须有要分配个数
        // 不用new的 声明和初始化必须在一条语句里，不能分开
        int[] a6;
//        a6 = {1, 2, 3};  编译报错


        /**
         * 匿名数组 new的时候没有引用变量，直接初始化
         * */
        // int[]里面不能分配长度，否则编译报错
        testAnonymous(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
//        testAnonymous(new int[10] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}); // 编译报错
        for (int i: new int[] {1, 2, 3}) {
            System.out.println(i);       // 1 2 3
        }


        /**
         * 数组的遍历 for循环 或者 for-each循环（顺序访问）
         * */
        // char数组特例：打印整个数组可以直接打印，其他类型数组必须for遍历
        char[] chars = {'c', 'h', 'i', 'n', 'a'};
        System.out.println(chars);    // china
        System.out.println(chars.toString());    // 加toString()打印的是[I@60e53b93 -> hashcode值

        System.out.println(a3);                  // int[] 打印的是[I@5e2de80c -> hashcode值
        System.out.println(a3.toString());       // int[].toString() 打印的是[I@5e2de80c -> hashcode值

        // for-each循环（增强型for循环）对数组来说是顺序访问的  但是不能改变元素的值
        for (int i: new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}) {
            System.out.println(i);       // 1 2 3 4 5 6 7 8 9 10 顺序访问数组
        }


        /**
         * 数组的复制
         * 1、引用复制  -- 赋值语句
         * 2、内容复制  -- 复制后引用变量的值是不一样的，指向不一样的对象空间
         * （1）循环遍历复制
         * （2）java.lang.System.arraycopy
         * （3）clone
         * */
        // 引用复制  -- 赋值语句
        int[] a11 = {1, 2};
        int[] a22 = {2, 3};
        a22 = a11;
        System.out.println(a22 == a11); // true a11和a22是两个引用变量，但是值一样，原来的a22会被垃圾回收机制回收


        // 内容复制 java.lang.System.arraycopy函数
        int[] a33 = new int[5];
        java.lang.System.arraycopy(a11, 0, a33, 0, 2);
        System.out.println(a33 == a11); // false
        for (int i: a33) {
            System.out.println(i);       // 1 2 0 0 0  从a11的0开始复制2个到a33的0位置
        }

        java.lang.System.arraycopy(a22, 1, a33, 2, 1);
        for (int i: a33) {
            System.out.println(i);       // 1 2 2 0 0  从a22的1开始复制1个到a33的2位置
        }

        ArrayClass[] array1 = new ArrayClass[]{new ArrayClass(), new ArrayClass()};
        ArrayClass[] array2 = new ArrayClass[]{new ArrayClass(), new ArrayClass()};
        java.lang.System.arraycopy(array1, 0, array2, 0, 2);
        for (ArrayClass i: array2) {
            System.out.println(i);       // ArrayClass@5e2de80c ArrayClass@1d44bcfa 两实例的地址值
        }

        /**
         * 函数的变长参数
         * 1、传递一个可变长度的参数序列
         * 2、传递一个数组
         * 以上两种情况函数都自动接收为一个数组处理
         * */
        testVarargs(1.2, 2.3, 5.6);
        testVarargs(new double[] {2.2, 6.6});

        /**
         * java.util.Arrays 类 以下函数对基本数据类型、包装类型、String都有重载
         */
        // sort 排序 改变原来的数组
        char[] chSort = new char[] {'c', 'b', 'a'};
        java.util.Arrays.sort(chSort);
        System.out.println(chSort);       // abc

        String[] strings = new String[]{"456", "123"};
        java.util.Arrays.sort(strings);
        System.out.println(strings);     // [Ljava.lang.String;@266474c2
        for (String i: strings) {
            System.out.println(i);       // 123 456
        }

        char[] chSort2 = new char[] {'c', 'b', 'a'};
        java.util.Arrays.sort(chSort2, 0,2);
        System.out.println(chSort2);       // bca  排序从 0 到 2-1 的元素

        // binarySearch 二分查找
        char[] chSearch = new char[] {65, 66, 67};
        System.out.println(java.util.Arrays.binarySearch(chSearch, 'A'));  // 0
        System.out.println(java.util.Arrays.binarySearch(chSearch, 'D'));  // -4

        // equals 两个数组内容是否相等
        char[] ch1 = new char[] {65, 66, 67};
        char[] ch2 = new char[] {65, 66, 67};
        char[] ch3 = new char[] {65, 66, 70};
        System.out.println(java.util.Arrays.equals(ch1, ch2));  // true
        System.out.println(java.util.Arrays.equals(ch1, ch3));  // false

        // fill 用新元素替换数组的相应位置
        int[] list = new int[]{1, 2, 4, 5, 6};
        java.util.Arrays.fill(list, 3);   // 用3替换整个数组
        for (int i: list) {
            System.out.println(i);       // 3 3 3 3 3
        }

        java.util.Arrays.fill(list, 1, 3, 6); // 用6替换 1 到 3-1 之间的元素
        for (int i: list) {
            System.out.println(i);       // 3 6 6 3 3
        }

        /**
         * 二维数组，每一个元素都是一个数组
         * */

        /**
         * 声明数组变量,没有分配空间，只有数组的引用，数组是null
         */
        int[][] b1; // 推荐使用
        int b2[][];

        /**
         * 用new创建数组，初始化默认值，必须指定至少行数的大小[n][]，才能分配空间，空间分配后就不能更改
         */
        b1 = new int[2][3];
        b2 = new int[2][];
        b2[0] = new int[3];
        b2[1] = new int[1]; // 长度不一定每一行都一样
        /**
         * 不用new 创建和初始化数组
         */
        int[][] b3 = {{1, 2, 3},{6}};
        /**
         * 用new创建，同时初始化数组，[][]里不能有数
         */
        int[][] b4 = new int[][]{{1, 2, 3},{6}};

        /**
         * 每一行长度不一定一样，用length来获取每一行的长度
         */
        System.out.println(b2[0].length);       // 3
        System.out.println(b2[1].length);       // 1

        /**
         * 多维数组类似
         * */
        int[][][] c1;
        c1 = new int[][][] {{{1,2},{3},{8}},{{5},{6}}};
        System.out.println(c1.length);    // 2  每一个元素都是一个二维数组，二维数组的个数
        System.out.println(c1[0].length); // 3  每一个元素都是一个一维数组，一维数组的个数
        System.out.println(c1[0][0].length); // 2  一维数组中元素的个数

        /**
         * 对象数组 每个元素是一个对象的引用变量
         */
        // 对象数组声明，创建分配对象
        ArrayClass[] arrayClass = new ArrayClass[5]; // 每个元素都是null
        // 对象数组初始化
        for (int i = 0; i < 5; i++) {
            arrayClass[i] = new ArrayClass();        // null -> 实例对象
        }


    }

    // 测试匿名函数
    private static void testAnonymous(int[] nums) {}
    private static void testVarargs(double... nums) {
        for (double i: nums) {
            System.out.println(i);       // 1.2 2.3 5.6  testVarargs(1.2, 2.3, 5.6);
                                         // 2.2 6.6 testVarargs(new double[] {2.2, 6.6}))
        }
    }

}
