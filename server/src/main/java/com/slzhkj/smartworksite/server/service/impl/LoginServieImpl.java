package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.LoginDto;
import com.slzhkj.smartworksite.model.mapper.LoginMapper;
import com.slzhkj.smartworksite.server.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * <h1>登录服务的实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月30日 14:30:46
 */
@Slf4j
@Service
public class LoginServieImpl implements ILoginService {

    @Autowired
    LoginMapper loginMapper;


    /**
     * <h2>用户登录系统</h2>
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        List<LoginDto> loginDtos = loginMapper.selectAll();
        if (CollectionUtils.isEmpty(loginDtos)) {
                throw new IllegalStateException("当前数据库用户表无数据");
        }
        Optional<LoginDto> loginDto = Optional.ofNullable(loginDtos.stream()
                .filter(l ->
                        username.equals(l.getUserName())
                                && password.equals(l.getPassword())
                )
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException("用户名或密码不正确")
                ));
        if (loginDto.isPresent()) {
            return true;
        }
        return  false;
    }

    /**
     * <h2>通过用户名获取系统登录用户的信息</h2>
     *
     * @param username
     * @return
     */
    @Override
    public LoginDto findUserInfoByUserName(String username) {
        List<LoginDto> loginDtos = loginMapper.selectAll();
        if (CollectionUtils.isEmpty(loginDtos)) {
            throw new IllegalStateException("当前数据库用户表无数据");
        }
        Optional<LoginDto> loginDto = Optional.ofNullable(loginDtos.stream()
                .filter(l ->
                        username.equals(l.getUserName())
                )
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("用户名不存在")
                ));
        if (loginDto.isPresent()) {
            return loginDto.get();
        }
        return  null;
    }
}
