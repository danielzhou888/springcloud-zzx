package com.zhouzhixiang.redis.redis.r1_3_3_article_group;

import lombok.Data;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 11:54
 **/
@Data
public class Article {
    private String link;
    private Integer votes;
    private Long time;
    private String title;
    private String poster;

    public Article(String link, Integer votes, Long time, String title, String poster) {
        this.link = link;
        this.votes = votes;
        this.time = time;
        this.title = title;
        this.poster = poster;
    }
}
