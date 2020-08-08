package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.success_example;

import com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example.Image;

/**
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class ImageProcessingJob {

    private static final String BUCKET_NAME = "ai_images_bucket";

    public void process() {
        Image image = new Image();
        ImageStore imageStore = new PrivateImageStore();
        imageStore.upload(image, BUCKET_NAME);

        ImageStore imageStore1 = new AliyunImageStore();
        imageStore1.upload(image, BUCKET_NAME);
    }
}
