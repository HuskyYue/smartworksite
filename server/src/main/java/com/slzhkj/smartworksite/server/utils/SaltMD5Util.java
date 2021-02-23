package com.slzhkj.smartworksite.server.utils;

import cn.hutool.core.lang.Snowflake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.DigestUtils;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/**
 *  加盐加密工具类
 *
 * @author Yuezejian
 * @date 2020年 11月02日 11:26:17
 */
public class SaltMD5Util {
    public static final Snowflake snowflake= new Snowflake(12,10);
    /**
     *
     * @param encrypt 需要加盐加密的字符串
     * @param saltType 选择加盐的类型（1，用雪花算法做加盐加密 2，用UUID做加盐加密  3. 使用固定字符串进行加密）
     * @return
     */
    public Pair<String,String> add(String encrypt,EncryptCategory saltType) {
        Pair<String, String> pair;
        StringBuilder sb = new StringBuilder();
        String salt =  "";

        switch (saltType) {
            case SNOWFLAKE:
                salt = snowflake.nextIdStr();
                break;
            case UUID:
                salt = UUID.randomUUID().toString();
                break;
            case FIXED_STRING:
                salt = "#1859*2020";
                break;

        }
        sb.append(salt).append(encrypt);
        byte[] bytes = sb.toString().getBytes();
        encrypt = DigestUtils.md5DigestAsHex(bytes);
        pair = Pair.of(salt,encrypt);
        return pair;
    }

    /**
     * 默认使用uuid进行加盐加密
     * @param encrypt
     * @return
     */
    public Pair<String,String> add(String encrypt) {
        Pair<String, String> pair;
        StringBuilder sb = new StringBuilder();
        String salt = UUID.randomUUID().toString();
        sb.append(salt).append(encrypt);
        byte[] bytes = sb.toString().getBytes();
        encrypt = DigestUtils.md5DigestAsHex(bytes);
        pair = Pair.of(salt,encrypt);
        return pair;
    }

    /**
     *
     * @param salt 盐
     * @param validate 需要进行加盐校验的字符串
     * @return
     */
    public String validate(String salt,String validate) {
        StringBuilder sb = new StringBuilder();
        sb.append(salt).append(validate);
        byte[] bytes = sb.toString().getBytes();
        return DigestUtils.md5DigestAsHex(bytes);
    }

    private static volatile SaltMD5Util instance;
    private SaltMD5Util(){}
    public static SaltMD5Util getInstance() {
        if (instance == null) {
            synchronized (SaltMD5Util.class) {
                if (instance == null) {
                    instance = new SaltMD5Util();
                }
            }
        }
        return instance;
    }

    @Getter
    @AllArgsConstructor
    public enum EncryptCategory {
        SNOWFLAKE("雪花算法作盐加密", "001"),
        UUID("UUID作盐加密", "002"),
        FIXED_STRING("固定字符串作盐加密", "003")

        ;

        /**
         * 加密算法描述（分类）
         */
        private String decription;

        /**
         * 加密算法码
         */
        private String code;

        public static EncryptCategory of(String code) {
            Objects.requireNonNull(code);
            return Stream.of(values())
                    .filter( encryptCategory -> encryptCategory.code.equals(code))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
        }

    }
}
