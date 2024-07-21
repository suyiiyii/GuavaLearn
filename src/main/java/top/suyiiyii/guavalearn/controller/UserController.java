package top.suyiiyii.guavalearn.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.guavalearn.dto.CommonResponse;
import top.suyiiyii.guavalearn.utils.JwtUtils;

@RestController
public class UserController {

    @Value("${jwt.secret}")
    private String secret;
    @PostMapping("/register")
    public CommonResponse register(@Valid @RequestBody RegisterRequest request) {
        System.out.println(request.getUsername());
        return new CommonResponse("注册成功");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @ModelAttribute LoginRequest request) {
        System.out.println(request.grant_type);
        LoginResponse response = new LoginResponse();
        response.setMessage("登录成功");
        response.setAccess_token(JwtUtils.generateToken(request.getUsername(), secret));
        response.setToken_type("Bearer");
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
