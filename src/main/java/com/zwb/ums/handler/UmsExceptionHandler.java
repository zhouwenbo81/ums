package com.zwb.ums.handler;

import com.zwb.ums.exception.ResponseBankException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * Title: ExceptionHandler
 * </p>
 * <p>
 * Description: 自定义异常捕获器
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/6 0:18
 */
@ControllerAdvice
public class UmsExceptionHandler {

    /**
     * 异常捕捉 返回403错误
     */
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException() {

    }
}
