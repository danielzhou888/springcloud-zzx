package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example;

/**
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class ImageProcessingJob {

    private static final String BUCKET_NAME = "ai_images_bucket";

    public void process() {
        Image image = new Image();
        AliyunImageStore imageStore = new AliyunImageStore();
        imageStore.createBucketIfNotExisting(BUCKET_NAME);
        String accessToken = imageStore.generateAccessToken();
        imageStore.uploadToAliyun(image, BUCKET_NAME, accessToken);
    }
}
