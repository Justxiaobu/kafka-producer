package com.tbl.api.kafka.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author sunbin
 * date 2018/6/27 15:03
 * description Token拦截器
 */
@SpringBootConfiguration
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static String id;
    @Value("${client.id}")
    public void setId(String id) {
        TokenInterceptor.id = id;
    }

    private static String secret;
    @Value("${client.secret}")
    public void setSecret(String secret) {
        TokenInterceptor.secret = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;

        if (request.getRequestURI().toString().indexOf("swagger") == -1 && request.getRequestURI().toString().indexOf("/v2/api-docs") == -1) {
            String clientId = request.getHeader("CLIENT_ID");
            String clientSecret = request.getHeader("CLIENT_SECRET");
            if (null != clientId && null != clientSecret) {
                String[] idList = id.split(",");
                String[] secretList = secret.split(",");
                //匹配配置文件中的id和secret
                for(int i=0;i<idList.length;i++){
                    if(clientId.equals(idList[i])){
                        if(clientSecret.equals(secretList[i])){
                            flag = true;
                        }else {
                            flag = false;
                        }
                        break;
                    }else{
                        flag = false;
                    }
                }
            }else{
                flag= false;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

    }
}
