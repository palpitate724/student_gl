package com.example.filter;

import com.example.jjwtukiter.JwtUkiter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;



@Slf4j
//@WebFilter(urlPatterns = "/*")
@CrossOrigin(origins = "*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("token校验过滤器启动");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; //请求
        HttpServletResponse res = (HttpServletResponse) response; //响应

        String uri = req.getRequestURI();
        if (uri.contains("/api/login") || uri.contains("/api/signup")) {
            log.info("登录/注册接口，放行");
            chain.doFilter(req, res);
            return;
        }
        String token = req.getHeader("token");
        if (token == null || token.isEmpty()){
            //返回401
            log.info("token为空，返回401");
            res.setStatus(401);
            return;
        }
        if(JwtUkiter.jiegouToken(token)){
            log.info("token有效，放行");
            chain.doFilter(req, res);
        }
        else {
            //返回401
            log.info("token无效，返回401");
            res.setStatus(401);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("token校验过滤器销毁");
    }
}
