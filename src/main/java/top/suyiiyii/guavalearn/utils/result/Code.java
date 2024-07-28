package top.suyiiyii.guavalearn.utils.result;
/*
统一结果集使用的状态码和对应的消息
 */
public enum Code {
    SUCCESS(200, "请求成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证"),
    NOT_FOUND(404, "未找到"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
