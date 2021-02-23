package com.slzhkj.smartworksite.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.slzhkj.smartworksite.model.dto.SchedulerDto;
import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.Cron;
import com.slzhkj.smartworksite.model.mapper.CronMapper;
import com.slzhkj.smartworksite.model.mapper.SchedulerMapper;
import com.slzhkj.smartworksite.server.enums.Constant;
import com.slzhkj.smartworksite.server.service.CronBaseService;
import com.slzhkj.smartworksite.server.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>定时任务service实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月16日 15:47:23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CronBaseServiceImpl extends AbstractValidServiceImpl implements CronBaseService {

    @Autowired
    CronMapper cronMapper;

    @Autowired
    SchedulerMapper schedulerMapper;


    /**
     * <h2>定时任务一键启停</h2>
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean startOrStopScheduled(Integer id, Integer status) throws Exception {
        //TODO: 更新数据状态，重启或停止当前任务
        List<Cron> cronList = cronMapper.selectAll();
        if ( null != cronList ) {
            Cron cron = cronList.stream()
                    .filter( i -> i.getId() == id ).limit(1)
                    .collect(Collectors.toList()).get(0);
            //如果为主动接入，需要启停定时器
            if (cron.getClassify() == 1) {
                    ScheduleUtils.Job job = new ScheduleUtils.Job();
                    job.setClassName(Constant.SchedulerClassPrefix + cron.getCronClass());
                    job.setCron(cron.getCron());
                    job.setJobName(cron.getCronName());
                    job.setMethodName(cron.getCronMethod());
                    job.setStatus(cron.getCronStatus());
                    if ( 0 == status ) {
                        ScheduleUtils.cancel(job);
                        ScheduleUtils.cancel(job);
                        return cronMapper.updateStatus(id, 0);
                    } else {
                        ScheduleUtils.add(job);
                        return cronMapper.updateStatus(id, 1);
                    }
            } else {
                // 如果被被动接入，不需要处理定时器，直接更新状态就行
                if ( 0 == status ) {
                    return cronMapper.updateStatus(id, 0);
                } else {
                    return cronMapper.updateStatus(id, 1);
                }
            }
        } else {
            throw new IllegalStateException("当前数据库中无任何定时任务");
        }
    }

    /**
     * <h2>新增定时任务</h2>
     *
     * @param cron {@link Cron}
     * @return
     * @throws IllegalStateException
     */
    @Override
    public boolean insert(Cron cron) throws IllegalStateException {
        Objects.requireNonNull(cron);
        if (StringUtils.isEmpty(cron.getCronId())) {
            throw new IllegalStateException("请先配置关联映射，否则无法提交");
        }
        //判定当前任务是否配置了对应的关联映射
        ifCurrencyCronTaskHasRelatedMapper(cron.getCronId());
        //TODO: 为任务自动配置可用的执行器
        List<SchedulerDto> schedulerDtos = getUsableScheduler();
        cron.setCronClass(schedulerDtos.get(0).getCronClass());
        cron.setCronMethod(schedulerDtos.get(0).getCronMethod());

        List<Cron> cronList = cronMapper.selectAllIncludeUnable();
        if ( null != cronList ) {
            //TODO: 通过任务名称获取任务信息
            Optional<Cron> cronOptional = cronList.stream()
                    .filter(
                            cron1 -> cron1.getCronName().equals(cron.getCronName()))
                    .findAny();
            if (cronOptional.isPresent()) {
                throw new IllegalStateException("当前定时任务名称已存在，请重新拟定！");
            }
            //TODO: 如果数据库无数据，直接新增；如果数据库有数据，
            // 判断是否有重复的反射方法，有报异常，不允许新增
            int size = cronList.stream()
                    .filter(cron2 -> cron2.getCronClass().equals(cron.getCronClass())
                            && cron2.getCronMethod().equals(cron.getCronMethod()))
                    .limit(1)
                    .collect(Collectors.toList()).size();
           if ( size == 0 ) {
              return cronMapper.insert(cron);
           } else {
               log.error("任务：{}，当前定时任务已存在，即映射类和方法重复，请检查并重新设定！",
                       cron.getCronName());
               throw new IllegalStateException(
                       "当前定时任务已存在，请检查并重新设定！"
               );}
        } else {
            return cronMapper.insert(cron);
        }

    }

    /**
     * <h2>查询全部定时器信息</h2>
     *
     * @param currentPage
     * @param pageSize
     * @return List<Cron> {@link Cron}
     */
    @Override
    public List<Cron> selectAllBySearchForm(Integer currentPage,
                                            Integer pageSize, SearchFormDto searchForm) {

        List<Cron> res = cronMapper.selectAllBySearchForm(searchForm);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        } else if (searchForm.getClassify() == 0) {
            if ( currentPage == 1 ) {
                return res.stream().sorted(Comparator.comparing(Cron::getId).reversed())
                        .limit(pageSize)
                        .map( d -> Cron.acquireTimesLimit2acquireTimesLimitDescription(d))
                        .collect(Collectors.toList());
            } else {
                return res.stream().sorted(Comparator.comparing(Cron::getId).reversed())
                        .skip((currentPage - 1) * pageSize).limit(pageSize)
                        .map( d -> Cron.acquireTimesLimit2acquireTimesLimitDescription(d))
                        .collect(Collectors.toList());
            }
        } else {
            if ( currentPage == 1 ) {
                return res.stream().sorted(Comparator.comparing(Cron::getId).reversed())
                        .limit(pageSize)
                        .collect(Collectors.toList());
            } else {
                return res.stream().sorted(Comparator.comparing(Cron::getId).reversed())
                        .skip((currentPage - 1) * pageSize).limit(pageSize)
                        .collect(Collectors.toList());
            }
        }
    }

    /**
     * <h2>通过 cronId 获取参数</h2>
     *
     * @param cronId
     * @return
     */
    @Override
    public String selectParamsByCronId(String cronId) {

        return cronMapper.selectParamsByCronId(cronId);
    }

    /**
     * <h2>通过 cronClass 和 cronMethod 获取参数</h2>
     *
     * @param cronClass
     * @param cronMethod
     * @return
     */
    @Override
    public String selectParamsByCronClassAndMethod(String cronClass, String cronMethod) {
        return cronMapper.selectParamsByCronClassAndMethod(cronClass, cronMethod);
    }

    /**
     * <h2>定时器任务逻辑删除</h2>
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        ifCurrencyCronTaskIsStopUsedOrNotPublic(id);
        return cronMapper.delete(id);
    }

    /**
     * <h2>更新定时器</h2>
     *
     * @param cron
     * @return
     */
    @Override
    public boolean update(Cron cron) {
        Objects.requireNonNull(cron);
        //全部定时任务记录
        List<Cron> cronList = cronMapper.selectAll();
        //判断当前定时任务信息是否存在
        ifCurrencyCronTaskExist(cron);
        //定时任务名称不可与其他定时任务名称重复
        ifCronTaskNameHasExistInOthers(cron);
        return cronMapper.update(cron);
    }

    /**
     * <h2>查询当前可用的执行器</h2>
     *
     * @return
     */
    @Override
    public List<SchedulerDto> getUsableScheduler() {

        //查询正在被使用的执行器信息
        List<Cron> cronList = cronMapper.selectAllCronClassAndCronMethod();
        List<SchedulerDto> schedulerDtos = cronList.stream()
                .map(Cron::cron2SchedulerDto)
                .collect(Collectors.toList());

        //查询全部可用的执行器信息
        List<SchedulerDto> schedulerDtoList = schedulerMapper.selectAll();

        //无正在执行的，直接返回全部执行器
        if (CollectionUtils.isEmpty(schedulerDtos)) {
            return schedulerDtoList;
        }

        //无可用执行器，抛出异常
        if (CollectionUtils.isEmpty(schedulerDtoList)) {
            throw new IllegalStateException("当前数据库中无可用执行器信息，请在 DataBase 另行添加");
        }

        //从全部可用执行器中移除正在使用的执行器
        List<String> schedulerUsableStringList = schedulerDtos.stream()
                .map( c -> JSON.toJSONString(c))
                .collect(Collectors.toList());

        List<String> schedulerAllStringList = schedulerMapper.selectAll().stream()
                .map( c -> JSON.toJSONString(c))
                .collect(Collectors.toList());

        List<SchedulerDto> res = new LinkedList<>();

        if (CollectionUtils.isSubCollection(
                schedulerUsableStringList,
                schedulerAllStringList
        )) {
            Collection<String> collection = CollectionUtils.subtract(
                    schedulerAllStringList,
                    schedulerUsableStringList
            );
            collection.stream().forEach( s -> {
                res.add(JSON.parseObject(s,SchedulerDto.class));
            });
        }

        if (CollectionUtils.isEmpty(res)) {
            throw new IllegalStateException("当前系统可用执行器剩余数量为 0 ，请在后台另行添加");
        }

        return res;
    }

    /**
     * 通过 id 获取定时任务信息
     * @param id
     * @return
     */
    @Override
    public Cron getSchedulerInfoById(Integer id) {
        Cron cron = Cron.getInstance();
        cron.setId(id);
        return ifCurrencyCronTaskExist(cron);
    }


}
