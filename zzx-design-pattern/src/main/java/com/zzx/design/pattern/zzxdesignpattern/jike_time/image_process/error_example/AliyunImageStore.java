package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example;

/**
 * 阿里云图片处理类
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class AliyunImageStore {

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

    public String uploadToAliyun(Image image, String bucketName, String accessToken) {
        // 上传图片到阿里云
        // 返回图片存储在阿里云到地址
        return "";
    }

    public Image downloadFromAliyun(String url, String accessToken) {
        // ... 从阿里云下载图片 ...
        return new Image();
    }

}
