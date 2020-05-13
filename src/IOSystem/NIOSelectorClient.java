package IOSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOSelectorClient {
    public static void main(String[] args) throws Exception {
        // *****************  单线程管理多个网络链接

        // 客户端：
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 3000));

            ByteBuffer writeBuffer = ByteBuffer.allocate(50);
            ByteBuffer readBuffer = ByteBuffer.allocate(50);

            writeBuffer.put("hello server! this is from client".getBytes());
            writeBuffer.flip();

            while (true) {
                // 客户端不断重复发相同消息，服务端会不断收到客户端发送过来的消息
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
