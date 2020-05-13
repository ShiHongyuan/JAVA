package designPattern.strategy;

import designPattern.strategy.strategies.ProbStrategy;
import designPattern.strategy.strategies.RandomStrategy;
import designPattern.strategy.strategies.WinningStrategy;

/**
 * 策略整体替换模式：不同玩家可以设置不同的具体策略
 *
 *              借助于委托，实现了可以动态替换算法的方法。
 *              使整个算法和使用算法的对象分离开，各种算法相互之间完全独立，可以作为可以随意增加、使用和被替换掉的组件。
 */
public class Main {
    public static void main(String[] args) {
        // 产生下一个拳头的随机数种子
        String seed1 = "100";
        String seed2 = "200";
        int seed11 = Integer.parseInt(seed1);
        int seed22 = Integer.parseInt(seed2);

        // 两种策略算法的玩家
        Player player1 = new Player("yuanzimin", new WinningStrategy(seed11));
        Player player2 = new Player("shihongyuan", new ProbStrategy(seed22));
        Player player3 = new Player("hahaha", new RandomStrategy(11));

        // 进行10000次比赛
        for (int i = 0; i < 10000; i++) {
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();

            if (nextHand1.isStrongerThan(nextHand2)) {
               player1.win();
               player2.lose();
            } else if (nextHand1.isWeakerThan(nextHand2)) {
                player1.lose();
                player2.win();
            } else {
                player1.even();
                player2.even();
            }
        }

        System.out.println("Total result:");
        System.out.println(player1.toString());
        System.out.println(player2.toString());

    }
}
