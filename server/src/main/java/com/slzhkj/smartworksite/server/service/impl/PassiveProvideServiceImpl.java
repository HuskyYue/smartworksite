package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.DataProvideLog;
import com.slzhkj.smartworksite.model.mapper.PassiveProvideMapper;
import com.slzhkj.smartworksite.server.service.IPassiveProvideService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <h1>数据对外提供（被动）实现类</h1>
 * @author Yuezejian
 * @date 2020年 12月14日 09:27:00
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PassiveProvideServiceImpl extends AbstractValidServiceImpl implements IPassiveProvideService {

    @Autowired
    PassiveProvideMapper passiveProvideMapper;

    /**
     * <h2>通过查询条件查询数据对外提供接口（被动）</h2>
     *
     * @param searchForm
     * @return
     */
    @Override
    public List<DataProvideLog> getListBySearchForm(SearchFormDto searchForm) {
        // 参数不可为空
        Objects.requireNonNull(searchForm);
        Integer currentPage = searchForm.getCurrentPage();
        Integer pageSize = searchForm.getPageSize();
        // 全部符合条件的数据提供接口信息
        List<DataProvideLog> res = passiveProvideMapper.selectAllBySearchForm(searchForm);
        // 分页显示
        if (CollectionUtils.isEmpty(res)) {
            return null;
        } else {
            if ( currentPage == 1 ) {
                return res.stream().sorted(Comparator.comparing(DataProvideLog::getId).reversed())
                        .limit(pageSize)
                        .map( d -> DataProvideLog.acquireTimesLimit2acquireTimesLimitDescription(d))
                        .collect(Collectors.toList());
            } else {
                return res.stream().sorted(Comparator.comparing(DataProvideLog::getId).reversed())
                        .skip((currentPage - 1) * pageSize).limit(pageSize)
                        .map( d -> DataProvideLog.acquireTimesLimit2acquireTimesLimitDescription(d))
                        .collect(Collectors.toList());
            }
        }

    }

    /**
     * <h2>数据对外提供接口（被动）开放/关闭</h2>
     *
     * @param id     接口唯一标识
     * @param status 接口开放状态
     * @return
     * @throws Exception
     */
    @Override
    public boolean startOrStopScheduled(Integer id, Integer status) {

        DataProvideLog provideLog = new DataProvideLog(id, status);
        ifCurrencyDataProvidePassiveInfoExist(provideLog);
        return passiveProvideMapper.update(provideLog);

    }

    /**
     * <h2>新增数据对外提供接口（被动）</h2>
     *
     * @param dataProvide {@link DataProvideLog} 数据对外提供接口信息
     * @return
     * @throws IllegalStateException
     */
    @Override
    public boolean insert(DataProvideLog dataProvide) {
        ApiNameIsUsable(dataProvide);
        return passiveProvideMapper.insert(dataProvide);
    }

    /**
     * <h2>更新数据对外提供接口（被动）</h2>
     *
     * @param dataProvide
     * @return
     */
    @Override
    public boolean update(DataProvideLog dataProvide) {
        ifApiNameHasExistInOthers(dataProvide);
        return passiveProvideMapper.update(dataProvide);
    }

    /**
     * <h2>通过 ID 获取对外数据提供 （被动） 接口信息</h2>
     *
     * @param id
     * @return
     */
    @Override
    public DataProvideLog getDataProvidePassiveInfoById(Integer id) {
        DataProvideLog dataProvideLog = DataProvideLog.getInstance();
        dataProvideLog.setId(id);
        return ifCurrencyDataProvidePassiveInfoExist(dataProvideLog);
    }

    /**
     * <h2>通过 ID 逻辑删除对外数据提供 （被动） 接口信息</h2>
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteDataProvideLog(Integer id) {
        DataProvideLog dataProvideLog = DataProvideLog.getInstance();
        dataProvideLog.setId(id);
        ifCurrencyDataProvidePassiveInfoExist(dataProvideLog);
        ifCurrencyDataProvideLogIsStopUsedOrNotPublic(id);
        return passiveProvideMapper.delete(id);
    }

}
