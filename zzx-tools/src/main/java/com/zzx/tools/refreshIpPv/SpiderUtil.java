package com.zzx.tools.refreshIpPv;
 
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
 
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class SpiderUtil {
 
    static FileUtil fileUtil = new FileUtil();
 
    //爬取代理IP
    public static void spiderIP(String url, int totalPage) {
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
        Pattern ipPtn = Pattern.compile(ipReg);
        for (int i = 1; i <= totalPage; i++) {
            System.out.println("开始爬取第"+i+"/"+totalPage+"页...");
            //发送请求，获取文档
            Document doc = null;
            try {
                doc = getDocument(url + i , "www.kuaidaili.com");
            } catch (IOException e) {
                System.out.println("链接不可用，爬取失败："+url+i);
                return;
            }
            Matcher m = ipPtn.matcher(doc.text());
 
            while (m.find()) {
                String s = m.group();
                if (CheckIPUtil.checkProxy(s)) {
                    fileUtil.write("IP", s, true);
                }
            }
        }
    }
 
    //爬取博客url
    public static void spiderUrl(String username) {
        HashSet<String> urlSet = new HashSet<String>();
        String urlReg = "这里写匹配页面中爬取的正则";
        Pattern urlPtn = Pattern.compile(urlReg);
        Document doc = null;
        try {
            doc = getDocument("这里写要爬取的页面", "爬取网站的host");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Matcher m = urlPtn.matcher(doc.body().html());
        while (m.find()) {
            String s = m.group();
            urlSet.add(s);
        }
        Iterator<String> iterator = urlSet.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
            fileUtil.write("URL", s, true);
        }
    }
 
    //获取页面
    public static Document getDocument(String url, String host) throws IOException {
        Document doc = null;
 
            doc = Jsoup.connect(url)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Cache-Control", "max-age=0")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .header("Cookie", "Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1462812244; _gat=1; _ga=GA1.2.1061361785.1462812244")
                    .header("Host", host)
                    .header("Referer", "https://" + host + "/")
                    .timeout(30 * 1000)
                    .get();
 
        return doc;
    }
 
 
    //创建爬ip的runnable对象
    public static spiderIpExcutor excutorBulid(String url,int totalPage){
        return new spiderIpExcutor(url, totalPage);
    }
 
    //执行爬虫的runnable类
    static class spiderIpExcutor implements Runnable{
        String url = null;
        int totalPage = 0;
        public spiderIpExcutor(String url,int totalPage){
            this.url=url;
            this.totalPage=totalPage;
        }
 
        @Override
        public void run() {
            if (url.equals("")||url==null||totalPage<=0){
                System.out.println("参数错误");
            }else {
                spiderIP(url,totalPage);
            }
        }
    }
 
}