package com.slzhkj.smartworksite.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.LoginDto;
import com.slzhkj.smartworksite.model.dto.UserDto;
import com.slzhkj.smartworksite.server.service.ILoginService;
import com.slzhkj.smartworksite.server.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <h1> login 相关操作的控制器</h1>
 *
 * @author Yuezejian
 * @date 2020年 12月01日 08:54:03
 */
@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController extends AbstractController{

    @Resource
    private HttpServletResponse response1;

    @Resource
    private HttpSession session;

    @Autowired
    private ILoginService loginService;

    /**
     * url: /login/token
     * <h2>登录校验</h2>
     * @param loginDto {@link LoginDto}
     * @return 结果
     */
    @PostMapping(value = "token")
    public BaseResponse login(@RequestBody UserDto loginDto)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);

        try {
            //TODO 登录验证
                if (loginService.login(loginDto.getUsername(), loginDto.getPassword())) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.convertValue(loginDto, new TypeReference<Map<String, Object>>() {
                });
                map.put("token", "admin-token");
                map.remove("password");
                session.setAttribute("admin", "admin");
                // 目前暂时将value写死
                session.setAttribute(
                        FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin");
                response1.addHeader("jsessionid", EncryptUtils.base64Encode(session.getId()));
                //session有效期：1小时
                session.setMaxInactiveInterval(60 * 60);
                response.setData(map);

            }
        } catch (Exception e) {
            logger.error("登录获取 token 异常: {}",e.getMessage() );
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }



    /**
     * url: /login/info
     * <h2>通过用户名获取系统登录用户的信息</h2>
     * @param username 用户名
     * @return
     */
    @GetMapping(value = "info")
    public BaseResponse getUserInfo(@RequestParam String username) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            LoginDto loginDto = loginService.findUserInfoByUserName(username);
            response.setData(loginDto);
        } catch (Exception e) {
            logger.error("通过用户名获取系统登录用户的信息异常: {}",e.getMessage() );
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * url: /login/logout
     * <h2>退出登录</h2>
     * @return
     */
    @PostMapping(value = "logout")
    public BaseResponse logout() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            session.invalidate();
            response.setData("success");
        } catch (Exception e) {
            logger.error("用户登出系统异常: {}",e.getMessage() );
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
