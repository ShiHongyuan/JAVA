package designPattern.adapter.exercise;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/designPattern/Adapter/exercise/file.txt");
            System.out.println(f.getValue("year"));
            f.setValue("year", "2006");
            f.setValue("month", "2");
            f.setValue("day", "21");
            System.out.println(f.getValue("year"));
            f.writeToFile("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/designPattern/Adapter/exercise/newfile.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
