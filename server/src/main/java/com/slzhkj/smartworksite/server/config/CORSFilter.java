package com.slzhkj.smartworksite.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>cors 跨域访问</h1>
 *
 * @author Yuezejian
 * @date 2020年 12月02日 16:00:27
 */
@Component
@Slf4j
public class CORSFilter implements Filter {

    /**
     * <h2>初始化时执行操作</h2>
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * <h2>拦截器设置响应相关参数</h2>
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        log.info(String.format("访问信息ip:%s,uri:%s,port:%s", req.getRemoteAddr(), request.getRequestURI(), req.getRemotePort()));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, " +
                "sAccept, token, login-token, jsessionid, x-token , database");
        chain.doFilter(req, res);
    }

    /**
     * <h2>销毁时执行操作。</h2>
     */
    @Override
    public void destroy() {

    }

}
