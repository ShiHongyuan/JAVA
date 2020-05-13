package designPattern.memento;

/**
 * 快照保存对象模式：快照是将被保存对象需要保存的数据集合起来的一个对象，可以用来恢复被保存对象到建立快照时候的状态
 *               为了安全，对外部使用者快照需要尽可能不公开其内部状态，以免快照被更改影响到被保存对象的封装性和独立性，
 *               建立保存对象（创建快照）和恢复成保存对象的样子（恢复快照）都是被保存对象的内部方法，
 *               使用者调用被保存对象的方法创建保存对象（快照），以对象的方式存在内存里，但是访问不了快照对象里面的内容，
 *               在需要的时候，使用者再次调用被保存对象的方法，用快照对象把被保存对象恢复成快照的样子。
 *
 *               什么时候创建快照对象，创建多少个快照对象，什么时候恢复快照对象，怎么保存快照对象，
 *               这些被保存对象都不用管，它只管把快照创建出来和用快照恢复状态，改变这些只需要修改使用者逻辑即可，
 *               让被保存对象和使用者独立，使被保存对象都成为独立的可复用组件。
 */

import designPattern.memento.game.Gamer;
import designPattern.memento.game.Memento;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * 快照保存对象模式：控制建立快照，恢复快照的快照对象和被保存对象的使用者
 */
public class Main {
    public final static String filename = "game.dat";
    public static void main (String[] args) {
        Gamer gamer = new Gamer(100);

        // 快照保存在内存里 版本
//          Memento memento = gamer.createMemento();  // 游戏开始前保存一个初始快照


        // 快照保存在文件里 版本
        if (new File(filename).exists()) {
            gamer.restoreMemento(loadMemento());
        } else {
            saveMemento(gamer.createMemento());
        }


        for (int i = 0; i < 10; i++) {
            System.out.println("=========  " + i);
            System.out.println("掷骰子前状态：" + gamer);

            gamer.bet();

            System.out.println("掷骰子后状态：" + gamer);

            // 快照保存在内存里 版本
//            if (gamer.getMoney() > memento.getMoney()) {
//                System.out.println("所持金钱增加了，快照保存当前游戏状态");
//                memento = gamer.createMemento();
//            } else if (gamer.getMoney() < memento.getMoney()) {
//                System.out.println("所持金钱减少了，用快照恢复游戏状态");
//                gamer.restoreMemento(memento);
//            }

            // 快照保存在文件里 版本
            if (gamer.getMoney() > loadMemento().getMoney()) {
                System.out.println("所持金钱增加了，快照保存当前游戏状态");
                saveMemento( gamer.createMemento() );
            } else if (gamer.getMoney() < loadMemento().getMoney()) {
                System.out.println("所持金钱减少了，用快照恢复游戏状态");
                gamer.restoreMemento(loadMemento());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println();
        }
    }

    public static Memento loadMemento () {
        Memento memento = null;

        try {
            // 读的时候解压缩 InflaterInputStream
            ObjectInputStream reader = new ObjectInputStream(new InflaterInputStream( new FileInputStream(filename) ));
            memento  = (Memento) reader.readObject();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return memento;
    }

    public static void saveMemento (Memento memento) {
        try {
            // 写的时候压缩 DeflaterOutputStream
            ObjectOutputStream writer = new ObjectOutputStream(new DeflaterOutputStream( new FileOutputStream(filename) ));
            writer.writeObject(memento);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
