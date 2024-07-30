package top.suyiiyii.guavalearn.controller.exception;

import top.suyiiyii.guavalearn.result.Code;

/*
业务异常基类
 */
public class BusinessException extends RuntimeException {
    private Code code;

    public BusinessException(Code code) {
        this.code = code;
    }

    public static BusinessException of(Code code) {
        return new BusinessException(code);
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                '}';
    }
}
