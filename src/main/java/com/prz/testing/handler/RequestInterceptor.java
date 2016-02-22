package com.prz.testing.handler;

import com.prz.testing.controller.UserData;
import com.prz.testing.util.LogUtil;
import com.prz.testing.util.RequestWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ROLO on 18.02.2016.
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogUtil log;

    Logger logger = Logger.getLogger(RequestInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String method = requestWrapper.getMethod();
            Map<String, String[]> parameters = requestWrapper.getParameterMap();
//        sb.append(userData.getId());
            sb.append(";HTTP-");
            sb.append(method.toUpperCase());
            sb.append(";" + requestWrapper.getRemoteHost() + ":" + requestWrapper.getRemotePort());
            sb.append(";" + requestWrapper.getRequestURL());
            if (parameters.size() < 1) {
                sb.append(";parameters:");
                sb.append(parameters);
            }
            if (method.equals("POST")) {
                String body = requestWrapper.getBody();
                sb.append(";body:");
                sb.append(body);
            }
            log.info(null != sb ? sb.toString() : "");
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return super.preHandle(request, response, handler);
    }
}

