package accessModifier.package1;

public class FatherCommonClass1 {

    /********************  字段  ************************/

    // 字段都不能被子类修改，子类如果有声明相同类型和相同名的字段，父类字段只是被隐藏，不是被修改

    public String publicVar = "publicVar";//能够被不同包的实例访问；被不同包的子类继承
    protected String protectedVar = "protectedVar";//不同包的实例不能访问，同一个包的实例才能访问；不同包的子类也可以继承
    String defaultVar = "defaultVar";//不同包的实例不能访问，同一个包的实例才能访问；不同包的子类不能继承，同一个包的子类才能继承
    private String privateVar = "privateVar";//不能被所有实例访问，不能被所有子类继承


    // final变量必须在声明时赋值或者在构造函数中赋值（static final在声明时赋值或者在static代码块中赋值），能够被所有实例访问；能够被所有子类继承
    public final String finalPublicVar = "finalPublicVar";
    protected final String finalProtectedVar = "finalProtectedVar";
    final String finalDefaultVar = "finalDefaultVar";
    private final String finalPrivateVar = "finalPrivateVar";

    // static，能够被类直接访问，也可以被所有实例访问；而且能够被所有子类继承，可以够被子类直接访问，也可以被子类的实例访问
    public static String staticPublicVar = "staticPublicVar";
    protected static String staticProtectedVar = "staticProtectedVar";
    static String staticDefaultVar = "staticDefaultVar";
    private static String staticPrivateVar = "staticPrivateVar";

    // final + static，具有final和static的特点
    public final static String finalStaticVar = "finalStaticVar";


    /********************  方法  ************************/
    // public: 能够被不同包的实例调用；被不同包的子类继承并重写
    public String getPublicFunction() {
        return "publicFunction";
    }

    // protected: 不同包的实例不能调用，同一个包的实例才能调用；不同包的子类也可以继承并重写
    protected String getProtectedFunction() {
        return "protectedFunction";
    }

    // default: 不同包的实例不能调用，同一个包的实例才能调用；不同包的子类不能继承，同一个包的子类才能继承并重写
    String getDefaultFunction() {
        return "defaultFunction";
    }

    // private: 不能被所有实例调用；不能被所有子类继承
    private String getPrivateFunction() {
        return "protectedFunction";
    }


    // final: final的方法必须有方法体，不能是abstract的方法。
    // 能够被所有实例（包括不同包）调用；能够被所有子类（包括不同包的子类）继承，但是不能被重写
    public final String getFinalPublicFunction() {
        return "finalPublicFunction";
    }

    protected final String getFinalProtectedFunction() {
        return "finalProtectedFunction";
    }

    final String getFinalDefaultFunction() {
        return "finalPublicFunction";
    }

    private final String getFinalPrivateFunction() {
        return "finalPrivateFunction";
    }


    // static: static的方法必须有方法体，不能是abstract的方法。
    // 能够被类直接调用，也可以被所有实例调用；能够被所有子类继承，但是不能被重写（只会被子类相同签名的静态方法隐藏），能够被子类直接调用，也可以被子类的实例调用
    // 不管是类直接访问还是通过实例访问，同样受public/protected/default/private的范围限制
    public static String getStaticPublicFunction() {
        return "staticPublicFunction";
    }

    protected static String getStaticProtectedFunction() {
        return "staticProtectedFunction";
    }

    static String getStaticDefaultFunction() {
        return "staticDefaultFunction";
    }

    private static String getStaticPrivateFunction() {
        return "staticPrivateFunction";
    }


    // final + static: 具有final和static的特征
    public final static String getFinalStaticFuntion() {
        return "finalStaticPublicFunction";
    }









}
