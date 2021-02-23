package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.Cron;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 自定义定时器配置mappr
 *
 * @author Yuezejian
 * @date 2020年 11月09日 14:41:10
 */
public interface CronMapper {

    /**
     * 新增自定义定时器
     * @param cron
     * @return
     */
    boolean insert(Cron cron);

    /**
     * 查询全部有效定时器信息
     * @return
     */
    List<Cron> selectAll();

    /**
     * 查询全部定时器信息（包括无效数据，用于校验以保证定时器名称永不重复）
     * @return
     */
    List<Cron> selectAllIncludeUnable();

    /**
     * 查询全部正在使用中的执行器
     * @return
     */
    List<Cron> selectAllCronClassAndCronMethod();

    /**
     * 删除自定义定时器（逻辑删除，isDel为 0 不可用）
     * @param id
     * @return
     */
    boolean delete(@Param("id") Integer id);

    /**
     * 更新自定义定时器
     * @param cron
     * @return
     */
    boolean update(Cron cron);

    /**
     * 更新定时任务状态通过id
     * @param id
     * @param cronStatus
     * @return
     */
    boolean updateStatus(@Param("id") Integer id, @Param("cronStatus") Integer cronStatus);

    /**
     * 查询定时器列表
     * @param cronId
     * @return
     */
    List<Cron> selectByPrimaryKey(@Param("cronId") String cronId);

    /**
     * 查询定时器列表
     * @param cronId
     * @return
     */
    String selectParamsByCronId(@Param("cronId") String cronId);

    /**
     * 通过 cronClass 和 cronMethod 获取参数
     * @param cronClass
     * @param cronMethod
     * @return
     */
    String selectParamsByCronClassAndMethod(@Param("cronClass") String cronClass,@Param("cronMethod") String cronMethod);

    /**
     * 通过输入条件查询定时任务信息
     * @param searchFormDto
     * @return
     */
    List<Cron> selectAllBySearchForm(SearchFormDto searchFormDto);



}
