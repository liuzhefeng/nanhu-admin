package com.nanhu.admin.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nanhu.admin.utils.ResultVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc:
 */
@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        //1.设置响应编码
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = null;
        if (e instanceof AccountExpiredException) {
            str = "账户过期，登录失败!";
        } else if (e instanceof BadCredentialsException) {
            str = "用户名或密码错误，登录失败!";
        } else if (e instanceof CredentialsExpiredException) {
            str = "密码过期，登录失败!";
        } else if (e instanceof DisabledException) {
            str = "账户被禁用，登录失败!";
        } else if (e instanceof LockedException) {
            str = "账户被锁，登录失败!";
        } else if (e instanceof InternalAuthenticationServiceException) {
            str = "账户不存在，登录失败!";
        } else {
            str = "登录失败!";
        }
        String res = JSONObject.toJSONString(
                new ResultVo(str, 500, null), SerializerFeature.DisableCircularReferenceDetect);
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();

    }

}
