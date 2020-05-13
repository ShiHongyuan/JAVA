package IOSystem;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java NIO 是 java 1.4 之后新出的一套IO接口，NIO中的N可以理解为Non-blocking，不单纯是New
 * Java NIO 是基于通道，面向缓冲，支持多通道复用的I/O操作方法
 * 在操作系统的IO层面，NIO是伪异步，即同步非阻塞
 *
 * NIO 的三个特性
 *      1. IO是面向流的，NIO是面向缓冲区的：
 *          IO：传统的IO是面向字节流和字符流的，即使使用了缓冲处理流，应用程序还是会从内存一个一个字节/字符的读出来放到缓存区，或者从缓存区一个一个的读进内存。
 *          NIO：而NIO的应用程序可以直接读写缓存，再通过通道与数据源交互。
 *
 *
 *      2. IO流是阻塞的，NIO流是不阻塞的：
 *          IO：IO流是阻塞的。意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。
 *          NIO：NIO是非阻塞IO操作。单线程中从通道读取数据到buffer，同时可以继续做别的事情，当数据读取到buffer中后，线程再继续处理数据。写数据也是一样的。另外，非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。
 *
 *      3. IO没有选择器，NIO有选择器可以使用单线程模拟多线程
 *          IO：IO没有选择器，只能一个进程或者线程处理一个流。
 *          NIO：可以使用选择器让单个线程绑定多个通道处理。因此可以用较少的线程来处理这些通道，线程之间的切换对于操作系统来说是昂贵的，因此，为了提高系统效率选择器是有用的。
 *
 *
 * NIO 实现三个特性的三个核心组件
 *      1. 通道channel （& 缓存区）：channel是连接数据源和缓冲区的，相当于BIO流的作用，但区别是它是可以同步也可以异步的，读写都可以的，数据通过channel从数据源读到buffer内，应用程序线程直接从buffer取数据，或者应用程序直接向buffer写数据，buffer再通过channel写入到数据源。
 *      2. 缓冲区buffer：buffer在内存中，应用程序直接与缓冲区交互，这也是NIO与IO区别的一个地方，直接读写缓冲区的好处就是可以提高读写效率。
 *      3. 选择器Selector：单个线程创建的Selector上可以注册多个channel，相当于单个线程可以模拟多线程同时管理多个读写IO，避免了上线文切换带来的开销。
 *                        如果应用程序有多个通道(连接)打开，但每个连接的流量都很低，则可考虑使用它。 例如：在聊天服务器中。
 *
 *
 *
 * 通道channel的使用：
 *      重要的Channel实现：
 *          FileChannel：文件IO，实现的是同步的Channel，通过Channel读写数据时，需要等到读写完成返回读写字节数
 *          DatagramChannel：UDP 网络IO，实现的是同步的Channel
 *          SocketChannel：TCP 网络IO，客户端，实现的是同步的Channel
 *          ServerSocketChannel：TCP 网络IO，服务器端，实现的是同步的Channel
 *
 *
 *      Channel通道和流非常相似，区别是：
 *          通道可以读也可以写，流一般来说是单向的，要么输入，要么输出。
 *          通道可以异步读写（把数据写入buffer后就不管了，Channel自己慢慢写入数据源，或者自己从数据源读）。
 *              只是FileChannel、SocketChannel、ServerSocketChannel实现的都是同步的Channel
 *
 *      常用方法：
 *          1. 从buffer写数据到Channel：inChannel.write(buffer);
 *          2. 从Channel读数据到buffer：inChannel.read(buffer);
 *          3. 网络IOChannel，连接服务器：socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));
 *          4. 网络IOChannel，等待客户端连接请求：serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 3333));
 *                                           serverSocketChannel.accept();
 *
 * 缓冲区buffer的使用：
 *      核心缓冲区 —— 通过I/O发送基本数据类型
 *      ByteBuffer
 *      CharBuffer
 *      ShortBuffer
 *      IntBuffer
 *      FloatBuffer
 *      DoubleBuffer
 *      LongBuffer
 *
 *      利用Buffer读写数据，通常遵循四个步骤：
 *          1. 把数据写入buffer（这个写包括应用程序写入buffer，再通过channel写入数据源；也包括数据源通过channel向buffer写数据）
 *          2. 调用flip，把buffer转换成可读缓存。（应用程序可以读，channel也可以读）
 *          3. 从Buffer中读取数据（这个读包括应用程序读出来处理数据；也包括channel读出来写入数据源）
 *          4. 清空旧数据，写入新数据，否则直接写新数据会超过buffer空间报错
 *                  调用buffer.clear()：清空全部数据；
 *                  或者调用buffer.compact()：清空已读取的数据，未被读取的数据会被移动到buffer的开始位置，写入位置则近跟着未读数据之后
 *
 *       buffer的三个属性：
 *          capacity容量：buffer分配的空间
 *          limit限制：有效数据长度
 *          position位置：当前读写指针
 *
 *
 *       常用方法：
 *          1. 分配缓冲区: ByteBuffer buffer = ByteBuffer.allocate(33);
 *          2. 读数据前，只读有效数据position=0，limit=有效数据长度: buffer.flip();
 *          3. 重新读数据，position=0，limit不变：buffer.rewind();
 *          4. 写数据前，清空缓冲区，position=0，limit与capacity同长：buffer.clear();
 *          5. 写数据前，只清空未读数据，移动已读数据到最前面，position=未读数据后面limit与capacity同长：buffer.compact();
 *          6. 获取数据：(char)buffer.get();
 *          7. 获取指定位置数据：(char)buffer.get(2);
 *          8. 获取指定长度的数据：byte[] dst = new byte[10];  buffer.get(dst, 0, 2);
 *          9. 写数据：buffer.put((byte)'a')
 *          10. 写入指定位置：buffer.put(2, (byte)'b');
 *          11. 写入一段数据：buffer.put("abcd".getBytes()); buffer2.put(buffer1);
 *          12. 手动移动指针：buffer.position(5);
 *          13. 手动移动有效读取数据区域：buffer.limit(15);
 *          14. 手动mark指针位置和恢复位置：buffer.mark();  buffer.reset();
 *
 *
 *
 * 选择器Selector的使用：
 *      FileChannel没有继承SelectableChannel，不能使用选择器，只有Socket channel可以使用选择器
 *
 *      SelectableChannel抽象类 有一个 configureBlocking（） 方法用于使通道处于阻塞模式或非阻塞模式。
 *      SelectableChannel抽象类的configureBlocking（） 方法是由 AbstractSelectableChannel抽象类实现的，SocketChannel、ServerSocketChannel、DatagramChannel都是直接继承了 AbstractSelectableChannel抽象类
 *
 *      Selector事件：
 *          Selector监听的是Channel感兴趣的事件，Channel在注册到Selector上时需要指明事件，事件类型包括：
 *              Connect —— SelectionKey.OP_CONNECT：某个Channel成功连接到另一个服务器称为“ 连接就绪 ”
 *              Accept —— SelectionKey.OP_ACCEPT：一个Server Socket Channel准备好接收新进入的连接称为“ 接收就绪”
 *              Read —— SelectionKey.OP_READ：一个有数据可读的通道可以说是“ 读就绪 ”
 *              Write —— SelectionKey.OP_WRITE：等待写数据的通道可以说是“ 写就绪 ”
 *
 *          如果一个Channel对多事件感兴趣，使用：int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE
 *
 *
 *      SelectionKey：
 *
 *          一个SelectionKey表示选择器和通道之间已将建立的关系，包括信息有哪个通道在哪个选择器上注册了哪个类型的事件。
 *          key.attachment(); //返回SelectionKey的attachment，attachment可以在注册channel的时候指定。
 *          key.channel(); // 返回该SelectionKey对应的channel。
 *          key.selector(); // 返回该SelectionKey对应的Selector。
 *          key.interestOps(); //返回代表需要Selector监控的事件类型的bit mask
 *          key.readyOps(); // 返回一个bit mask，代表在相应channel上已经准备好的事件可以操作。
 *
 *      Selector的select（）方法：
 *          select（）方法：会依次询问每个通道是否已经就绪，这个过程可能会造成调用线程进入阻塞，直到选择到已经准备就绪的通道们。
 *
 *          int select()：阻塞到至少有一个通道在你注册的事件上就绪了。int值表示有多少通道已经就绪,是自上次调用select()方法后有多少通道变成就绪状态。之前在select（）调用时进入就绪的通道不会在本次调用中被记入。
 *          int select(long timeout)：和select()一样，但最长阻塞时间为timeout毫秒。
 *          int selectNow()：非阻塞，只要有通道就绪就立刻返回。
 *
 *          Set selectedKeys=selector.selectedKeys(); //通过调用Selector的selectedKeys()方法来访问已准备就绪的键集合
 *
 *          有以下两种方式可以唤醒在select（）方法中阻塞的线程：
 *              wakeup()方法 ：通过调用Selector对象的wakeup（）方法让处在阻塞状态的select()方法立刻返回 该方法使得选择器上的第一个还没有返回的选择操作立即返回。如果当前没有进行中的选择操作，那么下一次对select()方法的一次调用将立即返回。
 *              close()方法 ：通过close（）方法关闭Selector， 该方法使得任何一个在选择操作中阻塞的线程都被唤醒（类似wakeup（）），同时使得注册到该Selector的所有Channel被注销，所有的键将被取消，但是Channel本身并不会关闭。
 *
 *
 *      如果使用Selector
 *          1. 需要把Channel都注册到Selector上。
 *          2. 应用程序线程调用Selector的select()方法。这个方法会进入阻塞，直到有一个channel的状态符合条件。当方法返回后，线程可以处理这些事件。
 *
 *
 * 关于NIO是同步 or 异步，阻塞 or 非阻塞的问题：
 *      本来NIO的Channel是可同步可异步的，只是常用的实现类FileChannel，SocketChannel读写数据都是同步阻塞的。
 *
 *      但是如果用选择器Selector来管理Channel的话，可以把Channel设置成非阻塞的，其实现方式其实就是把非阻塞的Channel注册到一个Selector上，
 *      每次Selector选择的时候回去轮询一回所有注册的Channel有没有准备好，没有准备好的话，因为Channel是非阻塞的，就会立刻返回，
 *      虽然轮询的过程是阻塞的，但是轮询完一回后Selector也会返回了，应用程序可以做其他事情，但是一般来说，会有一个循环再让Selector不断去轮询，直到有准备好的Channel，就开始处理读写数据了，
 *      读写Channel的过程也是同步的。
 *
 *      所以，总体来说，NIO可以是同步阻塞，也可以是同步非阻塞，也可以是异步非阻塞的，只是在目前的实现上，有同步阻塞的Channel，也有同步非阻塞的Channel，以及阻塞的Selector。
 *      所以，总体来说，虽然 NIO 在网络操作（网络IO）中，提供了非阻塞的方法，但是 NIO 的 文件IO 行为还是同步的。
 */

