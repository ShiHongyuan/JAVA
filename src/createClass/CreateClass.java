package createClass;

public class CreateClass {
    public int var;
    public CreateClass(int var) {
        this.var = var;
        testFunction();
    }

    public void testFunction() {
        this.testStatic();
    }
    public static void testStatic() {}

}
