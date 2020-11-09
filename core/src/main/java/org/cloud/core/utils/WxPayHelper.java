package org.cloud.core.utils;

import java.security.MessageDigest;
import java.util.Set;
import java.util.SortedMap;

/**
 * FileName: SignUtils
 * Author: Admin
 * Date: 2020/11/9 13:02
 * Description: sign
 */
public class WxPayHelper {
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 微信支付签名算法sign
     * @param signKey  sign key
     * @param parameters 参数集合
     * @return 返回md5签名
     */
    public static String createWxPaySign(String signKey, SortedMap<Object, Object> parameters) {
        StringBuilder sb = new StringBuilder();       // 多线程访问的情况下需要用StringBuffer
        Set es = parameters.keySet();                 // 所有参与传参的key按照accsii排序（升序）
        for (Object set : es) {
            String k = set.toString();
            Object v = parameters.get(k);
            assert v != null;
            sb.append(k)
                    .append("=")
                    .append(v.toString())
                    .append("&");
        }
        sb.append("key=").append(signKey);
        return str2MD5(sb.toString(), "utf-8").toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param data   要加密的数据
     * @param encode 加密的编码
     * @return md5字符串
     */
    public static String str2MD5(String data, String encode) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (encode == null || "".equals(encode))
                resultString = byteArrayToHexString(md.digest(data
                        .getBytes()));
            else {
                resultString = byteArrayToHexString(md.digest(data
                        .getBytes(encode)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * byte数组转换16进制字符串
     *
     * @param b 要转换的byte数组
     * @return 16进制字符串
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * byte转换成16进制字符串
     *
     * @param b 要转换的byte
     * @return byte对应的16进制字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
