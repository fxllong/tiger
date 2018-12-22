package com.tiger.common.advice;

import com.tiger.common.exception.TigerException;
import com.tiger.common.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @date 2018/9/15
 *
 * 自定义异常处理
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(TigerException.class)
    public ResponseEntity<ExceptionResult> handleException(TigerException e) {
        return ResponseEntity.status(e.getExceptionEnum().value())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
