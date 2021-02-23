package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>用户服务相关 service 接口</h1>
 * @author Yuezejian
 * @date 2020年 12月18日 14:01:59
 */
@Service
public interface IExternalUserService {

    /**
     * 新增外部用户
     * @param user {@link User}
     * @return
     */
    boolean insertExternalUser(User user);

    /**
     * 通过 id 逻辑删除单个外部用户信息
     * @param id
     * @return
     */
    boolean deleteExternalUser(Integer id);


    /**
     * 更新外部用户
     * @param user {@link User}
     * @return
     */
    boolean updateExternalUser(User user);

    /**
     * 通过 id 获取单个外部用户信息
     * @param id
     * @return
     */
    User getExternalUser(Integer id);

    /**
     * 条件查询外部用户
     * @param searchFormDto {@link SearchFormDto} 查询条件
     * @return
     */
    List<User> listExternalUser(SearchFormDto searchFormDto);




}
