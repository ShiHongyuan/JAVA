package designPattern.flyweight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 为了轻量级共享对象模式：被共享的对象
 */
public class BigChar {
    private char charname;
    private String fontdata;
    public BigChar(char charname) {
        this.charname = charname;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/designPattern/flyweight/files/big" + charname + ".txt")
            );

            String line;
            StringBuffer buf = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }

            reader.close();
            this.fontdata = buf.toString();
        } catch (FileNotFoundException e) {
            this.fontdata = charname + "?";
        } catch (IOException e) {
            this.fontdata = charname + "?";
        }
    }

    public void print() {
        System.out.println(fontdata);
    }
}
