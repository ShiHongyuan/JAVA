package designPattern.memento.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 快照保存对象模式：将被保存对象需要保存的数据集合起来的一个对象，可以用来恢复被保存对象到建立快照时候的状态
 */
public class Memento implements Serializable {
    int money;
    ArrayList fruits;

    // 快照里唯一对外部使用者公开的信息
    // 为了安全，对外部使用者快照需要尽可能不公开其内部状态，以免快照被更改影响到被保存对象的封装性和独立性。
    public int getMoney() {
        return money;
    }

    Memento(int money) {
        this.money = money;
        this.fruits = new ArrayList();
    }

    void addFruit(String fruit) {
        fruits.add(fruit);
    }

    List getFruits() {
        return (List) fruits.clone();
    }
}
