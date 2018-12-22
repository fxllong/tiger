package com.tiger.common.exception;

import com.tiger.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author bystander
 * @date 2018/9/15
 *
 * 自定义异常类
 */
@Getter
public class TigerException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public TigerException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}
