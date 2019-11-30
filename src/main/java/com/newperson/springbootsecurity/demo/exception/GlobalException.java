package com.newperson.springbootsecurity.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/27 9:59
 */
@Data
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private String message;
    private Integer code;
    private Object data;
}
