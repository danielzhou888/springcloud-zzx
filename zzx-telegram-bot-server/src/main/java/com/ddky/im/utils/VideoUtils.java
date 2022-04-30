package com.ddky.im.utils;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class VideoUtils {

    private static final String Boundary = "20140501";

    public static void main(String[] args) {

        String url = "https://api.telegram.org/bot5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8/sendDocument?chat_id=-1001592196115&document=/Users/daniel/soft/00-bitcoin/00.agif/1.mp4";
//        String url = "http://api.telegram.org/bot5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8/sendMessage?chat_id=-1001529097059&text=Nice project";
//        uploadVideo("/Users/daniel/soft/00-bitcoin/00.agif/1.mp4", url);

        HttpRequest post = HttpUtil.createPost(url);
        post.contentType(ContentType.FORM_URLENCODED.toString());
        HttpResponse response = post.execute();

        System.out.printf("dsdsfasdfsa");
    }

    /**
     * 上传视频
     */
    public static void uploadVideo(String targetDes, String url) {
        try {
            File file = new File(targetDes);
            InputStream inputStream = new FileInputStream(file);
            MultipartFile video = new MockMultipartFile(file.getName(), inputStream);

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(10*1000);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + Boundary);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            //=============================模拟表单提交视频的核心===============top
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(Boundary).append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\"" + "video" + "\"; filename=\"" + video.getOriginalFilename() + "\"\r\n");
            strBuf.append("Content-Type:" + "video/mp4" + "\r\n\r\n");

            out.write(strBuf.toString().getBytes());

            byte[] b = new byte[1024];
            int l = 0;
            out.write(b); // 写入文件
            out.writeUTF("\r\n--"+Boundary+"--\r\n");
            out.flush();
            out.close();
            //=============================模拟表单提交视频的核心===============bottom
            BufferedReader result = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String s = null;
            StringBuilder r = new StringBuilder();
            while((s=result.readLine())!=null) {
                r.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
