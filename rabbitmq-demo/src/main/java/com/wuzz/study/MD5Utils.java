package com.wuzz.study;

/**
 * @author wuzongzhao
 * @date 2020/11/18 12:35
 */
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

public class MD5Utils {

    /**
     * sign 签名 （参数名按ASCII码从小到大排序（字典序）+key+MD5+转大写签名）
     *
     * @param map
     * @return
     */
    public static String encodeSign(SortedMap<String, String> map, String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("签名key不能为空");
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        List<String> values = new ArrayList();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = String.valueOf(entry.getKey());
            String v = String.valueOf(entry.getValue());
            if (StringUtils.isNotEmpty(v) && entry.getValue() != null && !"sign".equals(k) && !"key".equals(k)) {
                values.add(k + "=" + v);
            }
        }
        values.add("key=" + key);
        String sign = StringUtils.join(values, "&");
        System.out.println(sign);
        System.out.println(encodeByMD5(sign));
        return encodeByMD5(sign).toUpperCase();
    }

    /**
     * 通过MD5加密
     *
     * @param algorithmStr
     * @return String
     */
    public static String encodeByMD5(String algorithmStr) {
        if (algorithmStr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(algorithmStr.getBytes("utf-8"));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String getFormattedText(byte[] digest) {
        StringBuffer buffer = new StringBuffer();
        //把每一个byte，做一个与运算，0xff
        for (byte b :
                digest) {
            int number = b & 0xff;//加盐
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                buffer.append("0");
            }
            buffer.append(str);
        }
        //标准的md5加密后的结果
        return buffer.toString();
    }

    public static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }

    public static void main(String[] args) {

        TreeMap<String, String> stringStringTreeMap = new TreeMap<>();
        stringStringTreeMap.put("park_id", "2");
        stringStringTreeMap.put("park_name", "测试");
        stringStringTreeMap.put("car_num", "皖A123456");
        stringStringTreeMap.put("place_num", "1");
        stringStringTreeMap.put("status", "0");
        stringStringTreeMap.put("in_time", "2020-11-18 15:16:20");

        String s = MD5Utils.encodeSign(stringStringTreeMap, "123456");
        System.out.println(s);
        System.out.println(s.equals("322C77067A392E9A8960CDEBCE147B5E"));
    }
}

