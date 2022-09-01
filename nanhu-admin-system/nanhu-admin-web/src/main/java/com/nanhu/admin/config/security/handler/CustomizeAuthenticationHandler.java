package com.nanhu.admin.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nanhu.admin.utils.ResultVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc: 匿名用户访问资源时处理器
 */
@Component("customizeAuthenticationHandler")
public class CustomizeAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String res = JSONObject.toJSONString(
                new ResultVo<>("匿名用户无权限访问！", 600, null)
                , SerializerFeature.DisableCircularReferenceDetect);

        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();

    }
}
