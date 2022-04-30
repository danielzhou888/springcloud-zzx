package com.ddky.im;

import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author zhouzhixiang
 * @Date 2020-08-12
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class RealAccountTest {

    public static final Logger logger = LoggerFactory.getLogger(RealAccountTest.class);

    private static final String PROJECTNAME = "RaptorMeta";
    private static final String PROJECT_NAME_TAG = "{PROJECTNAME}";
    private static final String[] AdminBots = {"https://api.telegram.org/bot5006280104:AAGl_3T87XWrEOPdatY3qBRmpTGPFKBYBEY/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5078619549:AAGflInjlqjIZFn6Ws1DlaJY5IYV6p37HNc/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5043401593:AAFpjv_md263RrQ8LrW6pMlTVtKmIEWNQSE/sendMessage?chat_id=-1001529097059&text="};
    private static final String[] CommonBots = {"https://api.telegram.org/bot5034413348:AAEAKLiEn-aklP9Wlhhou_f3Ro5LHW-weCw/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5042908752:AAH401WpDd2brFGjTx3UeJJ_STRF_YscbHA/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5006300020:AAGeeLDhAjsoeyyjGE4Zg3bsdIU2HE81Hk8/sendMessage?chat_id=-1001529097059&text="
            ,"https://api.telegram.org/bot5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5006175544:AAG62k_Fc1pjWfu5CE8e3QEE7Q18HUFseGY/sendMessage?chat_id=-1001529097059&text="
            ,"https://api.telegram.org/bot5016489006:AAG4abPqsUwHxbnHhLpUvgKxcSXP2orcl2c/sendMessage?chat_id=-1001529097059&text=","https://api.telegram.org/bot5057833185:AAE-bDGWYeXlZukQmRrY4uUHB0xv3AXTuhk/sendMessage?chat_id=-1001529097059&text="
            ,"https://api.telegram.org/bot5064942496:AAH9OHJNlCH7o7WM4BBgGn_l9NgmIGZIDpE/sendMessage?chat_id=-1001529097059&text="};

    private static final String[] WelcomeMessages = {"Welcome to {PROJECTNAME} community", "Welcome to home", "Welcome bro", "Welcome you all",
            "Kindly read pinned message first will help you know more about RaptorMeta",
            "Yezzzzz",
            "Like he said guys for any questions pls ask we are here for you",
            "Stay tuned guys big big this are coming up and it gonna be huge",
            "Pls go through pinned messages and visit website to know more about and don’t think twice to ask any questions",
            "Yeah let’s MOON",
            "All new members are welcome please go through pinned messages and visit website to know more about group:)\n" +
                    "\n" +
                    "\n" +
                    "And whitelist is live",
            "Welcome to RaptorMeta\n" +
                    "Keep the investors coming this project project is awesome truly awesome",
            "New members are welcome to the family, kindly check pinned message,if you have any question don't hesitate to let us know",
            "Hello.\n" +
                    "Tell more investors like me about RaptorMeta",
            "Make sure you invite all investors you to RaptorMeta",
            "Tell more investors that here is only were we all can invest freely without fear",
            "Kindly ask us questions about this great project.\n" +
                    "You can also check our pinned massages and visit our website thanks.",
            "Hello to you all",
            "Kindly stay tuned for official announcement",
            "Yeah feel free to invest here RaptorMeta has brought the good news to for we all",
            ""

    };

    private static final String[] niceMessages = {"Nice", "Nice work", "Nice team", "Nice project", "Nice community", "TO-THE-MOON", "This project is gonna moon hard guys trust us",
            "Let's pump this project guy's",
            "Okay let show the world what' we have here",
            "This is a great opportunity for we investors",
            "We have come to fly\n" +
                    "Conquer obstacle\n" +
                    "Let fucking go to the moon",
            "Nice to have you onboard sir! Fasten your seat belt",
            "Let fucking goooooooooooooo",
            "Sure is the real one mate invest without fear",
            "RaptorMeta is always the best...",
            "Investors keep on coming we're all here for you all",
            "Let's keep going for \n" +
                    "The RaptorMeta is boiling",
            "Hahaha sure dear this project is our we're all here to invest I'm coming along with my friend Edwin and modwatt",


    };

    private static final String[] longMessages = {
            "It's a good project. I really appreciate it, I will support you till the end, hope your project will go to the moon in the future and believe it. I am Glad to be participant.",
            "I Think, this is very strong project",
            "Thanks for shared this wonderful opportunity🚀",
            "Best wishes for all team members🙏🙏🙏🙏🙏",
            "Wish all the best for developer and all team project, I hope you're all still healthy and I believe your project will success.",
            "Keep working hard and don't ever surrender, To the moon!!",
            "Big opportunity to meet you at the best of the project is really fantastic project nice airdrop very good project.",
            "Amazing project and management work keep going great work ❤️",
            "RaptorMeta is a unique project and it's going to be at the top notch soon",
            "Thanks for the opportunity to participate in this airdrop, friends I think this will be a great project !!!",
            "Great project and best DEVs",
            "Hi sir great project and TO THE MOON",
            "Nice project and Congrats for the airdrop Hopefully to the moon and getting better",
            "Hi Admin, I want to say thank you for your hard work. I hope this project will be success and the development team will go the moon",
            "Your project is amazing. You have a great Team and strong Community, I am very impressed",
            "Best of luck for this project..",
            "a project that looks very good, may this project always be a success and be a guide for investing in the future. cheers to the team on this excellent project, and will fly to the moon🚀",
            "This project is looks so innovative and impactful, happy to take participate in such huge project. You guys are very hard working and I am pretty sure you will reach to the Moon very soon.",
            "I really like this project. The project has developed strongly and is on track. I always support the project for the continuation of the future",
            "This project looks very interesting. I am interested, and I will support this project until it is successful according to the plan that has been set",
            ""
    };


    private static final String[] linkMessage = {
            "https://t.me/RaptormetaAnnouncement/2612",
            "https://t.me/RaptormetaAnnouncement/2611",
            "https://t.me/RaptormetaAnnouncement/2610",
            "https://t.me/RaptormetaAnnouncement/2608",
            "https://twitter.com/RaptorMeta/status/1473226659704995841?s=20",
            "https://t.me/RaptormetaAnnouncement/2605",
            "https://t.me/RaptormetaAnnouncement/2604",
            "https://t.me/RaptormetaAnnouncement/2601",
            "https://twitter.com/RaptorMeta/status/1472460806575235072?s=20",
            "https://t.me/RaptormetaAnnouncement/2600",
            "https://t.me/RaptormetaAnnouncement/2601",
            "https://twitter.com/RaptorMeta/status/1472270158463127553?s=20",
            "https://t.me/RaptormetaAnnouncement/2587",
            "https://twitter.com/RaptorMeta/status/1471038081973166097?s=20",
            "https://t.me/RaptormetaAnnouncement/2612",
            "https://t.me/RaptormetaAnnouncement/2612",
    };

    public static final List<String> adminMessageList = new ArrayList<>();

    static {
        List<String> welcomeMessages = Arrays.asList(WelcomeMessages);
        List<String> linkMessages = Arrays.asList(linkMessage);
        adminMessageList.addAll(welcomeMessages);
        adminMessageList.addAll(linkMessages);
    }

    public static void main(String[] args) {



        String url = "https://api.telegram.org/bot8725669:71bae1d2e2de247602eb7b2605faa992/sendMessage?chat_id=-1001529097059&text=HI,ALL hhhhhhhhahahahahhahahaha";
        String result = HttpUtil.get(url);
        System.out.println(result);
    }

    private static String getRandomAdminMessage() {
        int size = adminMessageList.size();
        Random random = new Random(size);
        int index = random.nextInt(size);
        return adminMessageList.get(index);
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
