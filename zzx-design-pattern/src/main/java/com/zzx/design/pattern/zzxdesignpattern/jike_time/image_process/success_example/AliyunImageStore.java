package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.success_example;

import com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example.Image;

/**
 * 阿里云图片处理类
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class AliyunImageStore implements ImageStore {

    public AliyunImageStore() {
    }

    public void createBucketIfNotExisting(String bucketName) {
        // ... 创建bucket代码逻辑
        // ... 失败抛出异常...
    }

    public String generateAccessToken() {
        // ....
        return "";
    }

    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExisting(bucketName);
        String accessToken = generateAccessToken();
        // 上传图片到阿里云
        // 返回图片在阿里云的url
        return null;
    }

    @Override
    public Image download(String url) {
        String accessToken = generateAccessToken();
        // 从阿里云下载图片
        return null;
    }
}
