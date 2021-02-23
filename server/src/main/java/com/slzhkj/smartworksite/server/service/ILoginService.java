package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.LoginDto;
import org.springframework.stereotype.Service;

/**
 * <h1>登录service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月30日 14:28:52
 */
@Service
public interface ILoginService {

    /**
     * <h2>用户登录系统</h2>
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean login(String username, String password);

    /**
     * <h2>通过用户名获取系统登录用户的信息</h2>
     * @param username
     * @return
     */
    LoginDto findUserInfoByUserName(String username);
}
