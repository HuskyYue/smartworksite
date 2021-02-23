package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.User;
import com.slzhkj.smartworksite.model.mapper.ExternalUserMapper;
import com.slzhkj.smartworksite.server.service.IExternalUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1> 用户管理 service 实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 12月18日 14:03:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExternalUserServiceImpl extends AbstractValidServiceImpl implements IExternalUserService {

    /** 外部用户信息操作 mapper */
    private final ExternalUserMapper externalUserMapper;

    public ExternalUserServiceImpl(ExternalUserMapper externalUserMapper) {
        this.externalUserMapper = externalUserMapper;
    }

    /**
     * 新增外部用户
     *
     * @param user {@link User}
     * @return
     */
    @Override
    public boolean insertExternalUser(User user) {

        Objects.requireNonNull(user);
        ifAppIdOrUserNameHasExist(user);

        return externalUserMapper.insertExternalUser(user);
    }

    /**
     * 通过 id 逻辑删除单个外部用户信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteExternalUser(Integer id) {

        ifCurrencyExternalUserUsable(id);

        return externalUserMapper.deleteExternalUser(id);
    }

    /**
     * 更新外部用户
     *
     * @param user {@link User}
     * @return
     */
    @Override
    public boolean updateExternalUser(User user) {

        Objects.requireNonNull(user);
        ifCurrencyExternalUserExist(user.getId());
        ifCurrencyAppIdOrUserNameHasExistInOthers(user);

        return externalUserMapper.updateExternalUser(user);
    }

    /**
     * 通过 id 获取单个外部用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getExternalUser(Integer id) {
        ifCurrencyExternalUserExist(id);
        return externalUserMapper.getExternalUser(id);
    }

    /**
     * 条件查询外部用户
     *
     * @param searchFormDto {@link SearchFormDto} 查询条件
     * @return
     */
    @Override
    public List<User> listExternalUser(SearchFormDto searchFormDto) {
        Objects.requireNonNull(searchFormDto);
        List<User> allUserList = externalUserMapper.listExternalUser(searchFormDto);
        Integer currentPage = searchFormDto.getCurrentPage();
        Integer pageSize = searchFormDto.getPageSize();
        if (CollectionUtils.isEmpty(allUserList)) {
            return null;
        } else {
            if ( currentPage == 1 ) {
                return allUserList.stream().sorted(Comparator.comparing(User::getId).reversed())
                        .limit(pageSize)
                        .collect(Collectors.toList());
            } else {
                return allUserList.stream().sorted(Comparator.comparing(User::getId).reversed())
                        .skip((currentPage - 1) * pageSize).limit(pageSize)
                        .collect(Collectors.toList());
            }
        }

    }
}
