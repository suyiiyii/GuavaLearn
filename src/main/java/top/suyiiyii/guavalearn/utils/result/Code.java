package top.suyiiyii.guavalearn.utils.result;

/*
统一结果集使用的状态码和对应的消息
 */
public enum Code {
    SUCCESS(200, "请求成功"),
    FAIL(400, "失败"),
    FAIL_DUPLICATE_USERNAME(400, "重复的用户名"),
    UNAUTHORIZED(401, "未认证"),
    UNAUTHORIZED_MISS_TOKEN(40101, "未找到token"),
    FORBIDDEN(403, "无权限"),
    FORBIDDEN_USER_HAVE_NO_PERMISSION(40301, "没有执行此操作的权限"),
    NOT_FOUND(404, "未找到"),
    NOT_FOUND_USER(40401, "用户不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    FAIL_WRONG_PASSWORD(400, "密码错误");

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
