package com.slzhkj.smartworksite.server.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.Charset;

/**
 * <h1>基类控制器（包括日志记录和布隆过滤器）</h1>
 * @author Yuezejian
 * @date 2020年 08月22日 16:04:01
 */
public class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <h2>通用的基础过滤器，如有使用，请在此注明</h2>
     *
     * 1.BloomFilter 内数据需要预热（固定时间内有效token的过滤，正在使用此过滤器）
     * The current three parties request the current system and need to obtain the token,
     * which is using the filter {@Modified by yuezejian }
     */
    protected static final BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000,0.01);

}