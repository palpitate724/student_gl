package com.example.intercrptor;


import com.example.jjwtukiter.JwtUkiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenIntercrptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {



        String token = req.getHeader("token");
        if (token == null || token.isEmpty()){
            //返回401
            log.info("token为空，返回401");
            res.setStatus(401);
            return false;
        }
        if(JwtUkiter.jiegouToken(token)){
            log.info("token有效，放行");
            return true;
        }
        else {
            //返回401
            log.info("token无效，返回401");
            res.setStatus(401);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
