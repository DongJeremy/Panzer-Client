package org.cloud.core.utils;

import java.util.regex.Pattern;

/**
 * FileName: TextUtil
 * Author: Admin
 * Date: 2020/11/14 10:18
 * Description: TextUtil
 */
public class TextUtil {

    private TextUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 手机号判断
     *
     * @param str 手机
     * @return 是否
     */
    public static boolean isMobile(String str) {
        return Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$").matcher(str).matches();
    }

    /**
     * 邮件判断
     *
     * @param str 手机
     * @return 是否
     */
    public static boolean isEmail(String str) {
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(str).matches();
    }

    /**
     * url判断
     *
     * @param str 手机
     * @return 是否
     */
    public static boolean isUrl(String str) {
        return Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]").matcher(str).matches();
    }

    /**
     * url判断
     *
     * @param str 手机
     * @return 是否
     */
    public static String deleteHtml(String str) {
        return Pattern.compile("\\\\s*|\\t|\\r|\\n", 2).matcher(Pattern.compile("<[^>]+>", 2).matcher(Pattern.compile("<style[^>]*?>[\\\\s\\\\S" +
                "]*?<\\\\/style>", 2).matcher(Pattern.compile("<script[^>]*?>[\\\\s\\\\S]*?<\\\\/script>", 2).matcher(str).replaceAll("")).replaceAll("")).replaceAll("")).replaceAll("").trim();
    }

}
