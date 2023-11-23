package com.wpf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wpf.pojo.Result;
import com.wpf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//      自定义拦截器：实现HandlerInterceptor接口，并重写其所有方法
@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override   //目标资源方法执行前执行  返回TRUE：放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        1. 获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求路径：{}",url);
//        2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if(url.contains("/login")){
            log.info("正在登陆...");

            return true;
        }
//        如果不是登陆操作
//        3. 获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("从请求头中获取令牌：{}",token);
//        4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(token)){
            log.info("Token不存在");
            Result responseResult=Result.error("NOT_LOGIN");
            //把result对象转换为json格式字符串
            // （fastjson是阿里巴巴提供的用于实现对象和json的转换工具类）
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return false;
        }
//        5. 解析token，如果解析失败，返回错误结果（未登录）

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌解析失败！");
            Result responseResult=Result.error("NOT_LOGIN");
            //把result对象转换为json格式字符串
            // （fastjson是阿里巴巴提供的用于实现对象和json的转换工具类）
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return false;
        }
//        6. 放行

        System.out.println("preHandle....");
        return true;
    }

    @Override   //目标资源方法执行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override   //视图渲染完毕后执行，最后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
