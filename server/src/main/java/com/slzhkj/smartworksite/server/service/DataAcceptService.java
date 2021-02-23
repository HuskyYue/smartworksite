package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.DataAcceptDto;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * <h1>数据接入Service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月03日 10:49:55
 */
@Service
public interface DataAcceptService {

    /**
     * <h2>数据接入数据库</h2>
     * @param dto
     * @return
     */
    int put(DataAcceptDto dto);
}
