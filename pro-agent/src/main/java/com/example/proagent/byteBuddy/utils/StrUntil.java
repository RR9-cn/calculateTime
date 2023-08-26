package com.example.proagent.byteBuddy.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author CJJ
 * @date 2023/8/26 16:18
 */
public class StrUntil {

    public static void main(String[] args) {
        System.out.println(getLongestCommonSubstring(Arrays.asList("com.costumor.test.morcoservice.cache.config.redis.RedisConfig.redisTemplate:80ms"
                , "com.costumor.test.morcoEervice.redis.redisTemplate:80ms")));
    }

    public static String getLongestCommonSubstring(List<String> stringList){
        if (stringList == null || stringList.isEmpty()) {
            return "";
        }

        String commonSubstring = stringList.get(0);

        for (int i = 1; i < stringList.size(); i++) {
            String currentString = stringList.get(i);
            int j = 0;

            while (j < commonSubstring.length() && j < currentString.length() && commonSubstring.charAt(j) == currentString.charAt(j)) {
                j++;
            }

            commonSubstring = commonSubstring.substring(0, j);

            if (commonSubstring.isEmpty()) {
                break;
            }
        }
        commonSubstring = commonSubstring.substring(0,commonSubstring.lastIndexOf(".") + 1);
        return commonSubstring;

    }
}
