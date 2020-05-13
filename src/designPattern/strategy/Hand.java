package designPattern.strategy;

public class Hand {
    private final static String[] name = {
        "石头", "剪刀", "布"
    };
    private final static Hand[] hands = {
            new Hand(0),
            new Hand(1),
            new Hand(2)
    };

    private int handValue;

    private Hand(int handValue) {
        this.handValue = handValue;
    }

    private int fight(Hand hand) {
        if(this == hand) {
            return 0;
        } else if ((this.handValue + 1) % 3 == hand.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean isStrongerThan(Hand hand) {
        return fight(hand) == 1;
    }

    public boolean isWeakerThan(Hand hand) {
        return fight(hand) == -1;
    }


    public String toString() {
        return name[handValue];
    }

    public static Hand getHand(int handValue) {
        return hands[handValue];
    }


}
