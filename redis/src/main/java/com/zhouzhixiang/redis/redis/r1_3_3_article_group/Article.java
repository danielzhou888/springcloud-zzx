package com.zhouzhixiang.redis.redis.r1_3_3_article_group;

import lombok.Data;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
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
