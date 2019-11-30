package com.newperson.springbootsecurity.demo.exception;

import com.newperson.springbootsecurity.demo.utils.JsonData;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/25 18:42
 */
@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonData handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return JsonData.buildError(fieldError.getDefaultMessage(),400);
    }

    @ExceptionHandler(GlobalException.class)
    public JsonData handleGlobalException(GlobalException e) {
        return JsonData.buildError(e.getMessage(),e.getCode());
    }

}
