import java.util.List;

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
        // 声明数组的大小后，元素被赋予默认值，因为是相当于创建了一个对象
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

        // java可以是整形变量，c++不能用变量，只能用const int num = 10 的常量
        int num = 10;
        String[] ss1 = new String[num];//对的

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
         * （3）clone  clone方法是浅复制，不是深复制，可复制值，但是不能复制引用的值。
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
        //  *************  Arrays.toString() 数组转成数组字符串的形式，利于输出表示 *************

        // 只有char[]数组的toString()实现了把数组元素转化成字符串的形式，可以直接打印显示，但是其他类型（包括String，基本数据类型，包装类型）自身都没有重写toString()转换，所以只能借助Arrays.toString来打印字符串形式
        // char[]的Arrays.toString()转换
        char[] chString1 = new char[] {'男', '女', '别'};
        System.out.println(java.util.Arrays.toString(chString1));//[男, 女, 别]

        // char[]自身的toString方法转换成字符相连的字符串形式，打印函数默认调用对象的toString方法
        char[] chString2 = new char[] {'男', '女', '别'};//男女别
        System.out.println(chString2);

        // 其他类型没有重写toString方法，只能Arrays.toString()转换
        int[] intString1 = new int[] {1, 2, 3};
        String[] sString1 = new String[] {"男", "女", "性别"};
        System.out.println(java.util.Arrays.toString(intString1));//[1, 2, 3]
        System.out.println(java.util.Arrays.toString(sString1));//[男, 女, 性别]


        // ************* Arrays.sort() 串行排序 改变原来的数组  *************
        // 基本数据类型按照自然生序排序；包装类型按照它们默认实现的Comparable接口的compareTo排序；String按照特定位置的字母的自然升序排序
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

        int[] intSort = new int[] { 1, 3, 2, 7, 6, 5, 4, 9 };
        java.util.Arrays.sort(intSort);
        for (int i : intSort) {
            System.out.print(i + " ");// 1 2 3 4 5 6 7 9
        }
        System.out.print("\n");

        // ************* Arrays.parallelSort() java8新增的并行排序算法，基于fork/join框架，也都有其他类型重载，也有选取范围排序 *************
        // 当数据规模较大时，并行排序的性能比串行排序Arrays.sort()好
        int[] intSort2 = new int[] { 1, 3, 2, 7, 6, 5, 4, 9 };
        java.util.Arrays.parallelSort(intSort2);
        for (int i : intSort2) {
            System.out.print(i + " ");// 1 2 3 4 5 6 7 9
        }
        System.out.print("\n");


        // *************  Arrays.binarySearch() 二分查找  注意必须是已排序数组，否则二分查找找不到  *************
        char[] chSearch = new char[] {65, 66, 67};
        System.out.println(java.util.Arrays.binarySearch(chSearch, 'A'));  // 0  65是A
        System.out.println(java.util.Arrays.binarySearch(chSearch, 'D'));  // -4 68是D -号表示没找到，-后的数字表示相差的距离

        // *************  Arrays.equals() 两个数组内容是否相等  *************
        // 两个数组在相同位置的元素equals相等
        // 两个数组都是null
        char[] ch1 = new char[] {65, 66, 67};
        char[] ch2 = new char[] {65, 66, 67};
        char[] ch3 = new char[] {65, 66, 70};
        char[] ch4 = null;
        char[] ch5 = null;
        System.out.println(java.util.Arrays.equals(ch1, ch2));  // true
        System.out.println(java.util.Arrays.equals(ch1, ch3));  // false
        System.out.println(java.util.Arrays.equals(ch4, ch5));  // true


        // *************  Arrays.fill() 用新元素替换数组的相应位置  *************
        int[] list = new int[]{1, 2, 4, 5, 6};
        java.util.Arrays.fill(list, 3);   // 用3替换整个数组
        for (int i: list) {
            System.out.println(i);       // 3 3 3 3 3
        }

        java.util.Arrays.fill(list, 1, 3, 6); // 用6替换 1 到 3-1 之间的元素
        for (int i: list) {
            System.out.println(i);       // 3 6 6 3 3
        }

        // *************  Arrays.asList() 数组转成列表  *************

        // 返回由指定数组支持的固定大小的列表，返回的列表是可序列化的，并实现RandomAccess
        // 注意：是固定大小，不能增加删除，但是可以修改已有元素
        String[] strings1 = new String[]{"shi","hong","yuan"};
        List<String> list1 = java.util.Arrays.asList(strings1);
        list1.set(0,"shitou");
        System.out.println(list1);//[shitou, hong, yuan]

        // 提供了一种方便的方式来创建一个由单独几个元素组成的固定大小的列表
        List<String> list2 = java.util.Arrays.asList("yuan","zi","min");
        System.out.println(list2);//[yuan, zi, min]

        // 与collection.toArray()对立，collection.toArray()把列表转换成可以修改的数组，但是驻足在创建时长度就确定了，不能添加新的元素
        // 因为<T> T[] toArray(T[] a)，在把集合对象转换成数组时需要显式指定元素的类型，否则会报错，有两种方式指定具体类型
        // 第一种：转换后强制转换
        String[] strings2 = (String[])list1.toArray();
        // 第二种：new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型
        String[] strings3 = list1.toArray(new String[0]);
        System.out.println(java.util.Arrays.toString(strings2));//[shi, hong, yuan]
        strings2[0] = "amazing"; //可以修改已有元素，但是数组长度不是变长，所以不能添加删除新的元素
        System.out.println(java.util.Arrays.toString(strings2));//[amazing, hong, yuan]

        // *************  Arrays.copyOf()，Arrays.copyOfRange() 方法实现数组复制，新的，引用不同对象  *************
        // copyOf 需要指定从头开始复制的数组长度
        int[] intCopy = new int[]{ 1, 2, 3, 5, 6 };
        int[] intCopy1 = java.util.Arrays.copyOf(intCopy, 2);//复制2个
        System.out.println(java.util.Arrays.toString(intCopy1));//[1, 2]
        intCopy1[0] = 6;
        System.out.println(java.util.Arrays.toString(intCopy));//[1, 2, 3, 5, 6]
        System.out.println(java.util.Arrays.toString(intCopy1));//[6, 2]


        // copyOfRange 指定复制开始和结束的索引，不足的位置用0填充
        int[] intCopy2 = java.util.Arrays.copyOfRange(intCopy, 1, 7);
        System.out.println(java.util.Arrays.toString(intCopy2));//[2, 3, 5, 6, 0, 0]  原数组的索引是0-4，所以5-6的位置用0填充


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

        /** 数组对象没有重写Object的equals方法，用的是绝对相等，return (this == obj) **/
        int[] a = new int[]{1,2};
        int[] aa = a.clone();
        System.out.println(a == aa);            // false
        System.out.println(a.equals(aa));       // false

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
