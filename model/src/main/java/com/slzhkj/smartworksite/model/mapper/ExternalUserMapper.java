package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.User;

import java.util.List;

/**
 * <h1>外部用户管理 mapper</h1>
 * @author Yuezejian
 * @date 2020年 12月18日 14:45:56
 */
public interface ExternalUserMapper {

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
     * 条件查询外部用户
     * @param searchFormDto {@link SearchFormDto} 查询条件
     * @return
     */
    List<User> listExternalUser(SearchFormDto searchFormDto);

    /**
     * 通过 id 获取外部用户信息
     * @param id
     * @return
     */
    User getExternalUser(Integer id);

    /**
     * 用于条件判定
     * @return
     */
    List<User> listExternalUserAll();

    /**
     * 查询全部有效外部用户信息
     * @return
     */
    List<User> listUsableExternalUserAll();


}
