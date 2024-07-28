package top.suyiiyii.guavalearn.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import top.suyiiyii.guavalearn.utils.exception.BusinessException;
import top.suyiiyii.guavalearn.utils.result.Code;
import top.suyiiyii.guavalearn.utils.result.Result;

import java.lang.annotation.Annotation;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("Exception: ", e);
        if (e instanceof BusinessException businessException){
            return ResponseEntity.ok(new Result<>(businessException.getCode(), null));
        }
        return ResponseEntity.ok(new Result<>(Code.FAIL, null));
    }
}
