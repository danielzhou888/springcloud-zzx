package com.ddky.im.dreamdao;

import java.util.Random;

public class StickerConst {

    public static final String STICKER_PREFIX = "https://www.dreamdao.day/sticker/";
    public static final int START = 1;
    public static final int END = 121;
    public static final String STICKER_POSTFIX = ".mp4";

    public static String getRandomSticker() {
        int temp = new Random().nextInt(END) % (END - START + 1) + START;
        return STICKER_PREFIX + temp + STICKER_POSTFIX;
    }
}
