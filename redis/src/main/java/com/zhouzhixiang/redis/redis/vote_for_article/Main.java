package com.zhouzhixiang.redis.redis.vote_for_article;

import com.zhouzhixiang.redis.redis.ZhouzhixiangRedisApplication;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-07 23:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZhouzhixiangRedisApplication.class)
@WebAppConfiguration
public class Main {

    @Autowired
    private VoteForArticleDemo vfa;

    @Autowired
    private RedisUtil redisUtil;

    public static Main main;

    public Main() {
    }

    @PostConstruct
    public void init() {
        main = this;
        main.vfa = this.vfa;
    }

    @Test
    public void testForPublishArticle() {
        for (int i = 0; i < 10; i++) {
            String articleId = vfa.publishArticle(String.valueOf(i), "Redis投票系统" + i, "www.baidu.com" + i);
            System.out.println(articleId);
        }
    }

    @Test
    public void testForArticleVote() {
        Set<String> articles = redisUtil.zRange("score:", 0, -1);
        List<String> articleList = new ArrayList<>(articles);
        for (int i = 100; i < 200; i++) {
            vfa.articleVote(String.valueOf(i), articleList.get( (i / articleList.size()) /2));
        }
    }

    @Test
    public void testDeleteKey() {
        redisUtil.delete("score");

    }

    @Test
    public void testGetTopScoreArticles() {
        List<Map<Object, Object>> topScoreArticles = vfa.getTopScoreArticles(1);
        System.out.println(topScoreArticles);
    }

    @Test
    public void testGetNewPublishedArticles() {
        List<Map<Object, Object>> newPublishedArticles = vfa.getNewPublishedArticles(1);
        System.out.println(newPublishedArticles);
    }
}
