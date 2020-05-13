package designPattern.builder;

/**
 * 建造者模式的监工：它决定建造的工期，先后顺序。在什么时候派什么工人用什么砖瓦建造什么东西
 */
public class Director {
    // 监工用委托的模式持有具体的建造者材料对象
    // 委托的具体的建造者是什么，监工调用的建造方法就是具体建造者的方法
    private Builder builder;
    public Director (Builder builder) {
        this.builder = builder;
    }

    // 监工调用建造者材料对象的方法进行建造
    public void construct () {
        builder.makeTitle("Greeting");
        builder.makeString("从早上至下午 ");
        builder.makeItems(new String[] {
                "早上好。",
                "下午好。"
        });
        builder.makeString("晚上 ");
        builder.makeItems(new String[] {
                "晚上好。",
                "晚上。",
                "再见。"
        });
        builder.close();
    }
}
