package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.SchedulerDto;
import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.Cron;
import com.slzhkj.smartworksite.server.enums.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <h1>定时器service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月16日 14:22:33
 */
@SuppressWarnings("all")
@Service
public interface CronBaseService {

    /**
     * <h2>定时任务启停</h2>
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    boolean startOrStopScheduled(Integer id, Integer status) throws Exception;

    /**
     * <h2>新增定时任务</h2>
     * @param cron {@link Cron}
     * @return
     * @throws IllegalStateException
     */
    boolean insert(Cron cron) throws IllegalStateException;

    /**
     * <h2>查询全部定时器信息</h2>
     * @param SearchFormDto 查询条件 {@link SearchFormDto}
     * @return List<Cron> {@link Cron}
     */
    List<Cron> selectAllBySearchForm(Integer currentPage, Integer pageSize, SearchFormDto searchForm);

    /**
     * <h2>通过 cronId 获取参数</h2>
     * @param cronId
     * @return
     */
    String selectParamsByCronId(String cronId);

    /**
     * <h2>通过 cronClass 和 cronMethod 获取参数</h2>
     * @param cronClass
     * @param cronMethod
     * @return
     */
    String selectParamsByCronClassAndMethod(String cronClass, String cronMethod);

    /**
     * <h2>定时器任务逻辑删除</h2>
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * <h2>更新定时器</h2>
     * @param cron
     * @return
     */
    boolean update(Cron cron);

    /**
     * <h2>查询当前可用的执行器</h2>
     * @return
     */
    List<SchedulerDto> getUsableScheduler();

    /**
     * <h2>通过主键 id 查询定时任务信息</h2>
     * @param id
     * @return
     */
    Cron getSchedulerInfoById(Integer id);

    /**
     * <h2>
     * Through the unique key in the method,
     * the parameters of the query method will be querying redis first,
     * and the database will not be querying before it can be found.
     * </h2>
     * @param cronId unique key of the Corn {@link Cron}
     * @return
     * @throws Exception
     */
    default String selectParamsByCronIdFromRedis(String cronId) throws Exception{
        RedisTemplate redisTemplate = new RedisTemplate();
        HashOperations<String,String,String> schedulerParamsHash = redisTemplate.opsForHash();
        String params = schedulerParamsHash.get(Constant.Redis.RedisSchedulerMethodParamsKey,cronId);
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(params)) {
            sb.append("{").append(params).append("}");
        } else {
            throw new IllegalStateException("Redis query failed");
        }
        return sb.toString();
    }
}
