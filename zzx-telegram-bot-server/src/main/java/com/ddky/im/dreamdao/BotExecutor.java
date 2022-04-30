package com.ddky.im.dreamdao;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.ddky.im.utils.StickerUtils;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.ddky.im.dreamdao.LinkMessageConst.linkMessage;
import static com.ddky.im.dreamdao.LongMessageConst.longMessages;
import static com.ddky.im.dreamdao.NiceMessageConst.niceMessages;
import static com.ddky.im.dreamdao.WelcomeAdminMessageConst.WelcomeAdminMessages;
import static com.ddky.im.dreamdao.WelcomeCommonMessageConst.WelcomeCommonMessages;

/**
 * @author zhouzhixiang
 * @Date 2020-08-12
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class BotExecutor {

    public static final Logger logger = LoggerFactory.getLogger(BotExecutor.class);

    private static volatile Map<String,CircularFifoQueue<String>> msgCacheMap = new ConcurrentHashMap<>();

    private static final String PROJECTNAME = "DreamDAO";
    private static final String PROJECT_NAME_TAG = "PROJECTNAME";
    private static final String CHAT_ID_TAG = "CHATIDREPLACE";
    private static final String CHAT_ID = "-1001529841456";
    private static String PRE_URL = "https://api.telegram.org/bot";
    private static String POST_URL = "/sendMessage?chat_id=";
    private static String TEXT_URL = "&text=";

    private static int frequency = 5; // 次数
    private static int stickerFrequency = 2; // 次数
    private static int linkFrequency = 3; // 发送链接消息的频率
    private static int intervals = 50000000; // 发送消息的区间（ms=毫秒）
    private static int stickerIntervals = 50000000; // 发送消息的区间（ms=毫秒）
    private static int linkIntervals = 54450000; // 发送链接消息的时间区间 (ms=毫秒)
    private static boolean isAllowedDuplicate = true; // 每个人防止最近15条消息重复开关



    private static final String[] AdminBots = {"5035819371:AAHOi5YP1cU0apwKmTrZZf7MXfR2QkoxZ-Y",
            "5039622407:AAGCMBuUTtIHD7r46igoBX1m74O3KvFGWI0",
            "5006300020:AAGeeLDhAjsoeyyjGE4Zg3bsdIU2HE81Hk8"};
    private static final String[] CommonBots = {"5042908752:AAH401WpDd2brFGjTx3UeJJ_STRF_YscbHA",
            "5069780363:AAEDiQ06cN2rha-q3FftRYWTWfSbQK1Z6vs",
            "5049546668:AAEch-sSZ7fMJApFp0DJkbvktXG6UntSZ-U",
            "5062657997:AAHziXnEY3HnFRe_8aMOb2pwLWnWtgyYnbA",
            "5034013870:AAG_eqy3eHVRfvF4yMNyQqWrM1uEaE9QIKs",
            "5000838169:AAHzYw80YEJxj5BIQgwNFLv_S__5KdP9Vps",
            "5014866194:AAFrCOYu_HmxvcuR4K-74JxVhnYx93YtyHw",
            "5005408105:AAHoEXjnGkyz8eMd5562m0uxqG3AFkYrauY",
            "5090826442:AAFXckVPrbN6E1aHULn2YozXFG_Q7E6A5hU",
            "5012315019:AAGGJz226fI8FAVRIpIf08lPH9Bq8ufPxu8",
            "5078583019:AAGE8lOFHNNuBp8NCXs1LnH_JLdb32aR6E0",
            "5003416431:AAFcsdYDjnf8KM3KZGsslzFMqX8fa77QTmU",
            "5003444149:AAGQZ5Pe4EyYZYdR8oeGtGl2-trPKrMpqSQ",
            "5023757013:AAHGD8Cn46x3IOT3Vq1VLfLOAazSFkk5fm0",
            "5080123567:AAERXlERUhB4C2-aqRmuI68GhNlWt-dH0U8",
            "5042172402:AAFMNatuoqvyFTGGsqQszl9Ryj-4jt2yfnM"
    };

//    private static final String[] questionMessages = {"When launch?",
//
//    };










    public static List<String> adminMessageList = new ArrayList<>();
    public static List<String> commonMessageList = new ArrayList<>();
    public static List<String> linkMessageList = new ArrayList<>();
    public static List<String> adminBots = new ArrayList<>();
    public static List<String> commonBots = new ArrayList<>();
    public static List<String> linkBots = new ArrayList<>();
    public static List<String> allBotTokens = new ArrayList<>();


    static {

        List<String> welcomeAdminMessageList = Arrays.asList(WelcomeAdminMessages);
        List<String> linkMessages = Arrays.asList(linkMessage);
        List<String> niceMessageList = Arrays.asList(niceMessages);
        adminMessageList.addAll(welcomeAdminMessageList);
        adminMessageList.addAll(niceMessageList);
        //TODO
//        adminMessageList.addAll(linkMessages);

        List<String> longMessageList = Arrays.asList(longMessages);
        List<String> welcomeCommonMessageList = Arrays.asList(WelcomeCommonMessages);
        commonMessageList.addAll(longMessageList);
        commonMessageList.addAll(niceMessageList);
        commonMessageList.addAll(welcomeCommonMessageList);

        for (String token : AdminBots) {
            allBotTokens.add(token);
            String entire = (PRE_URL + token + POST_URL + CHAT_ID + TEXT_URL);
            adminBots.add(entire);
        }

        for (String token : CommonBots) {
            allBotTokens.add(token);
            String entire = (PRE_URL + token + POST_URL + CHAT_ID + TEXT_URL);
            commonBots.add(entire);
        }

        linkBots.addAll(adminBots);
//        linkBots.addAll(commonBots);
        linkMessageList.addAll(linkMessages);

        for (int i = 0; i < adminBots.size(); i++) {
            msgCacheMap.put(adminBots.get(i), new CircularFifoQueue(15));
        }

        for (int i = 0; i < commonBots.size(); i++) {
            msgCacheMap.put(commonBots.get(i), new CircularFifoQueue(15));
        }
    }

//    public static void main(String[] args) {
//
//
////        String url = "https://api.telegram.org/bot5006280104:AAGl_3T87XWrEOPdatY3qBRmpTGPFKBYBEY/sendMessage?chat_id=-1001529097059&text=";
////        String param = "Best wishes for all team members \uD83D\uDC4D\uD83D\uDE80";
////        url = url + URLEncoder.encode(param);
////        HttpRequest post = HttpUtil.createPost(url);
////        post.contentType(ContentType.TEXT_PLAIN_UTF_8);
////        post.execute();
//
//        sendAdminMessage2Group();
//        sendAdminLinkMessage();
//        sendCommonMessage2Group();
//
//    }

    public static void main(String[] args) {
        sendStickerMessage2Group();
    }

    private static void sendCommonMessage2Group() {
        int botCount = commonBots.size();
        for (int i = 0; i < botCount; i++) {
            String adminUrl = commonBots.get(i);
            for (int j = 0; j < frequency; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            int size = commonMessageList.size();
                            Random random = new Random();
                            int index = random.nextInt(size);
                            String msg = commonMessageList.get(index);
                            // 近15条消息同一个人防止重复
                            if (!isAllowedDuplicate) {
                                CircularFifoQueue<String> circularFifoQueue = msgCacheMap.get(adminUrl);
                                if (circularFifoQueue != null) {
                                    final String temp = msg;
                                    List<String> result = circularFifoQueue.stream().collect(Collectors.toList());
                                    if (CollectionUtil.isNotEmpty(result)) {
                                        long count = result.stream().filter(c -> temp.equals(c)).count();
                                        if (count > 0) {
                                            continue;
                                        }
                                    }
                                } else {
                                    circularFifoQueue = new CircularFifoQueue<>(15);
                                }
                                circularFifoQueue.offer(msg);
                                msgCacheMap.put(adminUrl, circularFifoQueue);
                            }
                            msg = msg.replaceAll(PROJECT_NAME_TAG, PROJECTNAME);
                            String reqUrl = adminUrl + URLEncoder.encode(msg);
                            reqUrl = reqUrl.replaceAll(CHAT_ID_TAG, CHAT_ID);
                            //                            doGetAtRandomTime(reqUrl, 0,200);
                            int start = 0;
                            int end = intervals;
                            int time = new Random().nextInt(end) % (end - start + 1) + start;
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            HttpRequest post = HttpUtil.createPost(reqUrl);
                            post.contentType(ContentType.FORM_URLENCODED.toString());
                            HttpResponse response = post.execute();

                            if (response.getStatus() != 200) {
                                logger.error(response.toString());
                            } else {
                                logger.info(response.toString());
                            }
                        }
                    }
                }).start();
            }
        }
    }

    public static void sendAdminMessage2Group() {
        int adminBotCount = adminBots.size();
        for (int i = 0; i < adminBotCount; i++) {
            String adminUrl = adminBots.get(i);
            for (int j = 0; j < frequency; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            int size = adminMessageList.size();
                            Random random = new Random();
                            int index = random.nextInt(size);
                            String msg = adminMessageList.get(index);
            //                            doGetAtRandomTime(reqUrl, 0,200);

                            if (!isAllowedDuplicate) {
                                // 近15条消息同一个人防止重复
                                CircularFifoQueue<String> circularFifoQueue = msgCacheMap.get(adminUrl);
                                if (circularFifoQueue != null) {
                                    final String temp = msg;

                                    List<String> result = circularFifoQueue.stream().collect(Collectors.toList());
                                    if (CollectionUtil.isNotEmpty(result)) {
                                        long count = result.stream().filter(c -> temp.equals(c)).count();
                                        if (count > 0) {
                                            continue;
                                        }
                                    }
                                } else {
                                    circularFifoQueue = new CircularFifoQueue<>(15);
                                }
                                circularFifoQueue.offer(msg);
                                msgCacheMap.put(adminUrl, circularFifoQueue);
                            }

                            msg = msg.replaceAll(PROJECT_NAME_TAG, PROJECTNAME);
                            String reqUrl = adminUrl + URLEncoder.encode(msg);
                            reqUrl = reqUrl.replaceAll(CHAT_ID_TAG, CHAT_ID);
                            int start = 0;
                            int end = intervals;
                            int time = new Random().nextInt(end) % (end - start + 1) + start;
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            HttpRequest post = HttpUtil.createPost(reqUrl);
                            post.contentType(ContentType.FORM_URLENCODED.toString());
                            HttpResponse response = post.execute();

                            if (response.getStatus() != 200) {
                                logger.error(response.toString());
                            } else {
                                logger.info(response.toString());
                            }
                        }
                    }
                }).start();
            }
        }
    }

    public static void sendMsg2Group() {
        sendAdminMessage2Group();
        sendAdminLinkMessage();
        sendStickerMessage2Group();
        sendCommonMessage2Group();
    }

    private static void sendAdminLinkMessage() {

        int botCount = linkBots.size();
        for (int i = 0; i < botCount; i++) {
            String adminUrl = linkBots.get(i);
            for (int j = 0; j < linkFrequency; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            int size = linkMessageList.size();
                            Random random = new Random();
                            int index = random.nextInt(size);
                            String msg = linkMessageList.get(index);
                            msg = msg.replaceAll(PROJECT_NAME_TAG, PROJECTNAME);
                            String reqUrl = adminUrl + URLEncoder.encode(msg);
                            reqUrl = reqUrl.replaceAll(CHAT_ID_TAG, CHAT_ID);
                            //                            doGetAtRandomTime(reqUrl, 0,200);
                            int start = 0;
                            int end = linkIntervals;
                            int time = new Random().nextInt(end) % (end - start + 1) + start;
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            HttpRequest post = HttpUtil.createPost(reqUrl);
                            post.contentType(ContentType.FORM_URLENCODED.toString());
                            HttpResponse response = post.execute();

                            if (response.getStatus() != 200) {
                                logger.error(response.toString());
                            } else {
                                logger.info(response.toString());
                            }
                        }
                    }
                }).start();
            }
        }
    }

//    public static String randomAdminMessage() {
//        int size = adminMessageList.size();
//        Random random = new Random();
//        int index = random.nextInt(size);
//        return adminMessageList.get(index);
//    }

//    public static String randomCommonMessage() {
//        int size = commonMessageList.size();
//        Random random = new Random();
//        int index = random.nextInt(size);
//        return commonMessageList.get(index);
//    }


    private static void sendStickerMessage2Group() {
        int botCount = allBotTokens.size();
        for (int i = 0; i < botCount; i++) {
            String botToken = allBotTokens.get(i);
            for (int j = 0; j < stickerFrequency; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String randomStickerUrl = StickerConst.getRandomSticker();
                            int start = 0;
                            int end = stickerIntervals;
                            int time = new Random().nextInt(end) % (end - start + 1) + start;
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            String url = StickerUtils.getStickerUrl(botToken, CHAT_ID, randomStickerUrl);
                            HttpRequest post = HttpUtil.createGet(url);
                            post.contentType(ContentType.FORM_URLENCODED.toString());
                            HttpResponse response = post.execute();

                            if (response.getStatus() != 200) {
                                logger.error(response.toString());
                            } else {
                                logger.info(response.toString());
                            }
                        }
                    }
                }).start();
            }
        }
    }

    private void doGetAtRandomTime(String url, int start, int end) {
        int time = new Random().nextInt(end) % (end - start + 1) + start;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = HttpUtil.get(url);
        logger.info(result);
    }
}
