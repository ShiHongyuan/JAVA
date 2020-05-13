package designPattern.strategy;

import designPattern.strategy.strategies.Strategy;

/**
 * 策略整体替换模式：通过持有传入的具体策略操作下一次出拳，并且操作每次赢输的结果给具体的策略进行学习
 */
public class Player {
    private String name;
    private Strategy strategy;
    private int wincount;
    private int losecount;
    private int gamecount;

    public Player (String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Hand nextHand() {
        return strategy.nextHand();
    }

    public void win() {  // 赢了
        strategy.study(true);
        wincount++;
        gamecount++;
    }

    public void lose() {  // 输了
        strategy.study(false);
        losecount++;
        gamecount++;
    }

    public void even() {  // 平局
        gamecount++;
    }

    public String toString() {
        return "[" + name + ":" + gamecount + " games, " + wincount + " win, " + losecount + " lose.]";
    }
}

