package TestClass;
import createClass.CreateClass;

public class testClass {
    static CreateClass x1 = new CreateClass(1);

    public static void main(String[] args) {
        x1.testFunction();
        x1.testStatic();
        CreateClass.testStatic();
        int var1 = x1.var;

        CreateClass x2 = new CreateClass(2);
        x2.testFunction();
        x2.testStatic();
        CreateClass.testStatic();
        int var2 = x2.var;
    }
}
