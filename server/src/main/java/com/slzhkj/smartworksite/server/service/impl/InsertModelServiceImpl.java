package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.mapper.DataBaseMapper;
import com.slzhkj.smartworksite.server.service.InsertModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>数据入库模型service 实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月06日 10:54:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InsertModelServiceImpl implements InsertModelService {

    @Autowired
    DataBaseMapper dbMapper;

    /**
     * <h2>查询该表名数据库内是否已经存在</h2>
     *
     * @param table
     * @return
     */
    @Override
    public int selectByTableName(String table) {
        return dbMapper.selectByTableName(table);
    }
}
