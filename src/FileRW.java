import java.io.*;
import java.util.Scanner;


public class FileRW {
    public static void main(String[] args) throws IOException, ClassNotFoundException{


        /*****************************  文本io  ******************************／
        ／***** 写入前按照文件写入的编码方式编码，读出显示前按照文件读出的编码方式解码 *****／
        ／************************** 看到的都是字符 .txt ************************／



//        /**
//         * java.io.File--创建file对象，可查询文件属性，删除文件，但不能操作内容
//         * /是unix linux系统 java 的目录分割符，而windows系统的目录分割符是\，在java的字符串中要变成\\（因为\有特别的含义），但是输出的字符串就只有一个\了
//         */
//
//        /** java.io.File---构造方法 **/
//
//        // pathname 构造，File对象可以是一个文件夹，可以是绝对路径
//        java.io.File file1 = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA");
//        System.out.println(file1.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA
//        // 也可以是一个文件，也可以是相对路径，相对路径是相对于项目根目录
//        File file2 = new File("src/FileRW.java");
//        System.out.println(file2.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/FileRW.java"
//
//        // string的parent、child构造，child的File对象可以是一个文件夹
//        File file3 = new File("./","src");
//        System.out.println(file3.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/./src
//
//        // child也可以是一个文件
//        File file4 = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src","FileRW.java");
//        System.out.println(file4.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/FileRW.java
//
//        // File的parent、string的child构造，child的File对象可以是一个文件夹
//        File file5 = new File(file1,"src");
//        System.out.println(file5.getAbsolutePath());    // ~/IdeaProjects/mygithub/java/JAVA／src
//
//        // child也可以是一个文件
//        File file6 = new File(file3,"FileRW.java");
//        System.out.println(file6.getAbsolutePath());    // ~/IdeaProjects/mygithub/java/JAVA／./src/FileRW.java
//
//        File file = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/yayaya");
//        /** java.io.File---获取File的属性 **/
//        // 是否存在，可以是文件也可以是文件夹
//        System.out.println(file1.exists());    // true
//        System.out.println(file2.exists());    // true
//        System.out.println(file.exists());     // false  file不存在
//
//        // 是否可读，可以是文件也可以是文件夹，文件不存在返回false
//        System.out.println(file1.canRead());    // true  文件夹都是false
//        System.out.println(file2.canRead());    // true
//        System.out.println(file.canRead());     // false  不存在的文件返回false
//
//        // 是否可写，可以是文件也可以是文件夹，文件不存在返回false
//        System.out.println(file1.canWrite());    // true  文件夹都是false
//        System.out.println(file2.canWrite());    // true
//        System.out.println(file.canWrite());     // false  不存在的文件返回false
//
//        // 是否可执行，可以是文件也可以是文件夹，文件不存在返回false
//        System.out.println(file1.canExecute());    // true   文件夹可执行
//        System.out.println(file2.canExecute());    // false  文件不可执行
//        System.out.println(file.canExecute());     // false  不存在的文件返回false
//
//        // 是否是文件夹
//        System.out.println(file1.isDirectory());    // true
//        System.out.println(file2.isDirectory());    // false
//        System.out.println(file.isDirectory());     // false  不存在的文件返回false
//
//        // 是否是文件
//        System.out.println(file1.isFile());    // false
//        System.out.println(file2.isFile());    // true
//        System.out.println(file.isFile());     // false  不存在的文件返回false
//
//        // 是否是隐藏文件，可以是文件也可以是文件夹
//        System.out.println(file1.isHidden());    // false
//        System.out.println(file2.isHidden());    // false
//        System.out.println(file.isHidden());     // false  不存在的文件返回false
//
//        // 是否是用绝对路径创建的File对象，可以是文件也可以是文件夹
//        System.out.println(file1.isAbsolute());    // true
//        System.out.println(file2.isAbsolute());    // false
//        System.out.println(file.isAbsolute());     // true  虽然绝对路径是不存在的，但是也适用绝对路径创建的
//
//        // 获取File对象的绝对路径，可以是文件也可以是文件夹
//        System.out.println(file2.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/FileRW.java
//        System.out.println(file3.getAbsolutePath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/./src
//        System.out.println(file.getAbsolutePath());     // 不存在的路径也会获取  /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/yayaya
//
//        // 获取File对象的绝对路径，可以是文件也可以是文件夹，路径除去了. ..等，windows的磁盘会大写，总之返回很规整的绝对路径
//        try {  // getCanonicalPath需要处理异常
//            System.out.println(file2.getCanonicalPath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA
//            System.out.println(file3.getCanonicalPath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src
//            System.out.println(file.getCanonicalPath());     // 不存在的路径也会获取  /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/yayaya
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 获取File对象的最后一个文件夹名或文件名，文件夹就文件夹名
//        System.out.println(file2.getName());    // FileRW.java
//        System.out.println(file3.getName());    // src    文件夹名
//        System.out.println(file.getName());     // 不存在的文件也会获取  yayaya
//
//        // 获取生成File对象时，输入的参数路径，包括parent和child
//        System.out.println(file1.getPath());    // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/
//        System.out.println(file2.getPath());    // src/FileRW.java
//        System.out.println(file3.getPath());    // ./src    文件夹名
//        System.out.println(file.getPath());     // 不存在的路径也会获取  /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/yayaya
//
//        // 获取生成File对象时，输入的参数路径（包括parent和child）的最后一个文件夹或文件的父目录
//        System.out.println(file1.getParent());    // /Users/Tinashy/IdeaProjects/mygithub/java
//        System.out.println(file2.getParent());    // src
//        System.out.println(file3.getParent());    // .    文件夹名
//        System.out.println(file.getParent());     // 不存在的路径也会获取  /Users/Tinashy/IdeaProjects/mygithub/java/JAVA
//
//        // 获取File对象最后一次修改的时间(到1970年的毫秒数)，可以是文件也可以是文件夹
//        System.out.println(file1.lastModified());    // 1552524096000  文件夹
//        System.out.println(file2.lastModified());    // 1553100998000  文件
//        System.out.println(file.lastModified());     // 0              不存在的文件修改时间是 0
//
//        // 获取File对象的文件大小，可以是文件也可以是文件夹
//        System.out.println(file1.length());    // 238   文件夹大小
//        System.out.println(file2.length());    // 7328  文件大小
//        System.out.println(file.length());     // 0     不存在的文件大小是 0
//
//        // 获取File对象下的文件File[]（只到下面一层，不回递归），只可以是文件夹，文件返回null，不存在也返回null
//        File[] files1 = file1.listFiles();
//        System.out.println(files1.length);       // 5
//        for (File f: files1) {
//            System.out.println(f.getAbsolutePath());
//            // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/.git
//            // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/.idea
//            // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/mygithub.iml
//            // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/out
//            // /Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src
//        }
//        File[] files2 = file2.listFiles();       // 文件返回null，File[] 为null
////        System.out.println(files2.length);     // null获取长度报错
//        File[] files3 = file.listFiles();        // 不存在也返回null，File[] 为null
//
//        /** java.io.File---File唯一两个可以更改文件的方法 **/
//        // 删除文件、文件夹
////        System.out.println(file1.delete());     // true  删除文件夹，删除成功返回true
////        System.out.println(file2.delete());     // true  删除文件，删除成功返回true
//        System.out.println(file.delete());        // false  删除不存在的文件，会删除失败
//
//        try {  // 文件找不到异常处理
//            PrintWriter output = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/testfile.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        File newFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/testfile.txt");
//        System.out.println(newFile.delete());    // true  删除文件，删除成功返回true
//
//        // 重命名文件、文件夹，参数必须是一个File对象，先创建一个不存在的File对象，再重命名为它
////        System.out.println(file1.renameTo(file3));  // true  重命名文件夹为file3的名字，重命名成功返回true
////        System.out.println(file2.renameTo(new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/newname")));       // true  重命名文件为newname的名字，重命名成功返回true
//        System.out.println(file.renameTo(new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/newname.txt"))); // false  重命名不存在的文件，会失败返回false
//
//        try {  // 要有文件找不到异常处理
//            PrintWriter output = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/testfile.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        File oldFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/testfile.txt");
//        // 重命名时加了一层目录，相当于mv，又重命名了又移动了文件
//        System.out.println(oldFile.renameTo(new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/newname.txt")));    // true  重命名文件为newname的名字，重命名成功返回true


//        /**
//         * java.io.PrintWriter--向文件对象写文件
//         * 1、文件不存在就创建文件
//         * 2、文件存在就覆盖文件，创建的时候，还没写的时候，就把源文件清空了
//         */
//
//        /** java.io.PrintWriter---构造方法 **/
//
//        try {   // 要有文件找不到异常处理
//
//            // 从文件对象创建文件
////            File scannerFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
//            File scannerFile = new File("/Users/shihongyuan/mygithub/java/src/testfile.txt");
//            PrintWriter printWriter1 = new PrintWriter(scannerFile);
//
//            // 从文件路径字符串创建文件
////            PrintWriter printWriter2 = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
//            PrintWriter printWriter2 = new PrintWriter("/Users/shihongyuan/mygithub/java/src/testfile.txt");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        /** java.io.PrintWriter--- 写入文件 **/
//
//        PrintWriter printWriter = null;
//        try {
////            printWriter = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
//            printWriter = new PrintWriter("/Users/shihongyuan/mygithub/java/src/testfile.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // print写各类型数据，写进去就成了string，没间隔不换行，返回值都是void
//        // 第一排
//        printWriter.print((byte)2);
//        printWriter.print(' ');
//        printWriter.print(9.9);
//        printWriter.print(' ');
//        printWriter.print(0.6f);
//        printWriter.print((char)32);  // 空格的asc2码是32
//        printWriter.print((short)6);
//        printWriter.print('\u0020');  // 统一码的 \u0020=32
//        printWriter.print(1);
//        printWriter.print(" ");
//        printWriter.print(12L);
//        printWriter.print(" ");
//        printWriter.print('0');
//        printWriter.print(new char[]{'1','2'});
//        printWriter.print("abc");
//        printWriter.println();
//
//        // println写各类型数据，写完后直接回车换行，返回值都是void
//        // 第二排
//        printWriter.println("this is a line");
//        // 第三排
//        printWriter.println(10);
//        // 第四排
//        printWriter.printf("%d", 11);
//        printWriter.println(" ");
//        // 第五排
//        printWriter.printf("%s", "shi");
//        printWriter.printf("%c", '&');
//        printWriter.println("hong&yuan");
//        // 第六排
//        printWriter.printf("%s", "zhen");
//        printWriter.printf("%c", '#');
//        printWriter.printf("%s", "bang");
//        printWriter.printf("%c", '\n');   // unix系统的回车换行符是\n  windows系统的是\r\n
//        // 第七排
//        printWriter.println("ha#");
//        // 第八排
//        printWriter.print("haha #");      // 最后一行不要用println，否则会多一行空行，在读入的时候会引起错误，所以最后一行用print写就行了
//
//        /** java.io.PrintWriter--- 关闭文件，才能保存，否则保存不了，写完后一定要调用close**/
//        printWriter.close();
//
//        /**
//         * 结果:
//         * 2 9.9 0.6 6 1 12 012abc
//         * this is a line
//         * 10
//         * 11
//         * shi&hong&yuan
//         * zhen#bang
//         * ha#
//         * haha #
//         */
//
//
//
//
//        /**
//         * java.util.Scanner --- 读文件
//         * 1、读控制台输入
//         * 2、读文件输入
//         * 3、读指定的字符串的值  Scanner scanner = new Scanner("shy 123 haha");
//         */
//
//        /** java.util.Scanner---构造方法 **/
//        java.util.Scanner scanner1 = null;
//        try {  // 要有文件找不到异常处理
////            File scannerFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
//            File scannerFile = new File("/Users/shihongyuan/mygithub/java/src/testfile.txt");
//            scanner1 = new java.util.Scanner(scannerFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        /** java.util.Scanner---读数据 **/
//        // 判断还有可读的数据吗，文件结束后必须不能有下一行，即使下一行是空的，也会被判定还有next可读
//        while (scanner1.hasNext()) {
//            // 以空白符或者回车符分割，读出对应类型的值转换成对应类型
//            byte b = scanner1.nextByte();       // 2
//            System.out.println(b);
//            float f = scanner1.nextFloat();     // 9.9
//            System.out.println(f);
//            double d = scanner1.nextDouble();   // 0.6
//            System.out.println(d);
//            short s = scanner1.nextShort();     // 6
//            System.out.println(s);
//            int i = scanner1.nextInt();         // 1
//            System.out.println(i);
//            long l = scanner1.nextLong();       // 12
//            System.out.println(l);
//
//            // 读出以空白符或者回车符分割的下一个值，不转换类型，直接返回其字符串的形式
//            String string = scanner1.next();
//            System.out.println(string);         // 0123abc
//
//            // 从空白符开始，以回车符或者换行符结束，返回回车符前（不包括回车符或者换行符）的字符串
//            scanner1.nextLine();                // ""  这一行已经被读出了0123abc，紧接着就是换行符，中间没有字符，所以输出空串
//            String stringline = scanner1.nextLine();
//            System.out.println(stringline);     // this is a line
//
//            scanner1.next();                 // 10
//            System.out.println(scanner1.nextLine());     // ""  这一行已经被读出了10，紧接着就是换行符，中间没有字符，所以输出空串
//            scanner1.next();                 // 11
//            System.out.println(scanner1.nextLine());     // " " 这一行已经被读出了10，紧接着就是空白符，换行符，换行符前只有空白符，所以输出空白符
//
//            /** java.util.Scanner---改变分隔符，改变分隔符后，不仅新的分隔符替代了原空白符的位置，回车符也不算分隔符，会被next()读进去 **/
//            // 字符串匹配
//            scanner1 = scanner1.useDelimiter("&");
//            System.out.println(scanner1.next());         // shi
//            System.out.println(scanner1.nextLine());     // &hong&yuan
//
//            // 正则匹配
//            scanner1 = scanner1.useDelimiter("[#*]");
//            System.out.println(scanner1.next());         // zhen
//            System.out.println(scanner1.next());         // bang回车ha
//            System.out.println(scanner1.next());         // ""回车"haha "
//            System.out.println(scanner1.nextLine());     // #
//
//
//        }
//
//        /** java.util.Scanner---关闭文件句柄，释放被文件占用的资源 **/
//        scanner1.close();        // void 返回值
//
//        /** java.util.Scanner---读指定的字符串的值 **/
//        Scanner scanner2 = new Scanner("shy 123 haha");
//        System.out.println(scanner2.next());             // shy
//        System.out.println(scanner2.nextInt());          // 123
//        System.out.println(scanner2.next());             // haha
//        scanner2.close();


        /***********
         * 二进制io: 写入读出的都是字节，比文本io效率高，.dat文件
         * 二进制io的所有方法都要声明必检异常:IOException (其子类FileNotFoundException也被包括了)
         */

        /** 根类--抽象类 java.io.InputStream  java.io.OutputStream**/
        /* java.io.InputStream */
        // int read() 读一个字节，转换成int，到达末尾，不读，返回-1
        // int read(byte[]) 读byte长度的字节给byte[]，返回字节个数，遇到末尾，不读，返回-1
        // int read(byte[], int off, int len) 读len长度的字节给byte[]，从off开始赋值，返回字节个数，遇到末尾，不读，返回-1
        // int available 能读的字节个数的估计数
        // int skip(int n) 向前跳过丢弃n个字节，返回实际跳过的字节数
        // boolean markSupported() 测试输入流是否支持mark和reset
        // void mark(int readline) 标记当前位置的输入流指针
        // void reset() 输入流指针恢复到最近一次mark的位置
        // void close() 关闭输入流，释放所占的资源

        /* java.io.OutputStream */
        // void write(int b) 写入一个整数对应的字节
        // void write(byte[]) 写入byte[]的所有字节
        // void write(byte[], int off, int len) 写入byte[]从off开始的len长度的字节
        // void flush() 刷新输出流，强制缓存写入文件
        // void close() 关闭输出流，释放所占的资源


        /** 继承根类---读写二进制文件的方式的是字节 java.io.FileInputStream  java.io.FileOutputStream **/
        /* java.io.FileInputStream */
        // 构造方法
        FileInputStream fileInputStream = new FileInputStream("src/testfile.txt");
        FileInputStream fileInputStream1 = new FileInputStream(new File("src/testfile.txt"));

        // 读文件方法，继承了所有的根类InputStream的方法
        // 读一个字符
        System.out.println(fileInputStream.read());         // 50 字符'2'的编码

        // 读多个字符
        byte[] bytes = new byte[2];
        System.out.println(fileInputStream.read(bytes));    // 2
        for (byte b: bytes) System.out.println(b);          // 32 57 一个空格' '，一个'9'
        bytes = new byte[10];
        System.out.println(fileInputStream.read(bytes, 2, 3));  // 3
        for (byte b: bytes) System.out.println(b);          // 0 0 46 57 32 0 0... 前两个没被赋值，一个'.'，一个'9'，一个' '
        System.out.println(fileInputStream.read(bytes, 2, 3));  // 3
        for (byte b: bytes) System.out.println(b);          // 0 0 48 46 54 0 0... 前两个没被赋值，一个'0'，一个'.'，一个'6'

        // 预估可读字符数
        System.out.println(fileInputStream.available());    // 71
        // 读字符数超过可读的字符数，读出可读的全部字符数，返回实际读的字符数
        bytes = new byte[1000];
        System.out.println(fileInputStream.read(bytes));    // 71
        System.out.println(bytes[0]);                       // 32  一个' '

        // 1000的时候读完了，再读读不出来了，返回-1，但是不抛出异常
        System.out.println(fileInputStream.read());         // -1
        bytes = new byte[1];
        System.out.println(fileInputStream.read(bytes));    // -1
        System.out.println(bytes[0]);                       // 0  末尾没读，默认值是0

        // 跳过'2'，读下一个空格
        System.out.println(fileInputStream1.skip(1));     // 1
        System.out.println(fileInputStream1.read());         // 32 一个空格' '

        // 标记恢复mark reset
        System.out.println(fileInputStream1.markSupported()); // false
//        fileInputStream1.mark(1);   // 标记空格的位置
//        fileInputStream1.skip(1);
//        fileInputStream1.reset();
//        System.out.println(fileInputStream1.read());         // 32 一个空格' '

        // 关闭输入流
        fileInputStream.close();
        fileInputStream1.close();

        /* java.io.FileOutputStream 不存在会创建文件 */
        // 构造方法
        FileOutputStream fileOutputStream = new FileOutputStream("src/outputstreamfile.txt");
        FileOutputStream fileOutputStream1 = new FileOutputStream(new File("src/outputstreamfile.txt"));

        // 覆盖文件
        fileOutputStream.write(49);   // 写入'1'
        bytes = new byte[] {50, 51};
        fileOutputStream.write(bytes);    // 写入'2' '3'
        fileOutputStream.write(bytes, 1, 1);    // 写入'3'
        fileOutputStream.flush();
        fileInputStream.close();
        fileOutputStream1.close();
        // 结果是1233

        // 追加文件 --- 除了继承抽象类的方法外，自己扩展的方法
        FileOutputStream fileOutputStream2 = new FileOutputStream("src/outputstreamfile.txt", true);                // 追加
        FileOutputStream fileOutputStream3 = new FileOutputStream(new File("src/outputstreamfile.txt"), false);  // 不追加
        // 如果文件已存在，调用基类构造方法或者apeend=false的构造方法的时候就会清空文件，结果是6
        fileOutputStream2.write(54);    // 追加写入'6', 但是是在文件已经被清空的情况下
        fileOutputStream2.close();
        fileOutputStream3.close();

        FileOutputStream fileOutputStream4 = new FileOutputStream(new File("src/outputstreamfile.txt"), true);
        fileOutputStream4.write(54);  // 追加写入'6', 结果是66
        fileOutputStream4.close();

        // 将二进制io的对象作为输入流或者输出流的对象去构造文本io
        Scanner scannerStream1 = new Scanner(new FileInputStream("src/testfile.txt"));
        Scanner scannerStream2 = new Scanner(new FileInputStream(new File("src/testfile.txt")));
        scannerStream1.close();
        scannerStream2.close();

        PrintWriter printWriterStream1 = new PrintWriter(new FileOutputStream("src/outputstreamfile.txt"));
        PrintWriter printWriterStream2 = new PrintWriter(new FileOutputStream(new File("src/outputstreamfile.txt")));
        printWriterStream1.close();
        printWriterStream2.close();

        /** 继承根类---标记性实现类---读写二进制文件的方式是基本数据类型和字符串 java.io.FilterInputStream  java.io.FilterOutputStream **/

        /** 标记性接口---读写二进制文件的方式是基本数据类型和字符串 java.io.DataInput java.io.DataOutput **/
         /* java.io.DataInput */
        // boolean readBoolean() 读一个boolean
        // byte readByte() 读一个byte
        // char readChar() 读一个char
        // float readFloat() 读一个float
        // double readDouble() 读一个double
        // int readInt() 读一个int
        // long readLong() 读一个long
        // short readShort() 读一个short
        // String readLine() 读一行，按照一个char一个char的字符转换
        // String readUTF()  按照UTF的格式读字符串（跟系统的编码方式无关，这是与文本io的差别）

        /* java.io.DataOutput */
        // void writeBoolean(boolean) 写入一个boolean
        // void writeByte(byte) 写入一个byte
        // void writeChar(char) 写入一个char （2个字节，所以就跟系统的编码方式无关了，这是与文本io的差别）
        // void writeFloat(float) 写入一个float
        // void writeDouble(double) 写入一个double
        // void writeInt(int) 写入一个int
        // void writeLong(long) 写入一个long
        // void writeShort(short) 写入一个short
        // void writeBytes(String) 取出一个String的每个字符的低字节（byte），写入
        // void writeChars(String) 取出一个String的每个2个字节（char），写入（char跟系统的编码方式无关，这是与文本io的差别）
        // String writeUTF(String)  按照UTF的格式写字符串（跟系统的编码方式无关，这是与文本io的差别）

        /** 继承标记性类FilterInputStream和FilterOutputStream，实现标记性接口DataInput和DataOutput---读写二进制文件的方式是基本数据类型和字符串 java.io.DataInputStream java.io.DataOutputStream **/
        /* java.io.DataOutputStream */
        // 构造方法
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/filteroutputstream.txt"));
        DataOutputStream dataOutputStream1 = new DataOutputStream(new FileOutputStream(new File("src/filteroutputstream.txt")));

        // 写文件
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeLong(2);
        dataOutputStream.writeFloat(3.3f);
        dataOutputStream.writeChar('a');
        dataOutputStream.writeBytes("abc");   // abc都只占一个字节
        dataOutputStream.writeChars("def");   // def都只占一个字节，所以写入 d e f
        dataOutputStream.writeUTF("shihonbyuan");
        dataOutputStream.write(49);   // '1'
        dataOutputStream.flush();

        dataOutputStream.close();
        dataOutputStream1.close();

        /* java.io.DataInputStream */
        // 构造方法 DataInputStream(InputStream)
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("src/filteroutputstream.txt"));
        DataInputStream dataInputStream1 = new DataInputStream(new FileInputStream(new File("src/filteroutputstream.txt")));

