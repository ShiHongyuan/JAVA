package designPattern.strategy.strategies;

import designPattern.strategy.Hand;

import java.util.Random;

/**
 * 策略整体替换模式：具体策略
 */
public class RandomStrategy implements Strategy {
    private Random random;

    public RandomStrategy(int seed) {
        random = new Random(seed);
    }


    @Override
    public Hand nextHand() {
        return Hand.getHand( random.nextInt(3) );
    }

    @Override
    public void study(boolean win) {

    }
}
