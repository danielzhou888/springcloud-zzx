package com.ddky.im.utils;

public class StickerUtils {



    //发送Sticker gif
    //http://api.telegram.org/bot5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8/sendSticker?chat_id=-1001592196115&sticker=https://www.gstatic.com/webp/gallery/1.webp
    //http://api.telegram.org/bot5037297030:AAGlICdAutRN18dHWb_jJBe7BHMpO3xbPz8/sendSticker?chat_id=-1001592196115&sticker=https://www.raptormeta.com/1.mp4
    public static String getStickerUrl(String botToken, String chatId, String stickerUrl) {
        return "https://api.telegram.org/bot" + botToken + "/sendSticker?chat_id="+chatId+"&sticker="+stickerUrl;
    }
}
