package designPattern.strategy.exercise;

import designPattern.strategy.exercise.strategies.QuickSorter;
import designPattern.strategy.exercise.strategies.SelectionSorter;

public class Main {
    public static void main(String[] args) {
        String[] data = {"Dumpty", "Bowman", "Carroll", "Elfland", "Alice"};

        SortAndPrint sap1 = new SortAndPrint(data, new SelectionSorter());
        sap1.execute();

        String[] data2 = {"Dumpty", "Bowman", "Carroll", "Elfland", "Alice"};
        SortAndPrint sap2 = new SortAndPrint(data2, new QuickSorter());
        sap2.execute();
    }
}
