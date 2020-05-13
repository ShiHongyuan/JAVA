/**
 * 可变参数，会把传递进来的独立参数组合成数组，也会把传递进来的数组元素拆分再组合成数组，而不会把它当做一个数组元素
 * 如果要传递一个是数组的元素，只能传参 new Object[]{1,2,new int[]{1,2,3}}
 * */
public class VariableLengthParameter {
    /**************
     * 可变参数
     *  1. 在方法中定义可变参数后，我们可以像操作数组一样操作该参数；

        2. 如果该方法除了可变参数还有其它的参数，可变参数必须放到最后；

        3. 调用使用了可变参数的方法时：
            a. 可以不写参数，即传入空参；
            b. 可以直接在里边写入参数，参数间用逗号隔开；
            c. 可以传入一个数组；
     *
     * ******************/

    //求若干个整型数中的最大值，要么传递多个独立的int参数，要么传递一个int[]数组，都会放进items里作为一个数组使用
    public static int getMax(int... items){       //定义可变参数items

        int max = Integer.MIN_VALUE;       //次数为int能表示的最小值，值为-2147483648
        for(int item : items){
            max = item > max? item : max;    //取大值
            //System.out.println(item);
        }
        return max;
    }
    //以Object作为形参类型，可以让一个整个数组作为一个参数，因为任何数组都是Object的子类
    public static int getObjectMax(Object... items){       //定义可变参数items

        int max = Integer.MIN_VALUE;       //次数为int能表示的最小值，值为-2147483648
        for(Object item : items){
            max = (Integer)item > max? (Integer)item : max;    //取大值
            //System.out.println((Integer)item);
        }
        return max;
    }
    //返回比number大的数的个数
    public static int getLagerNum(int number,int... items){  //若除了可变长参数还有其它参数，可变长参数一定要放在最后
        int sum = 0;
        for(int item : items){
            if(item > number){
                sum++;
            }
        }
        return sum;
    }


    public static void main(String[] args) throws Exception {
        /**
         * 可变参数，要么传递多个独立的形参类型的参数，要么传递一个形参类型的数组，都会放进items里作为一个数组使用
         *         这里就是要么传递多个独立的int，要么传递一个int[]数组
         * */
        System.out.println( getMax(1,2,3) );  //3
        System.out.println( getMax(new int[]{1,2,3}) );//3

        // 注意：items的类型是Object，为什么可以传递Integer类型的数组呢，因为Integer类是Object的子类，根据多态性，子类可以隐式转换成父类
        System.out.println( getObjectMax(new Integer[]{1,2,3}) );//3
//        getObjectMax(new int[]{1,2,3});// 基本数组类型不是对象，不是Object的子类，运行时会报错

        // 如果想要传递整个数组作为一个参数，可变长形参只能是Object类型，然后传递 new Object[]{1,2,new int[]{1,2,3}}，整个数组作为Object数组里的一个元素
        // 但是Integer[]在函数内强转成Integer时会出错，所以这个函数是不适合传递整个数组作为参数的哈
//        getObjectMax(new Object[]{1,2,new int[]{1,2,3}});



    }
}
