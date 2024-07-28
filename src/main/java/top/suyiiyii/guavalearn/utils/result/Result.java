package top.suyiiyii.guavalearn.utils.result;

import lombok.Data;

/*
统一结果集
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Code code) {
        this.code = code.code();
        this.message = code.message();
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(Code code, T data) {
        this.code = code.code();
        this.message = code.message();
        this.data = data;
    }

}
