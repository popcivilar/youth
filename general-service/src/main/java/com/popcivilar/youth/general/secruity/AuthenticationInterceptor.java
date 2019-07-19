package com.popcivilar.youth.general.secruity;

import com.popcivilar.youth.general.user.dao.UserInfoMapper;
import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.youthbase.token.TokenPass;
import com.popcivilar.youth.youthbase.token.TokenUtil;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName AuthenticationInterceptor
 * @Description 安全拦截器
 * @Author zhagnch
 * @Date 2019/7/18 18:18
 * @Version 1.0
 **/
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有tokenPass注释，有则跳过认证
        if (method.isAnnotationPresent(TokenPass.class)) {
            TokenPass passToken = method.getAnnotation(TokenPass.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查其他方法有无Token
            // 执行认证
        if (StringUtil.isNullOrEmpty(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = TokenUtil.parseJWT(token).getId();
        } catch (Exception j) {
            throw new RuntimeException("401");
        }
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }


}
