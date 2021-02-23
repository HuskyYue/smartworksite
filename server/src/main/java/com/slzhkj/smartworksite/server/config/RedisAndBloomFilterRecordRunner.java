package com.slzhkj.smartworksite.server.config;

import com.slzhkj.smartworksite.model.dto.RequestDto;
import com.slzhkj.smartworksite.model.mapper.RequestMapper;
import com.slzhkj.smartworksite.server.controller.AbstractController;
import com.slzhkj.smartworksite.server.controller.ResponseController;
import com.slzhkj.smartworksite.server.controller.TokenAndSignController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>
 * This loader is used for data preheating of redis and bloom filter.
 * The current preheating data mainly includes:
 * The first, Three parties request obtain time effective token {@link TokenAndSignController },
 * When the bloom filter and redis are loaded,
 * they need to be updated when I start the system
 * The second,Three party request for data access,
 * we judge the token {@link ResponseController} if it's enable
 * </h1>
 *
 * @author Yuezejian
 * @date 2020年 11月 10日 14:00:18
 */
@Component
public class RedisAndBloomFilterRecordRunner extends AbstractController implements ApplicationRunner, Ordered {

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private Environment env;

    /**
     * <h2>Callback used to run the bean.</h2>
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String,String> tokenOpera = redisTemplate.opsForValue();
        List<RequestDto> requestDtos = requestMapper.selectTokenRecord();
        AtomicInteger count = new AtomicInteger(0);
        requestDtos.stream()
                .parallel()
                .forEach( dto -> {
                    filter.put(dto.getToken());
                    filter.put(dto.getAppId());
                    if (filter.mightContain(dto.getToken())) {
                        count.getAndAdd(1);
                    }
                    String key = dto.getAppId();
                    //TODO:- Math.abs((System.currentTimeMillis() - dto.getUpdateTime().getTime())/60000 )
                    tokenOpera.set(key,dto.getToken(),env.getProperty("token.enable.time",Long.class), TimeUnit.MINUTES);
                } );
        logger.info("==========total is "+ count +", The data preloading of redis and bloom filter is completed!===========");
    }

    /**
     * <h2>Makes the current class load with a higher priority.</h2>
     * @return
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
