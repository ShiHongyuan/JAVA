package IOSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOSelectorServer {
    public static void main(String[] args) throws Exception {
        // *****************  单线程管理多个网络链接

        // 服务器端：
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 3000));
            ssc.configureBlocking(false);

            // 注册 channel，并且指定感兴趣的事件是 Accept
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            // 分别创建读写缓冲区，并分别读写数据
            ByteBuffer readBuff = ByteBuffer.allocate(50);
            ByteBuffer writeBuff = ByteBuffer.allocate(50);
            writeBuff.put("hello client! this is from server".getBytes());
            writeBuff.flip();


            while (true) {
                // 这里线程会轮询每个通道，有没有准备就绪，轮询期间线程是阻塞的
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                // 上一波轮询有没有准备就绪的通道，如果没有，就不进入循环
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        // 创建新的连接，并且把连接注册到selector上，而且声明这个channel只对读操作感兴趣。
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        readBuff.flip();
                        System.out.println("服务器 received : " + new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_READ);
                    } else if(key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // writeBuff 前面已写入数据，已变为可读，这里就不断的写入数据
                        writeBuff.rewind();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
