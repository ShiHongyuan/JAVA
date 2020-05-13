package IOSystem;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * Paths是Path的工具类，用来管理Path类的对象的
 * Files是File的工具类，用来管理File类的对象的
 *
 * 现在Path类可以替换原来的File类了，所以Files工具类也可以用来管理Path类的对象
 */

public class PathAndFiles {

    public static void main(String[] args) throws Exception {

        /*****************  Paths工具类 ——> 管理Path类的对象  ************************/

        // *******************  创建Path实例

        //使用绝对路径
        Path path1 = Paths.get("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt");

        //使用相对路径
        Path path2 = Paths.get("./src/IOSystem/PathFile.txt");

        //使用FileSystems工具类
        Path path3 = FileSystems.getDefault().getPath("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt");

        // *******************  Path实例与File实例的转换
        //Path可以转换成File
        File file1 = path1.toFile();

        //File也可以获取Path
        Path path4 = file1.toPath();

        //File也可以获取文件的URI
        URI uri = file1.toURI();
        System.out.println(uri);//file:/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt

        // *******************  通过Path对象获取文件的相关信息

        Path path = Paths.get("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt");
        System.out.println("文件名：" + path.getFileName());//文件名：PathFile.txt
        System.out.println("文件路径中名称的数量：" + path.getNameCount());//文件路径中名称的数量：9
        System.out.println("父路径：" + path.getParent());//父路径：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem
        System.out.println("根路径：" + path.getRoot());//根路径：/
        System.out.println("是否是绝对路径：" + path.isAbsolute());//是否是绝对路径：true

        //startsWith()方法的参数既可以是字符串也可以是Path对象
        System.out.println("是否是以为给定的路径/Users/Tinashy/开始：" + path.startsWith("/Users/Tinashy/"));//否是以为给定的路径/Users/Tinashy/开始：true
        System.out.println("该路径的字符串形式：" + path.toString());//该路径的字符串形式：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt


        // *******************  使用Path类的normalize()和toRealPath()方法把.和..去除
        // .表示的是当前目录
        //..表示父目录或者说是上一级目录

        // normalize() : 返回一个路径，该路径不包含冗余名称，即./，../等
        // toRealPath() : 融合了toAbsolutePath()方法和normalize()方法，返回一个路径，该路径是绝对路径且不冗余

        // .表示的是当前目录
        Path currentDir = Paths.get(".");
        System.out.println(currentDir.toAbsolutePath());///Users/Tinashy/IdeaProjects/mygithub/java/JAVA/.
        Path currentDir2 = Paths.get("./src/IOSystem/PathFile.txt");
        System.out.println("原始路径格式：" + currentDir2.toAbsolutePath());//原始路径格式：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/./src/IOSystem/PathFile.txt
        System.out.println("执行normalize（）方法之后：" + currentDir2.toAbsolutePath().normalize());//执行normalize（）方法之后：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt
        System.out.println("执行toRealPath()方法之后：" + currentDir2.toRealPath());//执行toRealPath()方法之后：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt

        //..表示父目录或者说是上一级目录
        Path currentDir3 = Paths.get("..");
        System.out.println("原始路径格式：" + currentDir3.toAbsolutePath());//原始路径格式：/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/..
        System.out.println("执行normalize（）方法之后：" + currentDir3.toAbsolutePath().normalize());//执行normalize（）方法之后：/Users/Tinashy/IdeaProjects/mygithub/java
        System.out.println("执行toRealPath()方法之后：" + currentDir3.toRealPath());//执行toRealPath()方法之后：/Users/Tinashy/IdeaProjects/mygithub/java

        /*****************  Files工具类 ——> 管理Path类的对象 ************************/

        Path pathFiles = Paths.get("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile.txt");

        // *******************  检查给定的path对象在文件系统中是否存在
        // 第二个参数是可选项，影响到Files.exists()如何确定一个路径是否存在。这个数组内包含了LinkOptions.NOFOLLOW_LINKS，表示检测时不包含符号链接文件。
        boolean pathExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        System.out.println(pathExists);//true

        // *******************  创建文件

        try {
            if (!Files.exists(path))
                Files.createFile(pathFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // *******************  创建文件夹

        // Files.createDirectories()会首先创建所有不存在的父目录来创建目录。
        try {
            Path newDir = Files.createDirectories(pathFiles);
        } catch (FileAlreadyExistsException e) {
            System.out.println(" the directory of the path already exists.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 而Files.createDirectory()方法只是创建目录，如果它的上级目录不存在就会报错。
        try {
            Path newDir = Files.createDirectory(Paths.get("/Users/Tinashy/IdeaProjects/mygithub/java/notexists/src/IOSystem/PathFile.txt"));
        } catch (FileAlreadyExistsException e) {
            System.out.println(" the directory of the path already exists.");
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // *******************  删除文件或目录

        // 删除文件
        try {
            Files.delete(pathFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (!Files.exists(path))
                Files.createFile(pathFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // *******************  复制文件

        Path sourcePath = pathFiles;
        Path destinationPath = Paths.get("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/PathFile-copy.txt");

        // 复制文件已存在，报错
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 复制文件已存在，覆盖，只需要添加参数 StandardCopyOption.REPLACE_EXISTING
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.delete(destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // *******************  获取文件属性
        System.out.println(Files.getLastModifiedTime(pathFiles));//2019-08-10T13:51:52Z
        System.out.println(Files.size(pathFiles));//0
        System.out.println(Files.isSymbolicLink(pathFiles));//false
        System.out.println(Files.isDirectory(pathFiles));//false
        //文件的所有属性： {lastAccessTime=2019-08-10T13:51:52Z, lastModifiedTime=2019-08-10T13:51:52Z, size=0, creationTime=2019-08-10T13:51:52Z, isSymbolicLink=false, isRegularFile=true, fileKey=(dev=1000004,ino=105902889), isOther=false, isDirectory=false}
        System.out.println("文件的所有属性： " + Files.readAttributes(pathFiles, "*"));



        // *******************  遍历单层目录
        Path dir = Paths.get("..");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path p : stream) {
                System.out.println(p.getFileName());
                //.DS_Store
                //.git
                //.idea
                //HelloWeb
                //Hibernate
                //JAVA
                //Log
                //Maven
                //MQ
                //NetFreamworks
                //RPC
                //Servlet
            }
        } catch (IOException e) {

        }


        // *******************  遍历递归目录 File.walkFileTree

        // walkFileTree有两个参数：
        //          Path对象是需要遍历的目录.
        //          FileVistor则会在每次遍历中被调用。FileVisitor需要调用方自行实现，FileVisitor的每个方法会在遍历过程中被调用多次。
        //          如果不需要处理每个方法，那么可以继承它的默认实现类SimpleFileVisitor，它将所有的接口做了空实现。

        List<Path> result = new LinkedList<>();
        Files.walkFileTree(dir, new FindJavaVisitor1(result));
        System.out.println(result.size());//78


        // *******************  Files类还有其他很多操作比如：读取和设置文件权限、更新文件所有者等等操作

    }

    // *******************  遍历递归目录 File.walkFileTree
    private static class  FindJavaVisitor1 extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor1(List<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if (path.toString().endsWith(".java")) {
                System.out.println(path.getFileName());
                result.add(path.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
