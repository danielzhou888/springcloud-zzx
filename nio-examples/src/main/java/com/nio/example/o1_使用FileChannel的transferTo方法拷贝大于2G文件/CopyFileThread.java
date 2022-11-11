package com.nio.example.o1_使用FileChannel的transferTo方法拷贝大于2G文件;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

@Slf4j
public class CopyFileThread extends Thread {

    private String srcPath; // 原文件位置
    private String destPath; // 目标文件位置
    private long start, end; // 起始位置和结束位置

    public CopyFileThread(String srcPath, String destPath, long start, long end) {
        this.srcPath = srcPath;
        this.destPath = destPath;
        this.start = start;
        this.end = end;
    }

    @SneakyThrows
    @Override
    public void run() {
        RandomAccessFile in = null;
        RandomAccessFile out = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        long startTime = System.currentTimeMillis();
        try {
            // 创建只读的随机访问文件
            in = new RandomAccessFile(srcPath, "r");
            // 创建可读写的随机访问文件
            out = new RandomAccessFile(destPath, "rw");
            // 将输入跳转到指定位置
            in.seek(start);
            // 从指定位置开始写
            out.seek(start);
            // 文件输入通道
            inChannel = in.getChannel();
            // 文件输出通道
            outChannel = out.getChannel();
            // 锁住需要操作的区域，false表示锁住
            FileLock lock = outChannel.lock(start, end - start, false);
            inChannel.transferTo(start, end - start, outChannel);
            lock.release();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            long endTime = System.currentTimeMillis();
            log.info("ThreadId = {}, cost time = {} ms", Thread.currentThread().getId(), endTime - startTime);
        }
    }
}