public class NIO {

    public static void main(String[] args) throws Exception {
        /**********************  缓冲区buffer  ***********************/
        /**
         * 核心缓冲区 —— 通过I/O发送基本数据类型
         *      ByteBuffer
         *      CharBuffer
         *      ShortBuffer
         *      IntBuffer
         *      FloatBuffer
         *      DoubleBuffer
         *      LongBuffer
         *
         *      利用Buffer读写数据，通常遵循四个步骤：
         *          1. 把数据写入buffer（这个写包括应用程序写入buffer，再通过channel写入数据源；也包括数据源通过channel向buffer写数据）
         *          2. 调用flip，把buffer转换成可读缓存。（应用程序可以读，channel也可以读）
         *          3. 从Buffer中读取数据（这个读包括应用程序读出来处理数据；也包括channel读出来写入数据源）
         *          4. 清空旧数据，写入新数据，否则由于读完后指针会在最后面，直接写新数据会内存溢出
         *                  调用buffer.clear()：清空全部数据；
         *                  或者调用buffer.compact()：清空已读取的，保留未读的，把未读的移到buffer前面，把position设为维度数据的后面，写入数据的位置
         *
         *
         *       buffer的三个属性：
         *          capacity容量：buffer分配的空间
         *          limit限制：有效数据长度
         *          position位置：当前读写指针
         *
         *
         *       常用方法：
         *          1. 分配缓冲区: ByteBuffer buffer = ByteBuffer.allocate(33);
         *          2. 读数据前，只读有效数据position=0，limit=有效数据长度: buffer.flip();
         *          3. 重新读数据，position=0，limit不变：buffer.rewind();
         *          4. 写数据前，清空缓冲区，position=0，limit与capacity同长：buffer.clear();
         *          5. 写数据前，只清空未读数据，移动已读数据到最前面，position=未读数据后面limit与capacity同长：buffer.compact();
         *          6. 获取数据：(char)buffer.get();
         *          7. 获取指定位置数据：(char)buffer.get(2);
         *          8. 获取指定长度的数据：byte[] dst = new byte[10];  buffer.get(dst, 0, 2);
         *          9. 写数据：buffer.put((byte)'a')
         *          10. 写入指定位置：buffer.put(2, (byte)'b');
         *          11. 写入一段数据：buffer.put("abcd".getBytes()); buffer2.put(buffer1);
         *          12. 手动移动指针：buffer.position(5);
         *          13. 手动移动有效读取数据区域：buffer.limit(15);
         *          14. 手动mark指针位置和恢复位置：buffer.mark();  buffer.reset();
         *
         *
         */
        // Buffer常用方法测试

        //分配缓冲区（Allocating a Buffer）
        ByteBuffer buffer = ByteBuffer.allocate(33);

        System.out.println("-------------Test reset：position回到标记的位置-------------");
        //clear()方法，position将被设回0，limit被设置成 capacity的值
        buffer.clear();
        // 设置这个缓冲区指针的位置
        buffer.position(5);
        //将此缓冲区的标记设置在其位置。没有buffer.mark();这句话会报错
        buffer.mark();
        buffer.position(10);
        System.out.println("before reset:      " + buffer);//java.nio.HeapByteBuffer[pos=10 lim=33 cap=33]
        //将此缓冲区的位置重置为先前标记的位置。（buffer.position(5)）
        buffer.reset();
        System.out.println("after reset:      " + buffer);//java.nio.HeapByteBuffer[pos=5 lim=33 cap=33]

        System.out.println("-------------Test rewind：把position设为0，mark设为-1，不改变limit的值-------------");
        buffer.clear();
        buffer.position(10);
        //改变此缓冲区的限制
        buffer.limit(15);
        System.out.println("before rewind:      " + buffer);//java.nio.HeapByteBuffer[pos=10 lim=15 cap=33]
        buffer.rewind();
        System.out.println("after rewind:      " + buffer);//java.nio.HeapByteBuffer[pos=0 lim=15 cap=33]

        System.out.println("-------------Test compact：清空已读的，保留未读的，把未读的移到前面，把position设为维度数据的后面，写入数据的位置-------------");
        buffer.clear();
        buffer.put("abcd".getBytes());
        System.out.println("before compact:      " + buffer);//java.nio.HeapByteBuffer[pos=4 lim=33 cap=33]
        System.out.println(new String(buffer.array()));//abcd                             
        //limit = position;position = 0;mark = -1; 翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，
        //翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
        buffer.flip();
        System.out.println("after flip:      " + buffer);//java.nio.HeapByteBuffer[pos=0 lim=4 cap=33]
        //get()方法：相对读，从position位置读取一个byte，并让position+1，为下次读写作准备
        System.out.println((char)buffer.get());//a
        System.out.println((char)buffer.get());//b
        System.out.println((char)buffer.get());//c
        System.out.println("after three gets:      " + buffer);//java.nio.HeapByteBuffer[pos=3 lim=4 cap=33]
        System.out.println("\t" + new String(buffer.array()));//abcd                             
        //把从position到limit中的内容移到0到limit-position的区域内，position和limit的取值也分别变成limit-position、capacity。
        // 如果先将positon设置到limit，再compact，那么相当于clear()
        buffer.compact();
        System.out.println("after compact:      " + buffer);//java.nio.HeapByteBuffer[pos=1 lim=33 cap=33]
        System.out.println(new String(buffer.array()));


        System.out.println("-------------Test get：按字节顺序获取buffer的数据-------------");
        ByteBuffer buffer1 = ByteBuffer.allocate(32);
        buffer1.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd').put((byte) 'e').put((byte) 'f');
        System.out.println("before flip:      " + buffer1);//java.nio.HeapByteBuffer[pos=6 lim=32 cap=32]
        // 转换为读取模式
        buffer1.flip();
        System.out.println("before get():      " + buffer1);
        System.out.println((char)buffer1.get());//a
        System.out.println("after get():      " + buffer1);
        // get(index)不影响position的值
        System.out.println((char)buffer1.get(2));//b
        System.out.println("after get(index):      " + buffer1);//java.nio.HeapByteBuffer[pos=1 lim=6 cap=32]
        byte[] dst = new byte[10];
        buffer1.get(dst, 0, 2);
        System.out.println("after get(dst, 0, 2):      " + buffer1);//java.nio.HeapByteBuffer[pos=3 lim=6 cap=32]
        System.out.println("\\t dst:" + new String(dst));//bc        
        System.out.println("buffer now is:      " + buffer1);//java.nio.HeapByteBuffer[pos=3 lim=6 cap=32]
        System.out.println("\t" + new String(buffer1.array()));//abcdef                          


        System.out.println("-------------Test put：按字节向buffer写数据-------------");
        ByteBuffer buffer2 = ByteBuffer.allocate(32);
        System.out.println("before put(bytes):      " + buffer2);// java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
        System.out.println("after put(bytes):      " + buffer2.put((byte)'a'));//java.nio.HeapByteBuffer[pos=1 lim=32 cap=32]
        // put(2,(byte) 'c')不改变position的位置
        buffer2.put(2, (byte)'b');
        System.out.println("after put(2,(byte) 'b'):      " + buffer2);//java.nio.HeapByteBuffer[pos=1 lim=32 cap=32]
        System.out.println("\t" + new String(buffer2.array()));//a b                             
        // 这里的buffer1是 abcdef[pos=3 lim=6 cap=32]
        buffer2.put(buffer1);
        System.out.println("after put(buffer):      " + buffer2);//java.nio.HeapByteBuffer[pos=4 lim=32 cap=32]
        System.out.println("\t" + new String(buffer2.array()));//adef                            



        /**********************  通道channel  ***********************/
        /**
         * 重要的Channel实现：
         *      FileChannel：文件IO，实现的是同步的Channel，通过Channel读写数据时，需要等到读写完成返回读写字节数
         *      DatagramChannel：UDP 网络IO，实现的是同步的Channel
         *      SocketChannel：TCP 网络IO，客户端，实现的是同步的Channel
         *      ServerSocketChannel：TCP 网络IO，服务器端，实现的是同步的Channel
         *
         *
         * Channel通道和流非常相似，区别是：
         *      通道可以读也可以写，流一般来说是单向的，要么输入，要么输出。
         *      通道可以异步读写（把数据写入buffer后就不管了，Channel自己慢慢写入数据源，或者自己从数据源读）。
         *           只是FileChannel、SocketChannel、ServerSocketChannel实现的都是同步的Channel
         *
         * 常用方法：
         *      1. 从buffer写数据到Channel：inChannel.write(buffer);
         *      2. 从Channel读数据到buffer：inChannel.read(buffer);
         *      3. 网络IOChannel，连接服务器：socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));
         *      4. 网络IOChannel，等待客户端连接请求：serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 3333));
         *                                       serverSocketChannel.accept();
         *
         */

        // ********************  FileChannel 写文件

        // 1. 创建一个RandomAccessFile（随机访问文件）对象
        RandomAccessFile raf = new RandomAccessFile("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/NIOFileChannel.txt","rw");
        // 2. 通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类。getChannel()方法创建了一个实现了FileChannel抽象类的匿名实例，并返回了这个实例的引用
        FileChannel inChannel = raf.getChannel();

        // 3. 创建一个写数据缓冲区对象
        ByteBuffer buf2 = ByteBuffer.allocate(48);

        // 4. 向文件中写数据，其实是写到buffer，再要求Channel写到文件里
        //写入buffer
        buf2.put("filechannel test".getBytes());
        //Buffer从写模式变成读模式，Channel才能从buffer里面读数据给文件
        buf2.flip();
        //写入文件
        inChannel.write(buf2);
        //关闭RandomAccessFile（随机访问文件）对象，关闭文件的时候，才会真正把写入channel的数据写入文件
        raf.close();

        // ********************  FileChannel 读文件

        // 1. 创建一个RandomAccessFile（随机访问文件）对象
        raf = new RandomAccessFile("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/IOSystem/NIOFileChannel.txt","rw");
        // 2. 通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类。getChannel()方法创建了一个实现了FileChannel抽象类的匿名实例，并返回了这个实例的引用
        inChannel = raf.getChannel();

        // 3. 创建一个读数据缓冲区对象
        ByteBuffer buf = ByteBuffer.allocate(48);

        // 4. 从文件中读取数据，其实要求Channel从文件里读数据，再读到buffer，返回通道从文件里读到的字节数
        int bytesRead = inChannel.read(buf);
        while(bytesRead != -1) {
            System.out.println("Read " + bytesRead);//16
            //Buffer从写模式变成读模式，应用程序才能从buffer里面读数据
            buf.flip();
            //如果还有未读内容
            while(buf.hasRemaining()) {
                System.out.print((char) buf.get());//filechannel test
            }
            //清空缓存区
            buf.clear();
            // 再次从文件里读数据，直到文件被读完了
            bytesRead = inChannel.read(buf);
        }
        //关闭文件
        raf.close();

        /**


        // ********************  SocketChannel和ServerSocketChannel的使用

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
            SocketChannel socketChannel1 = ssc.accept();

            //4.创建写数据的缓存区对象，向客户端写数据
            ByteBuffer writeBuffer1 = ByteBuffer.allocate(128);
            writeBuffer1.put("hello WebClient this is from WebServer".getBytes());
            writeBuffer1.flip();
            socketChannel1.write(writeBuffer1);

            //5. 创建读数据的缓存区对象，从客户端读数据
            ByteBuffer readBuffer1 = ByteBuffer.allocate(128);
            socketChannel1.read(readBuffer1);
            StringBuilder stringBuilder1 = new StringBuilder();
            readBuffer1.flip();
            while (readBuffer1.hasRemaining()) {
                stringBuilder1.append((char) readBuffer1.get());
            }
            System.out.println("从客户端接收到的数据：" + stringBuilder1);
            // 5. 关闭连接服务器和客户端的Channel，关闭通到监听器ServerSocketChannel
            socketChannel1.close();
            ssc.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }



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

         **/





        /**********************  选择区Selector  ***********************/
        /**
         *  FileChannel没有继承SelectableChannel，不能使用选择器，只有Socket channel可以使用选择器
         *
         *  SelectableChannel抽象类 有一个 configureBlocking（） 方法用于使通道处于阻塞模式或非阻塞模式。
         *  SelectableChannel抽象类的configureBlocking（） 方法是由 AbstractSelectableChannel抽象类实现的，SocketChannel、ServerSocketChannel、DatagramChannel都是直接继承了 AbstractSelectableChannel抽象类
         *
         *  Selector事件：
         *       Selector监听的是Channel感兴趣的事件，Channel在注册到Selector上时需要指明事件，事件类型包括：
         *       Connect —— SelectionKey.OP_CONNECT：某个Channel成功连接到另一个服务器称为“ 连接就绪 ”
         *       Accept —— SelectionKey.OP_ACCEPT：一个Server Socket Channel准备好接收新进入的连接称为“ 接收就绪”
         *       Read —— SelectionKey.OP_READ：一个有数据可读的通道可以说是“ 读就绪 ”
         *       Write —— SelectionKey.OP_WRITE：等待写数据的通道可以说是“ 写就绪 ”
         *
         *       如果一个Channel对多事件感兴趣，使用：int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE
         *
         *
         * SelectionKey：
         *
         *        一个SelectionKey表示选择器和通道之间已将建立的关系，包括信息有哪个通道在哪个选择器上注册了哪个类型的事件。
         *        key.attachment(); //返回SelectionKey的attachment，attachment可以在注册channel的时候指定。
         *        key.channel(); // 返回该SelectionKey对应的channel。
         *        key.selector(); // 返回该SelectionKey对应的Selector。
         *        key.interestOps(); //返回代表需要Selector监控的事件类型的bit mask
         *        key.readyOps(); // 返回一个bit mask，代表在相应channel上已经准备好的事件可以操作。
         *
         * Selector的select（）方法：
         *        select（）方法：会依次询问每个通道是否已经就绪，这个过程可能会造成调用线程进入阻塞，直到选择到已经准备就绪的通道们。
         *
         *        int select()：阻塞到至少有一个通道在你注册的事件上就绪了。int值表示有多少通道已经就绪,是自上次调用select()方法后有多少通道变成就绪状态。之前在select（）调用时进入就绪的通道不会在本次调用中被记入。
         *        int select(long timeout)：和select()一样，但最长阻塞时间为timeout毫秒。
         *        int selectNow()：非阻塞，只要有通道就绪就立刻返回。
         *
         *        Set selectedKeys=selector.selectedKeys(); //通过调用Selector的selectedKeys()方法来访问已准备就绪的键集合
         *
         *        有以下两种方式可以唤醒在select（）方法中阻塞的线程：
         *              wakeup()方法 ：通过调用Selector对象的wakeup（）方法让处在阻塞状态的select()方法立刻返回 该方法使得选择器上的第一个还没有返回的选择操作立即返回。如果当前没有进行中的选择操作，那么下一次对select()方法的一次调用将立即返回。
         *              close()方法 ：通过close（）方法关闭Selector， 该方法使得任何一个在选择操作中阻塞的线程都被唤醒（类似wakeup（）），同时使得注册到该Selector的所有Channel被注销，所有的键将被取消，但是Channel本身并不会关闭。
         *
         *
         * 如果使用Selector
         *         1. 需要把Channel都注册到Selector上。
         *         2. 应用程序线程调用Selector的select()方法。这个方法会进入阻塞，直到有一个channel的状态符合条件。当方法返回后，线程可以处理这些事件。
         *
         */

        // *****************  单线程管理多个网络链接

        // 服务器端：
//        try {
//            ServerSocketChannel ssc = ServerSocketChannel.open();
//            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 3000));
//            ssc.configureBlocking(false);
//
//            // 注册 channel，并且指定感兴趣的事件是 Accept
//            Selector selector = Selector.open();
//            ssc.register(selector, SelectionKey.OP_ACCEPT);
//
//            // 分别创建读写缓冲区，并分别读写数据
//            ByteBuffer readBuff = ByteBuffer.allocate(50);
//            ByteBuffer writeBuff = ByteBuffer.allocate(50);
//            writeBuff.put("hello client! this is from server".getBytes());
//            writeBuff.flip();
//
//
//            while (true) {
//                // 这里线程会轮询每个通道，有没有准备就绪，轮询期间线程是阻塞的
//                int nReady = selector.select();
//                Set<SelectionKey> keys = selector.selectedKeys();
//                Iterator<SelectionKey> it = keys.iterator();
//
//                // 上一波轮询有没有准备就绪的通道，如果没有，就不进入循环
//                while (it.hasNext()) {
//                    SelectionKey key = it.next();
//                    it.remove();
//                    if (key.isAcceptable()) {
//                        // 创建新的连接，并且把连接注册到selector上，而且声明这个channel只对读操作感兴趣。
//                        SocketChannel socketChannel = ssc.accept();
//                        socketChannel.configureBlocking(false);
//                        socketChannel.register(selector, SelectionKey.OP_READ);
//                    } else if (key.isReadable()) {
//                        SocketChannel socketChannel = (SocketChannel) key.channel();
//                        readBuff.clear();
//                        socketChannel.read(readBuff);
//                        readBuff.flip();
//                        System.out.println("服务器 received : " + new String(readBuff.array()));
//                        key.interestOps(SelectionKey.OP_READ);
//                    } else if(key.isWritable()) {
//                        SocketChannel socketChannel = (SocketChannel) key.channel();
//                        // writeBuff 前面已写入数据，已变为可读，这里就不断的写入数据
//                        writeBuff.rewind();
//                        socketChannel.write(writeBuff);
//                        key.interestOps(SelectionKey.OP_WRITE);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 客户端：
//        try {
//            SocketChannel socketChannel = SocketChannel.open();
//            socketChannel.connect(new InetSocketAddress("127.0.0.1", 3000));
//
//            ByteBuffer writeBuffer = ByteBuffer.allocate(50);
//            ByteBuffer readBuffer = ByteBuffer.allocate(50);
//
//            writeBuffer.put("hello server! this is from client".getBytes());
//            writeBuffer.flip();
//
//            while (true) {
//                // 客户端不断重复发相同消息，服务端会不断收到客户端发送过来的消息
//                writeBuffer.rewind();
//                socketChannel.write(writeBuffer);
//                readBuffer.clear();
//                socketChannel.read(readBuffer);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }

}
