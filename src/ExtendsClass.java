public class ExtendsClass extends Object{
    /**
     * 子类构造方法必须调用父类构造方法，为了为子类做初始化
     * 1、不显示定义，默认在构造方法第一行调用父类无参构造方法，父类若没有无参构造方法，则会报错
     * 2、显示定义super(有参||无参)，在构造方法第一行显示定义
     */
    public ExtendsClass() {}
    public ExtendsClass(int i) {}

    public class ChildClass1 extends ExtendsClass {
        // 隐式调用super()
        public ChildClass1() {}
        // 显式调用
        public ChildClass1(int i) {
            super(i);
        }
    }

    public class ChildClass2 extends ExtendsClass {
        // 显式调用
        public ChildClass2() {
            super(5);
        }
        // 隐式调用super()
        public ChildClass2(int i) {}
    }

    /**
     * 根类 Object，要自己重写的方法
     * 1、boolean equals(Object)
     * 2、int hashCode()  确定该对象在哈希表中的索引位置，能根据“键”快速的检索出对应的“值”
     * 作用：在集合框架HashSet和Map里，检查重复元素的方式就是用hashCode，先发现有相同 hashcode 值的对象，才会调用 equals（）方法来检查 hashcode 相等的对象是否真的相同，来决定对象能不能加入集合，不用一个一个比较，减少了equals的次数
     * 3、String toString()
     */
    public class ChildClass3 extends ExtendsClass {
        int value;
        int hash = 0;
        Integer objValue;
        final char[] charValue = {'a','b','c'};

        // 重写toString
        @Override
        public String toString () {
            return "this value is " + this.value;
        }


        /**
         * 1、如果两个对象相等，则hashcode一定也是相同的
         * 2、两个对象有相同的hashcode值，它们也不一定是相等的
         * 3、equals方法为true，则hashcode肯定一样
         * 4、equals方法为false，则hashcode不一定不一样
         * 5、hashCode() 本地方法默认对堆上的对象产生独特值。如果没有重写 hashCode()，两个对象==为false，hash值不同，两个对象==为true，hash值就相同
         */


        /**
         * 在集合框架HashSet和Map中，equals方法覆盖，则hashCode 方法也必须被覆盖：
         * 如果两个对象equals相等，则hashcode一定也是相同的，如果两个对象equals不相等，则hashcode不一定相同，但是这样不利于哈希表的性能，一般我们也不要这样做
         * Object的equals是引用相等才相等，hashCode()和equals()保持一致
         * 重写了equals方法，内容相等就相等，hashCode()和equals()就不一致了，会出现equals方法相等，但是hashcode不相等的情况，这不符合hashcode的规则，会使集合出现内容相等的元素
         */

        // 重写equals
        @Override
        public boolean equals (Object obj) {
            if (obj instanceof ChildClass3) {
                System.out.println("ChildClass3 ");
                return this.value == ((ChildClass3)obj ).value;
            }
            else
                return false;
        }

        // 重写hashCode
        // public native int hashCode(); Object不是java实现的，是jvm的本地方法，默认是对堆上的对象产生独特值，两个对象==为false，hash值就不同
        // 重写hashCode的方法：基本数据类型乘以一个质数，对象的hashCode乘以一个质数
        // String重写的办法
        @Override
        public int hashCode() {
            int h = hash;
            if (h == 0 && charValue.length > 0) {
                for (int i = 0; i < charValue.length; i++) {
                    h += 31 * h + charValue[i];              // 乘以一个质数
                }
                h += objValue.hashCode() * 17;               // 乘以一个质数
                hash = h;
            }
            return h;
        }

    }


}



