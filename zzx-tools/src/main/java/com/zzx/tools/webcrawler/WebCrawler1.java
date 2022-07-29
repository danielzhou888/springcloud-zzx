package com.zzx.tools.webcrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * @author zhouzhixiang
 * @Date 2021-08-07
 */
public class WebCrawler1 {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub


        try {
            URL url= new URL("https://www.csdn.net");
            InputStream input= url.openStream();
            FileOutputStream fout= new FileOutputStream(new File(".\\FirstPage.html"));//打开一个文件用来存储爬取的源码
            int a= 0;
            while(a> -1) {
                a= input.read();
                fout.write(a);  //写入文件
            }
            fout.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
