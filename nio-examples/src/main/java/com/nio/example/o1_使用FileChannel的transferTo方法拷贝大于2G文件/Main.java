package com.nio.example.o1_使用FileChannel的transferTo方法拷贝大于2G文件;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.tokens.BlockEndToken;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 使用FileChannel的transferTo方法拷贝大于2G文件
 */
@Slf4j
public class Main {

    private static int blockCount = 4; // 分块数，也就是线程数

    public static void main(String[] args) throws IOException {
//        try (FileChannel from = new FileInputStream("/Users/daniel/dd/00.dump文件/02.ddky-readProduct-all/测试/2022-05-11-dump.dump.zip").getChannel();
//             FileChannel to = new FileOutputStream("/Users/daniel/dd/00.dump文件/02.ddky-readProduct-all/测试/test.zip").getChannel()
//        ) {
//            long size = from.size();
//            for (long left = size; left > 0;) {
//                log.info("position: {}, left: {}", size - left, left);
//                left -= from.transferTo(size - left, left, to);
//            }
//        } catch (IOException e) {
//            log.error("e: {}", e);
//        }

        // ------------------单线程FileChannel----------------
//        File source = new File("/Users/daniel/soft/metersphere/metersphere-offline-installer-v1.20.10-lts.tar.gz");
//        File target = new File("/Users/daniel/soft/metersphere/test.tar.gz");
//        long startTime = System.currentTimeMillis();
//        transfer1(source, target);
//        long endTime = System.currentTimeMillis();
//        log.info("cost time = {} ms", endTime - startTime);
        // ------------------单线程FileChannel----------------
        // ------------------多线程FileChannel----------------

        String sourcePath = "/Users/daniel/soft/metersphere/metersphere-offline-installer-v1.20.10-lts.tar.gz";
        File source = new File(sourcePath);
        String destPath = "/Users/daniel/soft/metersphere/test.tar.gz";
        File destFile = new File(destPath);
        if (destFile.exists()) {
            destFile.delete();
        }
        destFile.createNewFile();
        long length = source.length();// 文件总长度
        long oneNum = length / blockCount; // 一块的长度
        for (int i = 0; i < blockCount - 1; i++) {
            new CopyFileThread(sourcePath, destPath, oneNum * i, oneNum * (i + 1)).start();
        }
        // 文件长度不能整除的放到最后一段处理
        new CopyFileThread(sourcePath, destPath, oneNum * (blockCount - 1), length).start();


        // ------------------多线程FileChannel----------------


    }

    /**
     * 单线程下使用FileChannel复制大文件
     */
    public static void transfer1(File source, File target) {

        if (!source.exists() || !source.isFile()) {
            throw new IllegalArgumentException("source file not exist");
        }
        if (target.exists()) {
            target.delete();
        }
        try {
            target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileChannel in = new FileInputStream(source).getChannel();
            FileChannel out = new FileOutputStream(target).getChannel()
        ) {

            in.transferTo(0, in.size(), out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void transfer2() {

    }
}
