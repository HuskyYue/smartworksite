package com.slzhkj.smartworksite.server.service;

import org.springframework.stereotype.Service;

/**
 * <h1>数据入库模型service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月06日 10:51:38
 */
@Service
public interface InsertModelService {

    /**
     * <h2>查询该表名数据库内是否已经存在</h2>
     * @param table
     * @return
     */
    int selectByTableName(String table);
}
