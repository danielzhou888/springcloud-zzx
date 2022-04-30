//package com.ddky.im.tguser;
//
//import org.telegram.api.auth.TLSentCode;
//
//public class UserTgAccount {
//
//    // Get them from Telegram's console
//    public static final int API_ID = 0;
//    public static final String API_HASH = "<YOUR_HASH_HERE>";
//
//    // What you want to appear in the "all sessions" screen
//    public static final String APP_VERSION = "AppVersion";
//    public static final String MODEL = "Model";
//    public static final String SYSTEM_VERSION = "SysVer";
//    public static final String LANG_CODE = "en";
//
//    public static TelegramApp application = new TelegramApp(API_ID, API_HASH, MODEL, SYSTEM_VERSION, APP_VERSION, LANG_CODE);
//
//    // Phone number used for tests
//    public static final String PHONE_NUMBER = "+00000000000"; // International format
//
//    public static void main(String[] args) {
//
//        TLSentCode tlSentCode = new TLSentCode();
//        tlSentCode.setPhoneCodeHash();
//
//        tlSentCode.setType();
//    }
//}
