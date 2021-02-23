package com.slzhkj.smartworksite.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.slzhkj.smartworksite.model.dto.DataAcceptDto;
import com.slzhkj.smartworksite.model.entity.Cron;
import com.slzhkj.smartworksite.model.entity.DataProvideLog;
import com.slzhkj.smartworksite.model.entity.User;
import com.slzhkj.smartworksite.model.mapper.CronMapper;
import com.slzhkj.smartworksite.model.mapper.DataBaseMapper;
import com.slzhkj.smartworksite.model.mapper.ExternalUserMapper;
import com.slzhkj.smartworksite.model.mapper.PassiveProvideMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>数据校验的抽像类</h1>
 * @author Yuezejian
 * @date 2020年 12月16日 10:39:46
 */
@Slf4j
@Component
public abstract class AbstractValidServiceImpl {

    @Autowired
    CronMapper cronMapper;

    @Autowired
    PassiveProvideMapper passiveProvideMapper;

    @Autowired
    ExternalUserMapper externalUserMapper;

    @Autowired
    DataBaseMapper dataBaseMapper;

    /**
     * <h2>判断 Api 名称是否重复</h2>
     * @param dataProvide
     */
    protected void ApiNameIsUsable(DataProvideLog dataProvide) {
        Objects.requireNonNull(dataProvide);
        // API 名称不可重复
        List<DataProvideLog> dataProvideLogList = passiveProvideMapper.selectAll();
        Optional<DataProvideLog> ifApiNameHasExisted = dataProvideLogList.stream()
                .filter(d -> dataProvide.getApiName().equals(d.getApiName()))
                .findAny();
        if (ifApiNameHasExisted.isPresent()) {
            throw new IllegalArgumentException("API名称已经存在, 请重新输入");
        }
    }

    /**
     * <h2>判断 新修改的 API 名称是否与其他 接口信息的 API 名称重复</h2>
     * @param dataProvide
     */
    protected void ifApiNameHasExistInOthers(DataProvideLog dataProvide) {
        Objects.requireNonNull(dataProvide);
        List<DataProvideLog> dataProvideLogList = passiveProvideMapper.selectAll();

        // 判断是否存在
        Optional<DataProvideLog> ifCurrencyDataProvideLogExist = dataProvideLogList.stream().filter(
                d -> d.getId().equals(dataProvide.getId())
        ).findAny();
        ifCurrencyDataProvideLogExist.orElseThrow(
                () -> new IllegalArgumentException("当前 API 接口信息不存在！")
        );

        List<DataProvideLog> dataProvideLogIncludeUnable = passiveProvideMapper.selectAllIncludeUnable();
        // 判断新改的 API 名称是否可用
        Optional<DataProvideLog> anotherSameApiNameDataProvideLog = dataProvideLogIncludeUnable.stream().filter(
                d -> !dataProvide.getId().equals(d.getId())
                        && dataProvide.getApiName().equals(d.getApiName())
        ).findAny();
        if (anotherSameApiNameDataProvideLog.isPresent()) {
            throw new IllegalArgumentException("API 名称已存在，请重新拟定！");
        }

    }

    /**
     * <h2>通过 id 查询 判断当前要进行修改的定时任务是否存在</h2>
     * @param cron
     * @return
     */
    protected Cron ifCurrencyCronTaskExist(Cron cron) {
        Objects.requireNonNull(cron);
        List<Cron> cronList = cronMapper.selectAll();
        if (CollectionUtils.isEmpty(cronList)) {
            log.info("数据库中无任何定时任务记录");
            throw new IllegalStateException("数据库中无任何定时任务记录");
        }
        return cronList.stream().filter( c -> c.getId().equals(cron.getId())).findFirst().orElseThrow(
                () -> new IllegalStateException("当前任务不存在 ")
        );
    }

    /**
     * <h2>定时任务名称是否与其他定时任务名称重复</h2>
     * @param cron {@link Cron}
     */
    protected void ifCronTaskNameHasExistInOthers(Cron cron) {
        Objects.requireNonNull(cron);
        List<Cron> cronList = cronMapper.selectAllIncludeUnable();
        List<Cron> cronOtherList = cronList.stream()
                .filter(
                        c -> !c.getId().equals(cron.getId()))
                .collect(Collectors.toList());
        //判断 更新后的定时任务名称，是否已经在其他任务中存在(包括已经失效的数据中的任务名称)
        Optional<Cron> ifSameCronNameWhiteOtherCron = cronOtherList.stream()
                .filter( c -> c.getCronName().equals(cron.getCronName())).findAny();
        if (ifSameCronNameWhiteOtherCron.isPresent()) {
            log.error("定时任务名称不可重复,，通过 id 更新定时任务信息出错", JSON.toJSONString(cron) );
            throw new IllegalStateException("定时任务名称已存在，请另行修改");
        }
    }

