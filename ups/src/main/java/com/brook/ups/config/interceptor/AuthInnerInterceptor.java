package com.brook.ups.config.interceptor;

import com.brook.ups.common.constant.ConfigConstant;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.TokenType;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.token.AccountToken;
import com.brook.ups.utils.encryption.TokenEncryptUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 内部鉴权拦截器
 */
public class AuthInnerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getHeader(ConfigConstant.X_TOKEN);
        if(token==null ||"".equals(token)){
            throw new UpsException(ExceptionCode.AU0002,"您的身份认证已失效，请重新登录");
        }
        AccountToken at = null;
        try {
            at = TokenEncryptUtil.parse(token);
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.AU0001,"解析身份认证失败",e);
        }
        if(at!=null ){
            if(at.getType()==null || TokenType.INNER!=at.getType()){
                throw new UpsException(ExceptionCode.AU0002,"不是有效的内部专用身份认证");
            }
            if(at.getAppId()==null || at.getAcId()==null || at.getDate()==null){
                throw new UpsException(ExceptionCode.AU0002,"您的身份认证缺少必要信息，身份认证无效");
            }
            if(new Date().getTime()-at.getDate().getTime()>=ConfigConstant.EXPIRATION_TIME){
                throw new UpsException(ExceptionCode.AU0002,"您的身份认证已过期无效，请重新登录");
            }
        }else{
            throw new UpsException(ExceptionCode.AU0002,"您的身份认证无效");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}

}