package designPattern.memento.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 快照保存对象模式：被快照保存的被保存对象具体实现类，里面实现了建立快照对象和根据快照对象恢复的方法
 */
public class Gamer {
    private int money;
    private ArrayList fruits = new ArrayList();

    private Random random = new Random();

    private static String[] fruitsname = {
            "苹果", "葡萄", "香蕉", "橘子"
    };

    public Gamer(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public List getFruits() {
        return (List) fruits.clone();
    }

    // 掷骰子进行游戏
    public void bet() {
        int dice = random.nextInt(6) + 1;
        if (dice == 1) {
            money += 100;
            System.out.println("掷骰子掷到1，所持金钱增加了。");
        } else if (dice == 2) {
            money /= 2;
            System.out.println("掷骰子掷到2，所持金钱减半了。");
        } else if (dice == 6) {
            String fruit = getFruit();
            fruits.add(fruit);
            System.out.println("掷骰子掷到3，增加了水果（" + fruit + "）。");
        } else {
            System.out.println("什么都不做。");
        }
    }

    private String getFruit() {
        String prefix = "";
        if (random.nextBoolean()) {
            prefix += "好吃的";
        }
        return prefix + fruitsname[random.nextInt(fruitsname.length)];
    }

    // 创建快照对象的方法
    public Memento createMemento() {
        Memento memento = new Memento(money);
        Iterator it = fruits.iterator();
        while (it.hasNext()) {
            String fruit = (String) it.next();
            if (fruit.startsWith("好吃的")) { // 可以添加条件，快照只保存好吃的水果
                memento.addFruit(fruit);
            }
        }
        return memento;
    }

    // 用快照对象恢复的方法
    public void restoreMemento(Memento memento) {
        this.money = memento.getMoney();
        this.fruits = (ArrayList) memento.getFruits();
    }

    @Override
    public String toString() {
        return "[money = " + money + ", fruits = " + fruits + "]";
    }
}
