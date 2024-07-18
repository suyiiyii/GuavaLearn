package top.suyiiyii.guavalearn.dto;

import lombok.Data;

@Data
public class CommonResponse {
    private String message;

    public CommonResponse(String message) {
        this.message = message;
    }
}
