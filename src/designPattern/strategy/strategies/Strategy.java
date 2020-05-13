package designPattern.strategy.strategies;

import designPattern.strategy.Hand;

/**
 * 策略整体替换模式：实现接口的都是具体的策略
 */
public interface Strategy {
    public abstract Hand nextHand();
    public abstract void study(boolean win);
}

