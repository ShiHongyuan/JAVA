package IOSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOChannelServer {
    public static void main(String[] args) throws Exception {
        // Channel是连接客户端和服务端的通道

        // 服务器：
        // ServerSocketChannel 允许我们监听TCP链接请求，
        // 通过ServerSocketChannelImpl的 accept()方法 可以创建一个SocketChannel对象
        // 服务器创建的SocketChannel对象和客户端创建的SocketChannel对象相连，服务器和客户端才能互相交换数据
        try {
            //1.通过ServerSocketChannel 的open()方法创建一个ServerSocketChannel对象，open方法的作用：打开套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();

            //2.通过ServerSocketChannel绑定ip地址和port(端口号)
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 3333));

            //3. 通过ServerSocketChannelImpl的accept()方法创建一个SocketChannel对象用户从客户端读/写数据
            SocketChannel socketChannel = ssc.accept();

            //4.创建写数据的缓存区对象，向客户端写数据
            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("hello WebClient this is from WebServer".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            //5. 创建读数据的缓存区对象，从客户端读数据
            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            socketChannel.read(readBuffer);
            StringBuilder stringBuilder = new StringBuilder();
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                stringBuilder.append((char) readBuffer.get());
            }
            System.out.println("从客户端接收到的数据：" + stringBuilder);
            // 5. 关闭连接服务器和客户端的Channel，关闭通到监听器ServerSocketChannel
            socketChannel.close();
            ssc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
