public class FinalClass {
    /**
     * final ���ξֲ������ͳ�Ա������������һ���������ʾ�ظ�ֵ����Ա�������û�б�����ֵ:����Զ������͵�Ĭ��ֵ����ֵ��
     * ������������ֵ����������ò��ɱ�
     */
    final double PI = 3.1415;

    /**
     * final ���η����ͳ�Ա����
     * 1����һ��ԭ���ǰѷ����������κμ̳���ֻ�ܼ̳У������޸���������
     * 2������Javaʵ�ְ汾�Ὣfinal����תΪ��Ƕ���ã����Ч�ʣ�����Java�汾�Ѿ�����Ҫ��Щ�Ż��ˡ��������е�private��������ʽ��ָ��Ϊfinal��
     */
    final void myFinalFuntion() {}


    public class childClass extends FinalClass {
        public void fun() {
            this.myFinalFuntion();
        }
    }



    /**
     * final ������
     * ��������಻�ܱ��̳У�final���е����г�Ա�������ᱻ��ʽ��ָ��Ϊfinal����
     */
    public final class finalClass {
        finalClass() {}
        public void fun() { }
    }

//    public class finalChildClass extends finalClass { } // ���뱨��cannot inherit from final class "FinalClass.finalClass"

}