        // 读文件
        System.out.println(dataInputStream.readBoolean());  // true
        System.out.println(dataInputStream.readInt());      // 1
        System.out.println(dataInputStream.readLong());     // 2
        System.out.println(dataInputStream.readFloat());    // 3.3
        System.out.println(dataInputStream.readChar());     // 'a'

        System.out.println(dataInputStream.readByte());     // 97 'a'
        System.out.println(dataInputStream.readByte());     // 98 'b'
        System.out.println(dataInputStream.readByte());     // 99 'c'
        System.out.println(dataInputStream.readChar());     // 'd'
        System.out.println(dataInputStream.readChar());     // 'e'
        System.out.println(dataInputStream.readChar());     // 'f'

//        System.out.println(dataInputStream.readLong());     // abc d e f shihonbyuan1
        System.out.println(dataInputStream.readUTF());      // shihongyuan  按照对应关系读，writeUTF写就以readUTF读
        System.out.println(dataInputStream.readByte());     // 49


        // 文件末尾还读DataInput的方法，跟InputStream.read()不一样，会抛出java.io.EOFException异常
        try {
            dataInputStream.read();
        }
        catch (java.io.EOFException ex) {
            System.out.println(ex.getStackTrace()[0].getClassName());  // 没有抛出异常
        }

