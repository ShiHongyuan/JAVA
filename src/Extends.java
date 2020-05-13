public class Extends {
    public static void main(String[] args) {
        C c = new C(10);
        System.out.println( c.getData() );
        System.out.println( c.doGetData() );
        //调用了A的构造函数
        //调用了B的构造函数
        //调用了C的构造函数
        //1
        //1
    }
}
class A{
    int data;
    A(){
        data = 0;
        System.out.println( "调用了A的构造函数" );
    }
    int getData() {
        return doGetData();
    }
    int doGetData() {
        return data;
    }
}

class B extends A{
    int data;
    B () {
        data = 1;
        System.out.println( "调用了B的构造函数" );
    }

    int doGetData() {
        return data;
    }
}

class C extends B{
    int data;
    C (int data) {
        this.data = data;
        System.out.println( "调用了C的构造函数" );
    }
}

