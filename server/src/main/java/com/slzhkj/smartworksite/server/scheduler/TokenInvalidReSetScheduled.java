package com.slzhkj.smartworksite.server.scheduler;

import com.google.common.collect.Lists;
import com.slzhkj.smartworksite.model.dto.RequestIDDto;
import com.slzhkj.smartworksite.model.mapper.RequestMapper;
import com.slzhkj.smartworksite.server.enums.Constant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * token失效数据删除定时器
 *
 * @author Yuezejian
 * @date 2020年 11月11日 11:59:02
 */
@Slf4j
@Component
public class TokenInvalidReSetScheduled {

    public static final Logger logger = LoggerFactory.getLogger(TokenInvalidReSetScheduled.class);

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    RedisTemplate redisTemplate;

//    @Async("threadPoolTaskExecutor")
//    @Scheduled(cron = Constant.CRON)
    public void schedulerCheckCode() {
        try {
            logger.info("=====>>>>>token失效数据删除定时任务启动", System.currentTimeMillis());
            List<RequestIDDto> list = requestMapper.getAppIdAndUpdateTime();
            if (null == list) {
                return;
            }
            list.stream().forEach(tokenRecord -> {
                //TODO: 当缓存内数据失效时，对数据库数据进行失效
                if (!redisTemplate.hasKey(tokenRecord.getAppId())) {
                    requestMapper.deleteTokenRecord(tokenRecord.getId());
                    logger.info("AppId 为{} 的数据，令牌已过有效期，DB执行逻辑失效操作：" + tokenRecord.getAppId());
                }
            });
            //TODO: 当 secret 变更时，失效其以前的全部数据，只保留其最新的一条有效数据
        } catch (Exception e) {
            logger.error("定时监测并失效已过期的 token 令牌 —— 发送异常",e);
        }

    }
}
