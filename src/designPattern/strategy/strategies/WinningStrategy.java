package designPattern.strategy.strategies;

import designPattern.strategy.Hand;

import java.util.Random;

/**
 * 策略整体替换模式：具体策略
 */
public class WinningStrategy implements Strategy{
    private Random random;
    private boolean win = false;
    private Hand currentHand;

    public WinningStrategy(int seed) {
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        if (!win) {
            currentHand = Hand.getHand( random.nextInt(3) );
        }
        return currentHand;
    }

    @Override
    public void study(boolean win) {
        this.win = win;
    }
}
