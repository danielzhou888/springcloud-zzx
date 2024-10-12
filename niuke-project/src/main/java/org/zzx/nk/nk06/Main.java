package org.zzx.nk.nk06;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-06-11 16:37
 */
public class Main {

    public static void main(String[] args) {

        String mobile = "17338026010";
        String tar = desensitizeMobilePhone(mobile, true);
        System.out.println(tar);

    }

    public static String desensitizeMobilePhone(String mobilePhone, boolean b) {
        if (StringUtils.isBlank(mobilePhone)) {
            return "";
        }
        if (b) {
            return mobilePhone.replaceAll("\\d", "*");
        } else {
            return mobilePhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
    }
}
