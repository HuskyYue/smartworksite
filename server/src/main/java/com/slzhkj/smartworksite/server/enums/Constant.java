package com.slzhkj.smartworksite.server.enums;

/**
 * <h1>系统常量配置</h1>
 *
 * @author Yuezejian
 * @date 2020年 08月23日 09:05:01
 */
public class Constant {

    /**
     * <h2>redis 常量</h2>
     */
    public static class Redis{

        public static final String RedisStringPrefix="SpringBootRedis:String:V1:";

        public static final String RedisListPrefix="SpringBootRedis:List:User:V1:";

        public static final String RedisHashKeyConfig ="SpringBootRedis:Hash:Key:SysConfig:V1";

        public static final String RedisCacheBeatLockKey="SpringBootRedis:LockKey:";

        public static final String RedisSchedulerMethodParamsKey= "SpringBootRedis:Scheduler:Method:ParamsKey:v1";

        public static final String RedisHashSchedulerDataRequestScheduledMethod ="RedisHash:Scheduler:DataRequestScheduled:Method:V1";

    }

    public static final String SplitChar = "_";

    public static final String logOperateUser="yuezejian";

    /* 建筑工人实名制管理平台标准化服务接口 */
    public static final String version = "1.0";

    public static final String SchedulerClassPrefix = "com.slzhkj.smartworksite.server.scheduler.";

    /**
     * <h2>API 常量</h2>
     */
    public static class API{

        /* 本程序访问的 url 前缀 */
        public static final String SmartWorkSiteGetPrefix = "http://192.168.101.60:7119/worker/";

        /* 中科 http 的 post 推送 url 前缀 */
        public static final String SmartPostAPIPrefix = "http://221.238.40.114:8059/api/smart";

    }

    /**
     * <h2>定时器类</h2>
     */
    public static class SCHEDULED{

        public static final String DataRequestScheduled = "DataRequestScheduled";

        public static final String MethodPrefix = "start";
    }
}