        try {
            dataInputStream.readDouble();
        }
        catch (java.io.EOFException ex) {
            System.out.println(ex.getStackTrace()[0].getClassName());  // 抛出异常 java.io.DataInputStream
        }

        dataInputStream.close();
        dataInputStream1.close();

        /** 继承标记性类FilterInputStream和FilterOutputStream --- 读写二进制文件的方式是二进制,且为输入输出流创建缓存区 java.io.BufferedInputStream java.io.BufferedOutputStream **/
        /* java.io.BufferedOutputStream 不存在会创建文件 */
        // 构造方法  --- 带缓存区，默认是512个字节，可以指定
        // 不指定，默认缓存区是512个字节
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("src/filteroutputstream.txt"));
        // 指定缓存区1024个字节
        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream(new File("src/filteroutputstream.txt")), 1024);

        // 写文件，跟FileOutputStream一样
        bufferedOutputStream.write(49);   // '1'
        // 每次缓冲区满了或者调用flush，才会写入文件，否则文件是空的，读会有EOFException异常
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        /* java.io.BufferedInputStream 不存在会创建文件 */
        // 构造方法  --- 带缓存区，默认是512个字节，可以指定
        // 不指定，默认缓存区是512个字节
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/filteroutputstream.txt"));
        // 指定缓存区1024个字节
        BufferedInputStream bufferedInputStream1 = new BufferedInputStream(new FileInputStream(new File("src/filteroutputstream.txt")), 1024);

        // 写文件，跟FileInputStream一样
        // 每次读都尽量占满缓存区，read的时候从缓存区读就比较快
        System.out.println(bufferedInputStream.read());   // 49
        bufferedInputStream.close();

        // 复制文件
        int r = 0;
        while ((r = bufferedInputStream1.read()) != -1) {
            bufferedOutputStream1.write(r);
        }

        bufferedInputStream1.close();
        bufferedOutputStream1.close();

        // 构造能读写基本数据类型又能带缓存区的DataInputStream和DataOutputStream
        DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new FileInputStream("src/filteroutputstream.txt")));
        DataOutputStream dataOutputStream2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/filteroutputstream.txt")));
        dataInputStream2.close();
        dataOutputStream2.close();

        /** 继承抽象类InputStream和OutputStream，实现extends DataInput和DataOutput的接口ObjectInput和ObjectOutput，常量接口ObjectStreamConstants --- 读写二进制文件的方式基本数据类型和字符串，和对象  java.io.ObjectInputStream java.io.ObjectOutputStream **/
        /* java.io.ObjectOutputStream */
        // 构造方法
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/filteroutputstream.txt"));
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream(new File("src/filteroutputstream.txt")));

        // 写入对象  void writeObject(Object)
        // 只有实现了java.io.Serializable 的对象才是可序列化的对象，才能写入输出流或者网络传输
        objectOutputStream.writeObject(new TestObject(222));
        objectOutputStream.close();
        objectOutputStream1.close();

        /* java.io.ObjectInputStream */
        // 构造方法
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/filteroutputstream.txt"));
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream(new File("src/filteroutputstream.txt")));
        // 读出对象 Object readObject()  可序列化对象的反序列化过程
        try {
            TestObject testObject = (TestObject) objectInputStream.readObject();  // readObject会抛出必检异常ClassNotFoundException
            System.out.println(testObject.n);                                     // 222
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        objectInputStream.close();
        objectInputStream1.close();


        // 构造能读写object又带有缓存区的ObjectInputStream和ObjectOutputStream
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/filteroutputstream.txt")));
        /** 序列化接口java.io.Serializable
         *  序列化对象不会存储static变量的值
         *  序列化对象，必须是每个数据域都是可序列化对象，否则可使用transient忽略数据域，不进行编码存储，不会被持久化和恢复，transient只能修饰变量，不能修饰类和方法
         */
        // 序列化数组，要求数组的每个元素都是可序列化的
        int[] nums = {1, 2, 3};
        String[] strings = {"a", "b", "c"};
        objectOutputStream2.writeObject(nums);
        objectOutputStream2.writeObject(strings);
        objectOutputStream2.flush();

        ObjectInputStream objectInputStream2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/filteroutputstream.txt")));
        int[] nums1 = (int[]) objectInputStream2.readObject();  // main声明了ClassNotFoundException
        String[] strings1 = (String[]) objectInputStream2.readObject();
        for (int i: nums1) System.out.println(i);      // 1 2 3
        for (String s: strings1) System.out.println(s);   // a b c

        objectOutputStream2.close();
        objectInputStream2.close();

        /**
         * 随机访问文件 文件指针
         * 继承Object，实现标记性接口DataInput和DataOutput---读写二进制文件的方式是基本数据类型和字符串 java.io.RandomAccessFile
         */
        // 构造方法 会报出FileNotFoundException的异常
        // 文件存在，创建文件指针
        // 文件不存在： 1、只读的文件会抛出异常，2、读写的文件新建一个空文件和文件指针
        java.io.RandomAccessFile randomAccessFile = new RandomAccessFile("src/filteroutputstream.txt", "r");   // 只读

        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile("src/randomaccessfile.txt", "r"); // 可读写
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());      // src/randomaccessfile.txt (No such file or directory)
        }
        RandomAccessFile randomAccessFile3 = new RandomAccessFile("src/randomaccessfile.txt", "rw"); // 可读写

        RandomAccessFile randomAccessFile4 = new RandomAccessFile(new File("src/randomaccessfile.txt"), "rw"); // 可读写


        // 文件存在，创建文件指针
        // 文件字节数
        System.out.println(randomAccessFile.length());     // 91
        System.out.println(randomAccessFile3.length());    // 0
        System.out.println(randomAccessFile4.length());    // 0

        // 写数据
        randomAccessFile3.write(1);                   // 写入1
        byte[] byte1 = new byte[] {1,2,3};
        randomAccessFile3.write(byte1);                  // 写入123
        randomAccessFile3.write(byte1, 2, 1);    // 写入3

        // 读数据
        randomAccessFile3.read();
        byte[] byte2 = new byte[] {1,2,3};
        randomAccessFile3.read(byte2);
        randomAccessFile3.read(byte2, 1, 1);

        // 实现接口DataInput和DataOutput，基本数据类型和string方式读写


        // 设置文件的新长度
        randomAccessFile3.setLength(10);

        // 文件指针跳过n个字节
        randomAccessFile3.skipBytes(2);

        // 设置文件指针相对于文件开始位置的偏移量
        randomAccessFile3.seek(7);
        randomAccessFile3.seek(0);                      // 文件开始位置
        randomAccessFile3.seek(randomAccessFile3.length()); // 文件结束位置
        randomAccessFile3.read();                           // 文件结束位置，没有字节了，返回-1

        // 获取文件指针相对于文件开始位置的偏移量
        randomAccessFile3.getFilePointer();

        // 关闭文件，释放资源
        randomAccessFile.close();
        randomAccessFile3.close();
        randomAccessFile4.close();

    }

    /** 只有实现了java.io.Serializable 的对象或者数组才是可序列化的，才能写入输出流或者网络传输
     *  Serializable是个标记性的接口，没有可实现的抽象方法，实现这个接口会启动java的序列化机制，自动完成存储对象和数组的过程
     */
    static class TestObject implements Serializable{
        int n;
        static int non;             // 序列化时不编码不存储，当对象被反序列化时，被transient修饰的变量值不会被持久化和恢复。transient只能修饰变量，不能修饰类和方法
        transient A a = new A();    // 由于A没有实现序列化接口，不可序列化，会影响到时TestObject的序列化，所以序列化时要忽略
        public TestObject (int n) {
            this.n = n;
        }
    }
    static class A{
        int n = 1;
    }
}
