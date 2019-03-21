import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;


public class FileRW {
    public static void main(String[] args) {

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


        /**
         * java.io.PrintWriter--向文件对象写文件
         * 1、文件不存在就创建文件
         * 2、文件存在就覆盖文件，创建的时候，还没写的时候，就把源文件清空了
         */

        /** java.io.PrintWriter---构造方法 **/

        try {   // 要有文件找不到异常处理

            // 从文件对象创建文件
//            File scannerFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
            File scannerFile = new File("/Users/shihongyuan/mygithub/java/src/testfile.txt");
            PrintWriter printWriter1 = new PrintWriter(scannerFile);

            // 从文件路径字符串创建文件
//            PrintWriter printWriter2 = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
            PrintWriter printWriter2 = new PrintWriter("/Users/shihongyuan/mygithub/java/src/testfile.txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /** java.io.PrintWriter--- 写入文件 **/

        PrintWriter printWriter = null;
        try {
//            printWriter = new PrintWriter("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
            printWriter = new PrintWriter("/Users/shihongyuan/mygithub/java/src/testfile.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // print写各类型数据，写进去就成了string，没间隔不换行，返回值都是void
        // 第一排
        printWriter.print((byte)2);
        printWriter.print(' ');
        printWriter.print(9.9);
        printWriter.print(' ');
        printWriter.print(0.6f);
        printWriter.print((char)32);  // 空格的asc2码是32
        printWriter.print((short)6);
        printWriter.print('\u0020');  // 统一码的 \u0020=32
        printWriter.print(1);
        printWriter.print(" ");
        printWriter.print(12L);
        printWriter.print(" ");
        printWriter.print('0');
        printWriter.print(new char[]{'1','2'});
        printWriter.print("abc");
        printWriter.println();

        // println写各类型数据，写完后直接回车换行，返回值都是void
        // 第二排
        printWriter.println("this is a line");
        // 第三排
        printWriter.println(10);
        // 第四排
        printWriter.printf("%d", 11);
        printWriter.println(" ");
        // 第五排
        printWriter.printf("%s", "shi");
        printWriter.printf("%c", '&');
        printWriter.println("hong&yuan");
        // 第六排
        printWriter.printf("%s", "zhen");
        printWriter.printf("%c", '#');
        printWriter.printf("%s", "bang");
        printWriter.printf("%c", '\n');   // unix系统的回车换行符是\n  windows系统的是\r\n
        // 第七排
        printWriter.println("ha#");
        // 第八排
        printWriter.print("haha #");      // 最后一行不要用println，否则会多一行空行，在读入的时候会引起错误，所以最后一行用print写就行了

        /** java.io.PrintWriter--- 关闭文件，才能保存，否则保存不了，写完后一定要调用close**/
        printWriter.close();

        /**
         * 结果:
         * 2 9.9 0.6 6 1 12 012abc
         * this is a line
         * 10
         * 11
         * shi&hong&yuan
         * zhen#bang
         * ha#
         * haha #
         */




        /**
         * java.util.Scanner --- 读文件
         * 1、读控制台输入
         * 2、读文件输入
         * 3、读指定的字符串的值  Scanner scanner = new Scanner("shy 123 haha");
         */

        /** java.util.Scanner---构造方法 **/
        java.util.Scanner scanner1 = null;
        try {  // 要有文件找不到异常处理
//            File scannerFile = new File("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/testfile.txt");
            File scannerFile = new File("/Users/shihongyuan/mygithub/java/src/testfile.txt");
            scanner1 = new java.util.Scanner(scannerFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /** java.util.Scanner---读数据 **/
        // 判断还有可读的数据吗，文件结束后必须不能有下一行，即使下一行是空的，也会被判定还有next可读
        while (scanner1.hasNext()) {
            // 以空白符或者回车符分割，读出对应类型的值转换成对应类型
            byte b = scanner1.nextByte();       // 2
            System.out.println(b);
            float f = scanner1.nextFloat();     // 9.9
            System.out.println(f);
            double d = scanner1.nextDouble();   // 0.6
            System.out.println(d);
            short s = scanner1.nextShort();     // 6
            System.out.println(s);
            int i = scanner1.nextInt();         // 1
            System.out.println(i);
            long l = scanner1.nextLong();       // 12
            System.out.println(l);

            // 读出以空白符或者回车符分割的下一个值，不转换类型，直接返回其字符串的形式
            String string = scanner1.next();
            System.out.println(string);         // 0123abc

            // 从空白符开始，以回车符或者换行符结束，返回回车符前（不包括回车符或者换行符）的字符串
            scanner1.nextLine();                // ""  这一行已经被读出了0123abc，紧接着就是换行符，中间没有字符，所以输出空串
            String stringline = scanner1.nextLine();
            System.out.println(stringline);     // this is a line

            scanner1.next();                 // 10
            System.out.println(scanner1.nextLine());     // ""  这一行已经被读出了10，紧接着就是换行符，中间没有字符，所以输出空串
            scanner1.next();                 // 11
            System.out.println(scanner1.nextLine());     // " " 这一行已经被读出了10，紧接着就是空白符，换行符，换行符前只有空白符，所以输出空白符

            /** java.util.Scanner---改变分隔符，改变分隔符后，不仅新的分隔符替代了原空白符的位置，回车符也不算分隔符，会被next()读进去 **/
            // 字符串匹配
            scanner1 = scanner1.useDelimiter("&");
            System.out.println(scanner1.next());         // shi
            System.out.println(scanner1.nextLine());     // &hong&yuan

            // 正则匹配
            scanner1 = scanner1.useDelimiter("[#*]");
            System.out.println(scanner1.next());         // zhen
            System.out.println(scanner1.next());         // bang回车ha
            System.out.println(scanner1.next());         // ""回车"haha "
            System.out.println(scanner1.nextLine());     // #


        }

        /** java.util.Scanner---关闭文件句柄，释放被文件占用的资源 **/
        scanner1.close();        // void 返回值

        /** java.util.Scanner---读指定的字符串的值 **/
        Scanner scanner2 = new Scanner("shy 123 haha");
        System.out.println(scanner2.next());             // shy
        System.out.println(scanner2.nextInt());          // 123
        System.out.println(scanner2.next());             // haha
        scanner2.close();

    }
}
