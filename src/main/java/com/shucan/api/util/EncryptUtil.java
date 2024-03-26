package com.shucan.api.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 签名生成
 * @author: ningkun
 * @date: 2024-03-25 15:53
 */
public class EncryptUtil {

    /**
     * 和风天气签名生成算法-JAVA版本
     *
     * 签名参数
     * publicid: 公共ID，每个KEY都对应一个公共ID，可在控制台查看
     *
     * t: 时间戳，秒级
     *
     * sign: 数字签名
     *
     * 以及其他接口必要参数
     *
     * 创建签名
     * 将请求参数格式化为“key=value”格式，如“k1=v1”、“k2=v2”、“k3=v3”；
     * 去除请求参数中值为空的参数
     * 去除请求参数中参数为sign 的参数 ( 签名参数不参与签名算法 )
     * 将第3步剩余的键值对以字典序升序排列，并用 & 符号连接，如 a=1&b=2&m=3&w=4
     * 将第4步得到的字符串后拼接上API密钥, 假设密钥为 abc , 则 结果为: a=1&b=2&m=3&w=4abc
     * 将第5步得到的字符串进行MD5加密 ( 注 ：字符集为 UTF-8 )，得到签名结果
     * 将第6步得到的签名结果 作为参数添加至请求中，参数名为 sign, 即 sign=xxxxxxx
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @param secret 签名密钥（用户的认证key）
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(HashMap<String, String> params, String secret) throws Exception {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder baseString = new StringBuilder();
        for ( Map.Entry<String, String> param : entrys ) {
            //sign参数 和 空值参数 不加入算法
            if ( param.getValue()!=null && !"".equals(param.getKey().trim()) && !"sign".equals(param.getKey  ().trim()) &&!"key".equals(param.getKey().trim()) && !"".equals(param.getValue().trim())  && !"null".equals(param.getValue().trim())) {
                baseString.append(param.getKey().trim()).append("=").append(param.getValue().trim()).append  ("&");
            }
        }
        if ( baseString.length() > 0 ) {
            baseString.deleteCharAt(baseString.length() - 1).append(secret);
        }
        // 使用MD5对待签名串求签
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(baseString.toString().getBytes(StandardCharsets.UTF_8));
            return new String(encodeHex(bytes));
        } catch (GeneralSecurityException ex) {
            throw ex;
        }
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        char[] toDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for ( int var5 = 0; i < l; ++i ) {
            out[var5++] = toDigits[(240 & data[i]) >>> 4];
            out[var5++] = toDigits[15 & data[i]];
        }
        return out;
    }
}
