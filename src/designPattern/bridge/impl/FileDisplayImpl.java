package designPattern.bridge.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDisplayImpl extends DisplayImpl{
    private String filename;
    private BufferedReader reader;


    public FileDisplayImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void rawOpen() {
        try{
            reader = new BufferedReader(new FileReader(filename));

            // mark(readAheadLimit)方法仅有一个参数，在mark处有效的情况下限制读取的字符数。当读取字符达到或超过此限制时，尝试重置流会失败。当限制数值大于输入buffer的默认大小时，将会动态分配一个容量不小于限制数值的buffer。因此，应该慎用大数值。
            reader.mark(256);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=~=~=~=~=~=~" + filename + "=~=~=~=~=~=~");//装饰框
    }

    @Override
    public void rawPrint() {
        try {
            String line;
            // 功能类有一个循环打印的功能，会循环调用rawPrint()，所以每次调用需要把文件复位
            reader.reset();
            while ((line = reader.readLine()) != null) {
                System.out.println("> " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rawClose() {
        System.out.println("=~=~=~=~=~=~" + filename + "=~=~=~=~=~=~");//装饰框
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
