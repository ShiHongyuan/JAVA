package designPattern.strategy.strategies;

import designPattern.strategy.Hand;

import java.util.Random;

/**
 * 策略整体替换模式：具体策略
 */
public class ProbStrategy implements Strategy {
    private Random random;
    private boolean win = false;
    private int prevHandValue = 0;
    private int currentHandValue = 0;
    private int[][] history = new int[][] {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
    };

    public ProbStrategy(int seed) {
        random = new Random(seed);
    }

    @Override
    public Hand nextHand() {
        int bet = random.nextInt(getSum(currentHandValue));
        int handValue;
        if (bet < history[currentHandValue][0]) {
            handValue = 0;
        } else if (bet < history[currentHandValue][0] + history[currentHandValue][1]) {
            handValue = 1;
        } else {
            handValue = 2;
        }
        prevHandValue = currentHandValue;
        currentHandValue = handValue;
        return Hand.getHand(handValue);
    }

    private int getSum(int currentHandValue) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += history[currentHandValue][i];
        }
        return sum;
    }

    @Override
    public void study(boolean win) {
        if (win) {
            history[prevHandValue][currentHandValue] ++;
        } else {
            history[prevHandValue][(currentHandValue + 1) % 3] ++;
            history[prevHandValue][(currentHandValue + 2) % 3] ++;
        }
    }
}
