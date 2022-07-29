package com.zzx.tools.refreshIpPv;
 
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
 
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
 
public class ClickUtil {
 
    //访问url
    public static void click(String url, String proxy) throws IOException {
        String proxyIP = proxy.split(" ")[0];
        int proxyPort = Integer.parseInt(proxy.split(" ")[1]);
        
            Document doc = Jsoup.connect(url)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Cache-Control", "max-age=0")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .header("Cookie", "Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1462812244; _gat=1; _ga=GA1.2.1061361785.1462812244")
                    .header("Host", "写目标网站的host")
                    .header("Referer", "写上一个页面的地址，指明是从哪个页面跳转到这里的")
                    .timeout(10 * 1000)
                    .proxy(proxyIP, proxyPort)
                    .ignoreContentType(true)
                    .get();
 
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    //使用一个IP访问所有url
    //如果失败三次，则停止，下一个IP
    public static void clickAll() {
        FileUtil fileUtil = new FileUtil();
        Iterator<String> ips = fileUtil.readFile("ip.txt").iterator();
        while (ips.hasNext()) {
            String ip = ips.next();
            int exceptionFlag = 0;
            Iterator<String> urls = fileUtil.readFile("url.txt").iterator();
            while (urls.hasNext()) {
                String url = urls.next();
                System.out.println("尝试访问："+url+"\n 使用代理:"+ip);
                try {
                    click(url, ip);
                } catch (IOException e) {
                    exceptionFlag++;
                }
                if(exceptionFlag>=3){
                    break;
                }
            }
        }
    }
 
    //获取excutor的build方法
    public static ClickExcutor excutorBuild(int time){
        return new ClickExcutor(time);
    }
 
 
    //点击行为的runable类
    static class ClickExcutor implements Runnable{
        int time = 1;
        public ClickExcutor(int time){
            if(time>1) {
                this.time = time;
            }else {
                System.out.println("输入次数不正确，默认执行一次");
            }
        }
        @Override
        public void run() {
            for (int i = 0; i < time; i++) {
                clickAll();
            }
        }
    }
}