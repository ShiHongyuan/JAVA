package IOSystem;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOChannelClient {
    public static void main(String[] args) throws Exception {

        // Channel是连接客户端和服务端的通道

        // 客户端

        // 1.通过SocketChannel的open()方法创建一个SocketChannel对象
        SocketChannel socketChannel = SocketChannel.open();

        // 2.连接到远程服务器（连接此通道的socket）
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));

        // 3.创建写数据缓存区对象，向服务器写数据
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("hello WebServer this is from WebClient".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        // 4. 创建读数据缓存区对象，从服务器读数据
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        socketChannel.read(readBuffer);
        //String 字符串常量，不可变；StringBuffer 字符串变量（线程安全），可变；StringBuilder 字符串变量（非线程安全），可变
        StringBuilder stringBuider = new StringBuilder();
        //将Buffer从写模式变为可读模式
        readBuffer.flip();
        while(readBuffer.hasRemaining()) {
            stringBuider.append((char) readBuffer.get());
        }
        System.out.println("从服务端接收到的数据：" + stringBuider);

        // 5. 关闭连接服务器和客户端的Channel
        socketChannel.close();
    }
}
