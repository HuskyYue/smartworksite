package com.slzhkj.smartworksite.server.scheduler;

import com.slzhkj.smartworksite.model.entity.Cron;
import com.slzhkj.smartworksite.model.mapper.CronMapper;
import com.slzhkj.smartworksite.server.config.BeanWiredAdvice;
import com.slzhkj.smartworksite.server.enums.Constant;
import java.util.Optional;

/**
 * 请求三方进行数据接入的定时器类（主动）
 * 定时任务数量超过当前定义的方法时，需要另行添加方法，该方法通过 java 反射进行执行
 * 当前预留了 20 个 定时任务启动器
 *
 * @author Yuezejian
 * @date 2020年 11月03日 10:44:45
 */
public class DataRequestScheduled extends BaseRequestScheduled {

    private CronMapper cronMapper = BeanWiredAdvice.getBean(CronMapper.class);

    public void start1() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start1").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start2() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start2").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start3() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start3").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start4() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start4").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start5() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start5").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start6() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start6").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start7() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start7").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start8() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start8").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start9() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start9").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start10() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start10").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start11() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start11").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start12() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start12").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start13() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start13").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start14() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start14").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start15() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start15").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start16() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start16").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start17() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start17").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start18() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start18").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start19() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start19").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start20() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start20").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start21() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start21").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }
    public void start22() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start22").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start23() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start23").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }
    public void start24() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start24").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }
    public void start25() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start25").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start26() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start26").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start27() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start27").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start28() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start28").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start29() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start29").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    public void start30() {
        //TODO:通过类名和方法名，获取进行调用的参数
        getCron("start30").ifPresent( cron -> {
            scheduled(cron.getCronClassParams(),Constant.API.SmartWorkSiteGetPrefix + cron.getPostUrl(),
                    Constant.API.SmartWorkSiteGetPrefix + cron.getGetUrl());
        } );
    }

    private Optional<Cron> getCron(String method) {
        return cronMapper.selectAll().stream()
                .filter(cron1 -> Constant.SCHEDULED.DataRequestScheduled.equals(cron1.getCronClass())
                        && method.equals(cron1.getCronMethod())).findAny();
    }


}
