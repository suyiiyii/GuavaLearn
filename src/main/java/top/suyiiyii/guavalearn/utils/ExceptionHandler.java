package top.suyiiyii.guavalearn.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import top.suyiiyii.guavalearn.controller.exception.BusinessException;
import top.suyiiyii.guavalearn.result.Code;
import top.suyiiyii.guavalearn.result.Result;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("Exception: ", e);
//        e.printStackTrace();
        if (e instanceof BusinessException businessException) {
            return ResponseEntity.ok(new Result<>(businessException.getCode(), null));
        }
        if (e instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return ResponseEntity.ok(new Result<>(400, methodArgumentNotValidException.getMessage()));
        }
        return ResponseEntity.ok(new Result<>(Code.FAIL, null));
    }
}
