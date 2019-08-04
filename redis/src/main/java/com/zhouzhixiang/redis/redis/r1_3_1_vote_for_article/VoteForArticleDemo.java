package com.zhouzhixiang.redis.redis.r1_3_1_vote_for_article;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>文章投票系统</p>
 * 1. 对文章进行投票 <br>
 * 2. 发布文章
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-07 22:34
 **/
@Component
public class VoteForArticleDemo {

    private final Integer VOTE_SCORE = 432;
    private final Integer ONE_WEEK_IN_SECONDS = 7 * 86400;
    private final Integer PAGE_NUM = 10;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 对文章进行投票
     * @param userId
     * @param article
     */
    public void articleVote(String userId, String article) {
        long cutOff = System.currentTimeMillis() - ONE_WEEK_IN_SECONDS;

        Double aDouble = redisUtil.zScore("time:", article);
        if (aDouble < cutOff) {
            return;
        }
        String articleId = article.split(":")[1];
        if (redisUtil.sAdd("voted:"+articleId, "user:"+userId) > 0) {
            redisUtil.zIncrementScore("score:", article, VOTE_SCORE);
            redisUtil.hIncrBy(article, "votes", 1);
        }
    }

    /**
     * 发布文章
     * @param userId
     * @param title
     * @param link
     * @return
     */
    public String publishArticle(String userId, String title, String link) {
        String articleId = String.valueOf(UUID.randomUUID());
        if (redisUtil.sAdd("voted:"+articleId, "user:"+userId) > 0) {
            redisUtil.expire("voted:"+articleId, ONE_WEEK_IN_SECONDS, TimeUnit.SECONDS);
            Long now = System.currentTimeMillis();
            Map<String, String> articleMap = new HashMap<>();
            articleMap.put("title", title);
            articleMap.put("link", link);
            articleMap.put("poster", "user:"+userId);
            articleMap.put("time", String.valueOf(now));
            articleMap.put("votes", String.valueOf(1));
            redisUtil.hPutAll("article:"+articleId, articleMap);
            redisUtil.zAdd("score:", "article:"+articleId, now + VOTE_SCORE);
            redisUtil.zAdd("time:", "article:"+articleId, now);
        }
        return articleId;
    }


    /**
     * 获取分值高的文章集合（从大到小）
     * @param page
     * @return
     */
    public List<Map<Object, Object>> getTopScoreArticles(int page) {
        int start = (page - 1) * PAGE_NUM;
        int end = start + PAGE_NUM - 1;
        Set<String> articles = redisUtil.zReverseRange("score:", start, end);
        List<Map<Object, Object>> articleList = new ArrayList<>();
        articles.forEach(a -> {
            Map<Object, Object> articleMap = redisUtil.hGetAll(a);
            articleList.add(articleMap);
        });
        return articleList;
    }


    /**
     * 获取最新发布的文章集合
     * @param page
     * @return
     */
    public List<Map<Object, Object>> getNewPublishedArticles(int page) {
        int start = (page - 1) * PAGE_NUM;
        int end = start + PAGE_NUM - 1;
        Set<String> articles = redisUtil.zReverseRange("time:", start, end);
        List<Map<Object, Object>> articleList = new ArrayList<>();
        articles.forEach(a -> {
            Map<Object, Object> articleMap = redisUtil.hGetAll(a);
            articleList.add(articleMap);
        });
        return articleList;
    }

    /**
     * 获取文章集合
     * @param page
     * @param order  需要查询的键，如"score:" 或 "time:" 或 "score:groupId"
     * @return
     */
    public List<Map<Object, Object>> getArticles(int page, String order) {
        int start = (page - 1) * PAGE_NUM;
        int end = start + PAGE_NUM - 1;
        Set<String> articles = redisUtil.zReverseRange(order, start, end);
        List<Map<Object, Object>> articleList = new ArrayList<>();
        articles.forEach(a -> {
            Map<Object, Object> articleMap = redisUtil.hGetAll(a);
            articleList.add(articleMap);
        });
        return articleList;
    }

    /**
     * 添加文章到分组中，如可爱的动物组，政治组
     * @param article
     * @param groupIds
     */
    public void addArticleToGroups(String article, List<String> groupIds) {
        groupIds.forEach(g -> {
            String group = "group:" + g;
            redisUtil.sAdd(group, article);
        });
    }

    /**
     * 从分组中移除文章，如可爱的动物组，政治组
     * @param article
     * @param groupIds
     */
    public void removeArticleFromGroups(String article, List<String> groupIds) {
        groupIds.forEach(g -> {
            String group = "group:" + g;
            redisUtil.sRemove(group, article);
        });
    }

    /**
     * 对群组文章进行排序和分页 <br>
     * 1. 通过ZINTERSTORE命令获取群组文章集合和分数文章有序集合交集，对其进行排序 <br>
     * 2. 为了减少redis的工作量，对这个命令的计算结果缓存60s
     * @param groupId
     * @param page
     * @param order
     * @return
     */
    public List<Map<Object, Object>> orderGroupArticles(String groupId, int page, String order) {
        String cacheKey = order + groupId;
        if (!redisUtil.hasKey(cacheKey)) {
            Long aLong = redisUtil.zIntersectAndStore("group:" + groupId, order, cacheKey);
            Boolean expire = redisUtil.expire(cacheKey, 60, TimeUnit.SECONDS);
        }
        return getArticles(page, cacheKey);
    }

}
