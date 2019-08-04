package com.zzx.druid;

import java.io.*;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class HoldIOMain {
    public static class HoldIOTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    FileOutputStream fos = new FileOutputStream(new File("zhouzhixiang.log"));
                    for (int i = 0; i < 100000; i++) {
                        fos.write(i);
                    }
                    fos.close();
                    FileInputStream fis = new FileInputStream(new File("zhouzhixiang.log"));
                    while (fis.read() != -1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class LazyTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldIOTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }

}