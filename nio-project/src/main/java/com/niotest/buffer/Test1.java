package com.niotest.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;

public class Test1 {

    /**
     * 3.6 从文件中读取
     * 在NIO系统中，任何时候执行一个读操作，都是从Channel中读取，而不是直接从Channel中读取数据，因为所有的数据都必须用Buffer来封装，所以应该是从Channel读取数据到Buffer。
     *
     * 因此，如果从文件读取数据的话，需要如下三步：
     *
     * 从FileInputStream获取Channel
     * 创建Buffer
     * 从Channel读取数据到Buffer
     */
    public void test1() {
        FileInputStream fis = null;
        FileChannel fileChannel = null;
        try {
            fis = new FileInputStream("~/test.txt");
            fileChannel = fis.getChannel();

            // 创建Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 3.7 写入数据到文件
     * 类似于从文件读取数据，如果想要写入数据到文件，需要如下三步:
     *
     * 从FileOutputStream获取Channel
     * 创建Buffer
     * 从Buffer写入数据到Channel
     */
    public void test2() {
        FileOutputStream fos = null;
        FileChannel fc = null;
        try {
            fos = new FileOutputStream("~/test2.txt");
            fc = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fc.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 3.8 读写结合
     * CopyFile是一个非常好的读写结合的例子，我们将通过CopyFile这个实力让大家体会NIO的操作过程。
     * CopyFile执行三个基本的操作：创建一个Buffer，然后从源文件读取数据到缓冲区，然后再将缓冲区写入目标文件。
     */
    public void test3() {
        try {
            String src = "~/test1.txt";
            String target = "~/test2.txt";
            copyFileUseNIO(src, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFileUseNIO(String src, String target) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int eof = fisChannel.read(byteBuffer);
            if (eof == -1) {
                break;
            }
            //将buffer的读模式切换为写模式：重设buffer的position=0，limit=position
            byteBuffer.flip();
            // 开始写
            fosChannel.write(byteBuffer);
            // 写完要将buffer的写模式切换回读模式：重置buffer，重设position=0，limit=capacity
            byteBuffer.clear();
//            byteBuffer.compact();// 只清理本次读写的这部分缓存
        }

        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();

    }

    /**
     * 4.3 创建选择器
     * 可以通过Selector.open()方法创建一个选择器。
     *
     * 4.4 使用选择器注册通道
     * 为了使用选择器与通道必须用选择器注册通道，使用SelectableChannel.register()方法完成
     */
    public void test4() {
        try {
            Selector selector = Selector.open();
            //4.4 使用选择器注册通道
            //为了使用选择器与通道必须用选择器注册通道，使用SelectableChannel.register()方法完成
//            SelectableChannel channel = new SelectableChannel();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
