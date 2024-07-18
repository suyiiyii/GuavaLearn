package top.suyiiyii.guavalearn.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.guavalearn.dto.CommonResponse;

@RestController
public class UserController {

    @PostMapping("/register")
    public CommonResponse register(@Valid @RequestBody RegisterRequest request) {
        System.out.println(request.getUsername());
        return new CommonResponse("注册成功");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        System.out.println(request.grant_type);
        LoginResponse response = new LoginResponse();
        response.setMessage("登录成功");
        return response;
    }


    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 6, max = 10)
        private String username;
        @NotBlank
        @Size(min = 6, max = 10)
        private String password;
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        private String grant_type;
        @NotBlank
        @Size(min = 6, max = 10)
        private String username;
        @NotBlank
        @Size(min = 6, max = 10)
        private String password;
    }

    @Data
    public static class LoginResponse {
        private String access_token;
        private String token_type;
        private String message;
    }

}
