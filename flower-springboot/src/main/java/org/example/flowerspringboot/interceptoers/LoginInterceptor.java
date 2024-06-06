package org.example.flowerspringboot.interceptoers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.flowerspringboot.utils.JwtHelper;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception  {
        //从请求头中获取token
        String token = request.getHeader("Authorization");

        //检查是否有效
        if (token==null) {
            //redis中过期或者不同
            response.setStatus(401);
            return false;
        }
        boolean expiration = jwtHelper.isExpiration(token);
        //有效放行
        if (!expiration){
            //放行，没有过期
            //先把token放进threadLocal
            ThreadLocalUtil.set(jwtHelper.getUserId(token));
            return true;
        }
        response.setStatus(401);
        return false;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
