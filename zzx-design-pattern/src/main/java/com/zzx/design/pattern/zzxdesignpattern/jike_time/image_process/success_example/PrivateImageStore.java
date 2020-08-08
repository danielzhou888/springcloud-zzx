package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.success_example;

import com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example.Image;

/**
 * 私有云
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class PrivateImageStore implements ImageStore {
    @Override
    public String upload(Image image, String bucketName) {
        return null;
    }

    @Override
    public Image download(String url) {
        return null;
    }

    private void createBucketIfNotExisting(String bucketName) {

    }

}