    /**
     * <h2>通过 id 查询当前对外提供数据接口信息是否存在</h2>
     * @param dataProvideLog
     * @return
     */
    protected DataProvideLog ifCurrencyDataProvidePassiveInfoExist(DataProvideLog dataProvideLog) {
        Objects.requireNonNull(dataProvideLog);
        List<DataProvideLog> all = passiveProvideMapper.selectAll();
        Optional<DataProvideLog> res = all.stream()
                .filter( d -> d.getId().equals(dataProvideLog.getId()))
                .findAny();
        return res.orElseThrow(
                () -> new IllegalArgumentException("当前 API 接口信息不存在！")
        );
    }

    /**
     * <h2>外部人员信息管理，appId 和 用户名 验证是否重复</h2>
     * @param user
     */
    protected void ifAppIdOrUserNameHasExist(User user){
        List<User> allExternalUser = externalUserMapper.listExternalUserAll();

        System.out.println(allExternalUser);
        Optional<User> userNameHasExist = allExternalUser.stream()
                .filter( u -> u.getUserName().equals(user.getUserName()))
                .findAny();
        if (userNameHasExist.isPresent()) {
            throw new IllegalArgumentException("用户名已存在，请重设！ ");
        }

        Optional<User> appIdHasExist = allExternalUser.stream()
                .filter( u -> u.getAppId().equals(user.getAppId()))
                .findAny();
        if (appIdHasExist.isPresent()) {
            throw new IllegalArgumentException("AppId 已存在，请重设！ ");
        }

    }

    /**
     * <h2>外部人员信息管理，当前用户名是否存在验证</h2>
     * @param id
     * @return
     */
    protected User ifCurrencyExternalUserExist(Integer id) {
        List<User> allExternalUser = externalUserMapper.listExternalUserAll();
        System.out.println(allExternalUser);
        Optional<User> ifExternalUserExist = allExternalUser.stream()
                .filter( u -> u.getId().equals(id))
                .findAny();
        return ifExternalUserExist.orElseThrow(
                () -> new IllegalArgumentException("当前用户不存在")
        );
    }

    /**
     * <h2>外部人员信息管理，当前外部用户是否为有效数据</h2>
     * @param id
     * @return
     */
    protected User ifCurrencyExternalUserUsable(Integer id) {
        List<User> UsableExternalUserAll = externalUserMapper.listUsableExternalUserAll();
        System.out.println(UsableExternalUserAll);
        Optional<User> ifExternalUserExist = UsableExternalUserAll.stream()
                .filter( u -> u.getId().equals(id))
                .findAny();
        return ifExternalUserExist.orElseThrow(
                () -> new IllegalArgumentException("当前用户不存在")
        );
    }

    /**
     * <h2>是否当前 appId 或用户名已经存在</h2>
     * @param user
     */
    protected void ifCurrencyAppIdOrUserNameHasExistInOthers(User user) {
        List<User> allUserIncludeUnable = externalUserMapper.listExternalUserAll();
        Optional<User> isUserNameHasExistInOther = allUserIncludeUnable.stream()
                .filter( u -> !u.getId().equals(user.getId()) && u.getUserName().equals(user.getUserName()))
                .findAny();
        if (isUserNameHasExistInOther.isPresent()) {
            throw new IllegalArgumentException("当前用户名已存在");
        }

        Optional<User> isAppIdHasExistInOther = allUserIncludeUnable.stream()
                .filter( u -> !u.getId().equals(user.getId()) && u.getAppId().equals(user.getAppId()))
                .findAny();
        if (isAppIdHasExistInOther.isPresent()) {
            throw new IllegalArgumentException("当前 AppId 已存在");
        }

    }

    /**
     * <h2>是否当前定时任务或被动接入数据接口，处于停止或关闭等可删除状态</h2>
     * @param id
     */
    protected void ifCurrencyCronTaskIsStopUsedOrNotPublic(Integer id) {
        List<Cron> cronList = cronMapper.selectAll();
        Optional<Cron> isHasBeenStoppedCronTask = cronList.stream()
                .filter(cron -> cron.getId().equals(id)
                        && cron.getCronStatus() == 0)
                .findAny();
        isHasBeenStoppedCronTask.orElseThrow(
                () -> new IllegalArgumentException("正在使用中，无法关闭")
        );
     }

    /**
     * <h2>是否当前对外提供数据接口，处于停止或关闭等可删除状态</h2>
     * @param id
     */
     protected void ifCurrencyDataProvideLogIsStopUsedOrNotPublic(Integer id) {
         List<DataProvideLog> all = passiveProvideMapper.selectAll();
         Optional<DataProvideLog> isHasBeenStoppedDataProvideLog = all.stream()
                 .filter(cron -> cron.getId().equals(id) && cron.getApiStatus() == 0)
                 .findAny();
         isHasBeenStoppedDataProvideLog.orElseThrow(
                 () -> new IllegalArgumentException("正在使用中，无法关闭")
         );
     }

    /**
     * 判定当前任务是否配置对应的关联映射
     * @param id
     */
    protected void ifCurrencyCronTaskHasRelatedMapper(String id) {
        List<String> cronIds = dataBaseMapper.listCronIdsInTableMappers();
        Optional<String> currencyCronId = cronIds.stream()
                .filter( cronId -> cronId.equals(id))
                .findAny();
        currencyCronId.orElseThrow(
                () -> new IllegalArgumentException("请先配置关联映射，否则无法提交")
        );


    }

}