package com.baizhi.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class Md5Util {
    public static String getSalt() {
        char[] array = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sbu = new StringBuilder();
        Random random = new Random();

        int cycleLength = 6;
        for (int i = 1; i <= cycleLength; i++) {
            int index = random.nextInt(array.length);
            sbu.append(array[index]);
        }
        return sbu.toString();
        //生成的盐需要保存到数据表中,作为登录时的数据校验
    }

    public static String encryption(String src) {
        return DigestUtils.md5Hex(src);
    }

}
