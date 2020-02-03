package com.zhouzhixiang.redis.redis.r1_3_1_vote_for_article;

import com.zhouzhixiang.redis.redis.ZhouzhixiangRedisApplication;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
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

    @Test
    public void testAddArticleToGroups() {
        String[] groupArrays = {"1", "2"};
        Set<String> articles = redisUtil.zRange("time:", 0, -1);
        List<String> articlesList = new ArrayList<>(articles);
        for (int i = 0; i < articlesList.size(); i++) {
            if (i % 2 != 0) {
                vfa.addArticleToGroups(articlesList.get(i), Arrays.asList(groupArrays));
            }
        }
    }

    @Test
    public void testRemoveArticleFromGroups() {
        String[] groupArrays = {"1"};
        vfa.removeArticleFromGroups("article:161623e5-1326-4351-b317-d94a975f9491", Arrays.asList(groupArrays));
    }

    @Test
    public void testOrderGroupArticles() {
        List<Map<Object, Object>> articles = vfa.orderGroupArticles("1", 1, "time:");
        System.out.println(articles);
    }
}
