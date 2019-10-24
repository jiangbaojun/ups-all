package com.brook.ups.config.exceptionhandle;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.ExceptionLevel;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常捕获
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * UPS异常处理
     */
    @ExceptionHandler(UpsException.class)
    @ResponseBody
    public Map<String,Object> illegalPropExceptionHandler(HttpServletRequest request, UpsException exception) throws Exception {
        ErrorMessage em = new ErrorMessage();
        ExceptionCode code = exception.getCode();
        if(code==null){
            code = ExceptionCode.C99999;
        }
        //出现无效token异常，跳转到登录页
        if(code==ExceptionCode.AU0002 || code==ExceptionCode.AU0003){
            em.setRedirectLogin(1);
        }
        em.setLevel(exception.getLevel().getValue());
        em.setCode(code.name());
        em.setDescription(exception.getMessage());
        //记录日志
        if(exception.getLevel() == ExceptionLevel.WARN){
            logger.warn("捕获到Ups异常警告,"+exception.getExceptionInfo(),exception);
        }else{
            logger.error("捕获到Ups异常错误,"+exception.getExceptionInfo(),exception);
        }
        return exceptionMessage(em);
    }

    /**
     * 系统异常处理
     * 此处是最终异常捕获，情况较为严重
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        ErrorMessage em = new ErrorMessage();
        em.setCode(ExceptionCode.S99999.name());
        em.setLevel(ExceptionLevel.ERROR.getValue());
        em.setDescription(exception.getMessage());
        //记录日志
        logger.error("捕获到系统异常错误",exception);
        return exceptionMessage(em);
    }
}
