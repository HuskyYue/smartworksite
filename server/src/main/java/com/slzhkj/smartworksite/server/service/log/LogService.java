package com.slzhkj.smartworksite.server.service.log;

import com.google.gson.Gson;
import com.slzhkj.smartworksite.model.entity.SysLog;
import com.slzhkj.smartworksite.model.mapper.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志监听service
 *
 * @author Yuezejian
 * @date 2020年 08月22日 21:06:22
 */
@Service
@Transactional
public class LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    @Autowired
    private SysLogMapper logMapper;

    //记录日志
    @Async("threadPoolTaskExecutor")
    public void recordLog(SysLog log) throws Exception {
        logger.info("异步日志记录，执行线程 thread: {}", Thread.currentThread());
        logMapper.insertSelective(log);
    }
}