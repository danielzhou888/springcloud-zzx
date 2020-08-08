package com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.success_example;

import com.zzx.design.pattern.zzxdesignpattern.jike_time.image_process.error_example.Image;

/**
 * 图片处理接口
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public interface ImageStore {

    String upload(Image image, String bucketName);

    Image download(String url);
}

