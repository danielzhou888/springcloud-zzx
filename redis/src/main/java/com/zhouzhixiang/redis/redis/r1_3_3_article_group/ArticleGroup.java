package com.zhouzhixiang.redis.redis.r1_3_3_article_group;

import com.zhouzhixiang.redis.redis.utils.JavaBeanUtil;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 对文章进行分组
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 11:46
 **/
@Component
public class ArticleGroup {

    @Autowired
    private RedisUtil redisUtil;
    private static final Integer PAGE_NUM = 20;


    /**
     * 将文章添加到新群组，并移除旧群组
     * @param articleId
     * @param addGroups
     * @param removeGroups
     */
    @Test
    public void addAndRemoveGroups(String articleId, Set<String> addGroups, Set<String> removeGroups) {
        String article = "article:" + articleId;
        addGroups.forEach(group -> {
            redisUtil.sAdd("group:" + group, article);
        });
        removeGroups.forEach(group -> {
            redisUtil.sRemove("group:" + group, article);
        });
    }

    /**
     * 获取群组文章，分页，排序<br>
     * 采用zinterstore命令，这个命令比较花时间，为了减少redis的工作量，通常会对这个命令的计算结果缓存60s<br>
     * 如将group:programing集合和score有序集合合并到score:programing(临时有序时间，缓存时间60s)
     * @param group   分组种类名称，如programing，编程群组，可以组成"group:programing"
     * @param page    current page
     * @param order   排序维度，如按文章分值排序->"score:"，按照发布时间->"publish:"
     * @return
     */
    @Test
    public Set<Article> getGroupArticles(String group, Integer page, String order) {
        String key = order + group;
        if (!redisUtil.hasKey(key)) {
            redisUtil.zIntersectAndStore("group:"+group, order, key);
            redisUtil.expire(key, 60, TimeUnit.SECONDS);
        }
        return getArticles(key, page);
    }

    /**
     * 获取文章列表
     * @param key
     * @param page
     * @return
     */
    private Set<Article> getArticles(String key, Integer page) {
        Integer start = (page - 1) * PAGE_NUM;
        Integer end = start + PAGE_NUM - 1;
        Set<String> articles = redisUtil.zRange(key, start, end);
        Set<Article> articleSet = new HashSet<>();
        articles.forEach(article -> {
            Map<Object, Object> articleMap = redisUtil.hGetAll(article);
            Article articleEntity = JavaBeanUtil.mapToEntity(articleMap, Article.class);
            if (articleEntity != null) {
                articleSet.add(articleEntity);
            }
        });
        return articleSet;
    }

}
