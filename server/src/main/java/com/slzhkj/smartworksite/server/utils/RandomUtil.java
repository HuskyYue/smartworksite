package com.slzhkj.smartworksite.server.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 随机数工具
 *
 * @author Yuezejian
 * @date 2020年 08月23日 10:11:17
 */
public class RandomUtil {

    //短信验证码
    public static String randomMsgCode(final Integer num){
        /*ThreadLocalRandom random=ThreadLocalRandom.current();
        int num=random.nextInt(1000,9999);
        return String.valueOf(num);*/

        return RandomStringUtils.randomNumeric(num);
    }

}












