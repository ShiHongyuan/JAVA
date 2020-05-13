package IOSystem;

import java.io.*;

public class BIO {
    public static void main(String[] args) throws Exception {

    /******************  所有流的抽象基类：InputStream/Reader，OutputStream/Writer  *********************/

    // 模本方法

    // ******************  InputStream
    // int read(); 从输入流中读取单个字节，返回所读取的字节数据（字节数据可直接转换为int类型）
    // int read(byte[] b); 从输入流中最多读取b.length个字节的数据，并将其存储在字节数组b中，返回实际读取的字节数
    // int read(byte[] b,int off,int len); 从输入流中最多读取len个字节的数据，并将其存储在数组b中，放入数组b中时，并不是从数组起点开始，而是从off位置开始，返回实际读取的字节数


    // ******************  Reader
    // int read(); 从输入流中读取单个字符，返回所读取的字符数据（字节数据可直接转换为int类型）
    // int read(char[] b); 从输入流中最多读取b.length个字符的数据，并将其存储在字节数组b中，返回实际读取的字符数
    // int read(char[] b,int off,int len); 从输入流中最多读取len个字符的数据，并将其存储在数组b中，放入数组b中时，并不是从数组起点开始，而是从off位置开始，返回实际读取的字符数

    // ******************  InputStream和Reader提供的一些移动指针的方法
    // void mark(int readAheadLimit); 在记录指针当前位置记录一个标记（mark）
    // boolean markSupported(); 判断此输入流是否支持mark()操作，即是否支持记录标记
    // void reset(); 将此流的记录指针重新定位到上一次记录标记（mark）的位置
    // long skip(long n); 记录指针向前移动n个字节/字符



    // ******************  OutputStream
    // void write(int c); 将指定的字节输出到输出流中，其中c即可以代表字节，也可以代表字符
    // void write(byte[] buf); 将字节数组中的数据输出到指定输出流中
    // void write(byte[] buf, int off,int len); 将字节数组中从off位置开始，长度为len的字节输出到输出流中


    // ******************  Writer
    // void write(int c); 将指定的字符输出到输出流中，其中c即可以代表字节，也可以代表字符
    // void write(char[] buf); 将字符数组中的数据输出到指定输出流中
    // void write(char[] buf, int off,int len); 将字符数组中从off位置开始，长度为len的字符输出到输出流中
    // void write(String str); 将str字符串里包含的字符输出到指定输出流中
    // void write (String str, int off, int len); 将str字符串里面从off位置开始，长度为len的字符输出到指定输出流中



        /**********  节点流实现类典型：文件流：FileInputStream/FileReader ，FileOutputStream/FileWriter  *************/

        // 节点流可以直接由数据源（比如文件路径，File对象）创建流实例

        // ******************  FileInputStream读取文件
        FileInputStream fis = null;
        try {
            //创建字节输入流
            fis = new FileInputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileInputStream.txt");

            //创建一个长度为1024的竹筒
            byte[] bytes = new byte[10];
            //用于保存的实际字节数
            int hasRead = 0;

            //使用循环来重复取水的过程
            System.out.print("\n");
            while ((hasRead = fis.read(bytes)) > 0) {
                //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
                System.out.print(new String(bytes, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fis.close()来关闭该文件的输入流。
            // 程序里面打开的文件IO资源不属于内存的资源，垃圾回收机制无法回收该资源，所以应该显示的关闭打开的IO资源。
            // Java 7改写了所有的IO资源类，它们都实现了AntoCloseable接口，因此都可以通过自动关闭资源的try语句来关闭这些Io流。
            fis.close();
        }


        // ******************  FileReader读取文件
        FileReader fr=null;
        try {
            //创建字节输入流
            fr = new FileReader("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileReader.txt");

            //创建一个长度为1024的竹筒
            char[] chars = new char[10];
            //用于保存的实际字节数
            int hasRead = 0;

            //使用循环来重复取水的过程
            System.out.print("\n");
            while((hasRead = fr.read(chars)) > 0){
                //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
                System.out.print(new String(chars,0,hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            fr.close();
        }


        // ******************  FileOutputStream写入文件
        FileInputStream fis1 = null;
        FileOutputStream fos1 = null;
        try {
            //创建字节输入流
            fis1 = new FileInputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileInputStream.txt");
            //创建字节输出流
            fos1 = new FileOutputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileOutputStream.txt");

            byte[] bytes = new byte[10];
            int hasRead = 0;

            //循环从输入流中取出数据
            while((hasRead = fis1.read(bytes)) > 0){
                //每读取一次，即写入文件输出流，读了多少，就写多少，这里是覆盖写入，所以文件内容没变
                fos1.write(bytes,0, hasRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 关闭输出流除了可以保证流的物理资源被回收之外，可能还可以将输出流缓冲区中的数据flush到物理节点中里（因为在执行close（）方法之前，自动执行输出流的flush（）方法）。
            // java很多输出流默认都提供了缓存功能，其实我们没有必要刻意去记忆哪些流有缓存功能，哪些流没有，只有正常关闭所有的输出流即可保证程序正常。
            fis1.close();
            fos1.close();
        }


        // ******************  FileWriter写入文件
        FileReader fr1 = null;
        FileWriter fw1 = null;
        try {
            //创建字符输入流
            fr1 = new FileReader("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileReader.txt");
            //创建字符输出流
            fw1 = new FileWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileWriter.txt");

            char[] chars = new char[10];
            int hasRead = 0;

            //循环从输入流中取出数据
            while ((hasRead = fr1.read(chars)) > 0) {
                //每读取一次，即写入文件输出流，读了多少，就写多少，这里是覆盖写入，所以文件内容没变
                fw1.write(chars, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fr1.close();
            fw1.close();
        }



        /**********  处理流实现类典型：缓冲流：BufferedInputStream/BufferedReader, BufferedOutputStream/BufferedWriter  *************/

        // 处理流不能直接由数据源创建流实例，而需要先创建节点流的实例后，再套接在节点流实例上创建处理流实例

        // 不能同时读写同一个文件，因为创建输出流实例的时候，如果不是以追加模式创建，就会在实例创建的时候就清空文件的内容，
        // 后面再通过输入流读文件就没有任何内容可读了

        // ******************  BufferedInputStream/BufferedReader 读写文件
        FileInputStream fis2 = null;
        FileOutputStream fos2 = null;
        BufferedInputStream bis2 = null;
        BufferedOutputStream bos2 = null;
        try {
            //创建字节输入流
            fis2 = new FileInputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileInputStream.txt");
            //创建字节输出流
            fos2 = new FileOutputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileOutputStream.txt");
            //创建字节缓存输入流，需要套接在文件节点流实例上创建处理流
            bis2 = new BufferedInputStream(fis2);
            //创建字节缓存输出流，需要套接在文件节点流实例上创建处理流
            bos2 = new BufferedOutputStream(fos2);

            byte[] bytes = new byte[1024];
            int hasRead = 0;

            //循环从缓存流中读取数据
            while((hasRead = bis2.read(bytes))>0){
                //向缓存流中写入数据，读取多少写入多少
                bos2.write(bytes,0, hasRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            // 只关闭了缓存流。
            // 这个需要注意一下，当我们使用处理流套接到节点流上的使用的时候，只需要关闭最上层的处理就可以了。
            // java会自动帮我们关闭下层的节点流。
            bis2.close();
            bos2.close();
        }


        // ******************  BufferedOutputStream/BufferedWriter，用法和上面字节缓存流一致
        FileReader fr2 = null;
        FileWriter fw2 = null;
        BufferedReader br2 = null;
        BufferedWriter bw2 = null;

        try {
            fr2 = new FileReader("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileReader.txt");
            fw2 = new FileWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOFileWriter.txt");
            br2 = new BufferedReader(fr2);
            bw2 = new BufferedWriter(fw2);

            char[] chars = new char[10];
            int hasRead = 0;

            while ((hasRead = br2.read(chars)) > 0) {
                bw2.write(chars, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br2.close();
            bw2.close();
        }



//        /**********  处理流实现类典型：转换流：InputStreamReader/OutputStreamWriter  *************/
//
//        // 把控制台的输入打印出来，同时写入文件
//        // 系统自带一个连接控制台的节点流，输入输出流分别是 System.in / System.out
//
//        try {
//            // 套接在系统自带输入流System.in上创建InputStreamReader实例
//            InputStreamReader reader = new InputStreamReader(System.in);
//            // 在处理流上再套接一层缓冲流，处理流可以套接在其他处理流上，并且可以套接多层
//            // BufferedReader流具有缓存功能，它可以一次读取一行文本——以换行符为标志，如果它没有读到换行符，则程序堵塞。
//            // 等到读到换行符，程序才会打印出刚刚输入的内容。
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//
//            // 套接在文件节点流FileOutputStream的匿名实例上创建OutputStreamWriter实例
//            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOOutputStreamWriter.txt"));
//            // 在处理流上再套接一层缓冲流，处理流可以套接在其他处理流上，并且可以套接多层
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//
//            String buffer = null;
//
//            // 一行一行读取控制台输入，直到输入 exit
//            while ((buffer = bufferedReader.readLine()) != null){
//                // 如果读取到的字符串为“exit”,则退出循环
//                if(buffer.equals("exit")){
//                    break;
//                }
//                //打印读取的内容
//                System.out.println("输入内容：" + buffer);
//                bufferedWriter.write(buffer);
//                bufferedWriter.write("\n");
//            }
//            bufferedReader.close();
//            bufferedWriter.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//        }



        /**********  处理流实现类典型：对象流：ObjectInputStream/ObjectOutputStream  *************/

        // ******************  读写对象
        // 使用对象流的一些注意事项：
        //      1. 读取顺序和写入顺序一定要一致，不然会读取出错。
        //      2. 在对象属性前面加transient关键字，则该对象的属性不会被序列化。
        //      3. 被写入的对象类必须实现Serializable接口，否则这里会runtime error

        OutputStream outputStream = null;
        BufferedOutputStream bufWrite = null;
        ObjectOutputStream objWrite = null;
        try {
            //序列化文件输出流
            outputStream = new FileOutputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOObjectOutputStream.txt");
            //构建缓冲流
            bufWrite = new BufferedOutputStream(outputStream);
            //构建字符输出的对象流
            objWrite = new ObjectOutputStream(bufWrite);
            //序列化数据写入
            objWrite.writeObject(new Person("shy", 21)); //Person类必须实现Serializable接口，否则这里会runtime error

            //关闭流
            objWrite.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            InputStream inputStream = new FileInputStream("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/BIOObjectOutputStream.txt");
            //构建缓冲流
            BufferedInputStream bufRead = new BufferedInputStream(inputStream);
            //构建字符输入的对象流
            ObjectInputStream objRead = new ObjectInputStream(bufRead);
            Person tempPerson = (Person) objRead.readObject();
            System.out.println("从文件读出的Person对象为：" + tempPerson);//从文件读出的Person对象为：IOSystem.BIO$Person@7cc355be
            System.out.println("从文件读出的Person对象name为：" + tempPerson.name);//从文件读出的Person对象name为：shy
            System.out.println("从文件读出的Person对象age为：" + tempPerson.age);//从文件读出的Person对象age为：21

            //关闭流
            objRead.close();
//            bufRead.close();
//            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    static class Person implements Serializable{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


}
