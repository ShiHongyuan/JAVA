package accessModifier.package2;

public interface PublicInterface2 {
    // public接口的变量即使只写了字段类型，也表示public final static。final变量必须在声明时赋值。
    int var = 1;

    int getVar();

    static int getVarStatic() {
        return var;
    };

    default int getVarDefault() {
        return var;
    };


}
