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


    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 6, max = 10)
        private String username;
        @NotBlank
        @Size(min = 6, max = 10)
        private String password;
    }

}
