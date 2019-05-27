package com.zzx.eu.bitwalker.useragentutils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-27 01:20
 **/
public class UserAgentUtilsDemo {

    @Test
    public void test1(HttpServletRequest request) {
        // 获取浏览器信息
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        // 获取浏览器版本号
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        String info = browser.getName() + "/" + version.getVersion();
        System.out.println(info);

    }
}
