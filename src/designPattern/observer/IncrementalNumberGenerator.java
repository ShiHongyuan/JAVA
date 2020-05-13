package designPattern.observer;

import designPattern.observer.framework.NumberGenerator;

public class IncrementalNumberGenerator extends NumberGenerator {
    private int start;
    private int stop;
    private int step;
    private int number;

    public IncrementalNumberGenerator(int start, int stop, int step) {
        this.start = start;
        this.stop = stop;
        this.step = step;
        this.number = start;
    }

    @Override
    public void excute() {
        for (int i = start; i < stop; i += step) {
            number = i;
            notifyObserver();
        }
    }

    @Override
    public int getNumber() {
        return number;
    }
}
