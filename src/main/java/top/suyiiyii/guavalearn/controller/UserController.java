package top.suyiiyii.guavalearn.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.guavalearn.service.UserService;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        System.out.println(request.getUsername());
        userService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @ModelAttribute LoginRequest request) {
        System.out.println(request.grant_type);
        String token = userService.login(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse();
        response.setMessage("登录成功");
        response.setAccess_token(token);
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
