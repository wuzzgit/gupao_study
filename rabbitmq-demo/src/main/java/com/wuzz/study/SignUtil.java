package com.wuzz.study;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wuzongzhao
 * @date 2020/11/18 12:17
 */
public class SignUtil {

    private static void getDigest(TreeMap<String, String> map, String key, String charset){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            sb = sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.append("key").append("=").append(key);
        System.out.println("拼接后的字符："+sb.toString());
        String sign = MD5(sb.toString());
        System.out.println("加密后的签名："+sign);
    }
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * MD5加密工具类
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte bt : array) {
                sb.append(Integer.toHexString((bt & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        //treeMap默认是key升序排序 ,如果需要降序,可以使用Comparator中的compare方法
        TreeMap<String,String> map = new TreeMap<String, String>();
        map.put("name", "zychen");
        map.put("password", "123456");
        map.put("project", "base");
        map.put("tenantId", "192319387131");
        getDigest(map, "helloWorld","utf-8");
    }
}

