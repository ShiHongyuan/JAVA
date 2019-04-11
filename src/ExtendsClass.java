public class ExtendsClass extends Object{
    /**
     * ���๹�췽��������ø��๹�췽����Ϊ��Ϊ��������ʼ��
     * 1������ʾ���壬Ĭ���ڹ��췽����һ�е��ø����޲ι��췽����������û���޲ι��췽������ᱨ��
     * 2����ʾ����super(�в�||�޲�)���ڹ��췽����һ����ʾ����
     */
    public ExtendsClass() {}
    public ExtendsClass(int i) {}

    public class ChildClass1 extends ExtendsClass {
        // ��ʽ����super()
        public ChildClass1() {}
        // ��ʽ����
        public ChildClass1(int i) {
            super(i);
        }
    }

    public class ChildClass2 extends ExtendsClass {
        // ��ʽ����
        public ChildClass2() {
            super(5);
        }
        // ��ʽ����super()
        public ChildClass2(int i) {}
    }

    /**
     * ���� Object��Ҫ�Լ���д�ķ���
     * 1��boolean equals(Object)
     * 2��int hashCode()  ȷ���ö����ڹ�ϣ���е�����λ�ã��ܸ��ݡ��������ٵļ�������Ӧ�ġ�ֵ��
     * ���ã��ڼ��Ͽ��HashSet��Map�����ظ�Ԫ�صķ�ʽ������hashCode���ȷ�������ͬ hashcode ֵ�Ķ��󣬲Ż���� equals������������� hashcode ��ȵĶ����Ƿ������ͬ�������������ܲ��ܼ��뼯�ϣ�����һ��һ���Ƚϣ�������equals�Ĵ���
     * 3��String toString()
     */
    public class ChildClass3 extends ExtendsClass {
        int value;
        int hash = 0;
        Integer objValue;
        final char[] charValue = {'a','b','c'};

        // ��дtoString
        @Override
        public String toString () {
            return "this value is " + this.value;
        }


        /**
         * 1���������������ȣ���hashcodeһ��Ҳ����ͬ��
         * 2��������������ͬ��hashcodeֵ������Ҳ��һ������ȵ�
         * 3��equals����Ϊtrue����hashcode�϶�һ��
         * 4��equals����Ϊfalse����hashcode��һ����һ��
         * 5��hashCode() ���ط���Ĭ�϶Զ��ϵĶ����������ֵ�����û����д hashCode()����������==Ϊfalse��hashֵ��ͬ����������==Ϊtrue��hashֵ����ͬ
         */


        /**
         * �ڼ��Ͽ��HashSet��Map�У�equals�������ǣ���hashCode ����Ҳ���뱻���ǣ�
         * �����������equals��ȣ���hashcodeһ��Ҳ����ͬ�ģ������������equals����ȣ���hashcode��һ����ͬ���������������ڹ�ϣ������ܣ�һ������Ҳ��Ҫ������
         * Object��equals��������Ȳ���ȣ�hashCode()��equals()����һ��
         * ��д��equals������������Ⱦ���ȣ�hashCode()��equals()�Ͳ�һ���ˣ������equals������ȣ�����hashcode����ȵ�������ⲻ����hashcode�Ĺ��򣬻�ʹ���ϳ���������ȵ�Ԫ��
         */

        // ��дequals
        @Override
        public boolean equals (Object obj) {
            if (obj instanceof ChildClass3) {
                System.out.println("ChildClass3 ");
                return this.value == ((ChildClass3)obj ).value;
            }
            else
                return false;
        }

        // ��дhashCode
        // public native int hashCode(); Object����javaʵ�ֵģ���jvm�ı��ط�����Ĭ���ǶԶ��ϵĶ����������ֵ����������==Ϊfalse��hashֵ�Ͳ�ͬ
        // ��дhashCode�ķ����������������ͳ���һ�������������hashCode����һ������
        // String��д�İ취
        @Override
        public int hashCode() {
            int h = hash;
            if (h == 0 && charValue.length > 0) {
                for (int i = 0; i < charValue.length; i++) {
                    h += 31 * h + charValue[i];              // ����һ������
                }
                h += objValue.hashCode() * 17;               // ����һ������
                hash = h;
            }
            return h;
        }
    }


    /**
     * super ����Ը����������ã�ָ��������������ʸ���ı����ͷ���
     * �ڽ�����������ʱ����ʵ�൱��ͬʱ�����˸���������ʵ�����������ʵ����Ҳ�Ǹ����ʵ������̬�԰�
     * this��super��������static������
     * */
    public class Super {
        protected int number;

        protected void showNumber() {
            // �������������ڸ������ʹ��������õ��������������ı���Ҳ�Ǹø����Լ��ı���
            System.out.println("number = " + number);
        }
    }

    public class Sub extends Super {
        int number = 20;
        public void showNumber() {
            System.out.println("number = " + number);
        }
        void testSuper() {
            showNumber();         // 20  ����������д������һ���ķ���ʱ��û�м�super��ƥ���Լ���ķ���������Լ���ı���ֵ
            super.showNumber();   // 0   ����������д������һ���ķ���ʱ������super��ƥ�丸��ķ�����super�����˸���ķ������������ı���ֵ
            super.number = 10;
            super.showNumber();   // 10  super�޸��˸���ı���ֵ��super�����˸���ķ�����������ķ�����ͷ����ĸ����Լ��ı�����������ı�ĸ���ı���ֵ��
        }
    }


    public static void main(String[] args) {
        ExtendsClass obj = new ExtendsClass();
        Sub sub = obj.new Sub();
        sub.testSuper();
    }


}



