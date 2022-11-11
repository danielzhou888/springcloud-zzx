package com.zzx.algorithm.zzxalgorithm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ZzxAlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzxAlgorithmApplication.class, args);
    }


    public void test1() {
        FileChannel channel = null;
        try {
            //从FileChannel读取数据
            RandomAccessFile randomAccessFile = new RandomAccessFile("/export/servers/test.txt", "wr");
            channel = randomAccessFile.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(84);
            int read = channel.read(allocate);

            // 2.5.4 将数据写入FileChannel
            String data = "233232328sdkjhkjshjdskjdskjdslkdjsldsjdklsjdslkdsj";
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(84);
            byteBuffer2.clear();
            byteBuffer2.put(data.getBytes(StandardCharsets.UTF_8));
            while (byteBuffer2.hasRemaining()) {
                channel.write(byteBuffer2);
            }

            // 移动位置
            long position = channel.position();
            // 设置新位置
            channel.position(position + 127);

            // 2.5.7 FileChannel大小
            //对象的size()方法FileChannel返回通道所连接的文件的文件大小。
            long size = channel.size();

            //2.5.8 FileChannel截断
            //可以通过调用FileChannel.truncate()方法截断文件。截断文件时，会以给定长度将其剪切掉。
            channel.truncate(1024);

            //2.5.9 FileChannel Force
            //FileChannel.force()方法将所有未写入的数据从通道刷新到磁盘。出于性能原因，操作系统可能会将数据缓存在内存中，因此在调用该force()方法之前，
            // 无法保证写入通道的数据实际写入磁盘。force()方法采用布尔值作为参数，告知是否应该刷新文件元数据（权限等）。以下是刷新数据和元数据的案例
            channel.force(true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    // 关闭channel通道
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void test2() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        CharBuffer charBuffer = CharBuffer.allocate(1024);
    }
}
