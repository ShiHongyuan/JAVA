package accessModifier.package2;

public abstract class FatherAbstracClass2 {
    /********************  字段没有 abstract 修饰符  ************************/
    // 抽象类的字段的访问和继承规则与普通类一致
    public static String staticPublicVar = "staticPublicVar";

    public final String finalPublicVar = "finalPublicVar";

    /********************  方法  ************************/

    // public: 能够被不同包的子类继承并重写
    public abstract String getPublicFunction();

    // protected: 不同包的子类也可以继承并重写
    protected abstract String getProtectedFunction();

    // default: 不同包的子类不能继承，同一个包的子类才能继承并重写；
    // 能够被同一个包的子类继承实现，所以这里声明不会报错；但是被不同包的子类继承时，由于永远无法重写这个抽象方法，不同包的子类只能是抽象类
    // 要实现不同包的子类的具体实现类，父抽象类就不能有default方法
//    abstract String getDefaultFunction();



    // private: abstract 方法需要被重写实现，所以不能是 private
//    private abstract String getPrivateFunction();

    // final: final的方法必须有方法体，不能是abstract的方法。
//    public final abstract String getFinalPublicFunction();

    // static: static的方法必须有方法体，不能是abstract的方法。
//    public static abstract String getStaticPublicFunction();



    // 抽象类的非抽象方法的访问和继承规则与普通类一致
    public final String getFinalPublicFunction() {
        return "finalPublicFunction";
    }

    public static String getStaticPublicFunction() {
        return "staticPublicFunction";
    }

    public final static String getFinalStaticFuntion() {
        return "finalStaticPublicFunction";
    }
}
