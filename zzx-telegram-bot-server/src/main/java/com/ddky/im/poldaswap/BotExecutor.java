package com.ddky.im.poldaswap;

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

import static com.ddky.im.poldaswap.LinkMessageConst.linkMessage;
import static com.ddky.im.poldaswap.LongMessageConst.longMessages;
import static com.ddky.im.poldaswap.NiceMessageConst.niceMessages;
import static com.ddky.im.poldaswap.WelcomeAdminMessageConst.WelcomeAdminMessages;
import static com.ddky.im.poldaswap.WelcomeCommonMessageConst.WelcomeCommonMessages;

/**
 * @author zhouzhixiang
 * @Date 2020-08-12
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class BotExecutor {

    public static final Logger logger = LoggerFactory.getLogger(BotExecutor.class);

    private static volatile Map<String,CircularFifoQueue<String>> msgCacheMap = new ConcurrentHashMap<>();

    private static final String PROJECTNAME = "PoldaSwap";
    private static final String PROJECT_NAME_TAG = "PROJECTNAME";
    private static final String CHAT_ID_TAG = "CHATIDREPLACE";
    private static final String CHAT_ID = "-1001592196115"; // PoldaSwap
    private static String PRE_URL = "https://api.telegram.org/bot";
    private static String POST_URL = "/sendMessage?chat_id=";
    private static String TEXT_URL = "&text=";

    private static int frequency = 2; // 次数
    private static int stickerFrequency = 1; // 次数
    private static int linkFrequency = 1; // 发送链接消息的频率
//    private static int intervals = 50000000; // 发送消息的区间（ms=毫秒）
//    private static int stickerIntervals = 50000000; // 发送消息的区间（ms=毫秒）
//    private static int linkIntervals = 54450000; // 发送链接消息的时间区间 (ms=毫秒)
    private static int intervals = 8000000; // 发送消息的区间（ms=毫秒）
    private static int stickerIntervals = 8000000; // 发送消息的区间（ms=毫秒）
    private static int linkIntervals = 15445000; // 发送链接消息的时间区间 (ms=毫秒)
    private static boolean isAllowedDuplicate = true; // 每个人防止最近15条消息重复开关



    private static final String[] AdminBots = {"5006175544:AAG62k_Fc1pjWfu5CE8e3QEE7Q18HUFseGY"};
    private static final String[] CommonBots = {"5079985841:AAErYGxJI8IzNKZjzCMMIuGXdNJxSouFBkE",
            "5008495686:AAFilhlLy_cG-zCU8ABOE_l81iHJCPQ-Wq0",
            "5034413348:AAEAKLiEn-aklP9Wlhhou_f3Ro5LHW-weCw",
            "5055727482:AAEEWVwDfz_216e-2UcQ0AebISb0_8CPDIU",
            "5033143799:AAFSuI94N5law5XTSx-wwjFKbmT3ITB52yM",
            "5006300020:AAGeeLDhAjsoeyyjGE4Zg3bsdIU2HE81Hk8",
            "5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8",
            "5090826442:AAFXckVPrbN6E1aHULn2YozXFG_Q7E6A5hU"
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
        linkBots.addAll(commonBots);
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
//        sendAdminLinkMessage();
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
        for (int i = 0; i < botCount - 2; i++) {
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