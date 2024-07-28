package top.suyiiyii.guavalearn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.suyiiyii.guavalearn.dto.JwtData;
import top.suyiiyii.guavalearn.mapper.RbacMapper;
import top.suyiiyii.guavalearn.mapper.UserMapper;
import top.suyiiyii.guavalearn.models.User;
import top.suyiiyii.guavalearn.utils.JwtUtils;
import top.suyiiyii.guavalearn.utils.exception.BusinessException;
import top.suyiiyii.guavalearn.utils.result.Code;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class UserService {

    private final RbacMapper rbacMapper;
    UserMapper userMapper;
    ObjectMapper objectMapper = new ObjectMapper();
    @Value("${jwt.secret}")
    private String secret;

    public UserService(UserMapper userMapper, RbacMapper rbacMapper) {
        this.userMapper = userMapper;
        this.rbacMapper = rbacMapper;
    }

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void addUser(User user) {
        try {
            userMapper.addUser(user);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new BusinessException(Code.FAIL_DUPLICATE_USERNAME);
        }
    }

    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setHashedPassword(sha256(password));
        addUser(user);
        rbacMapper.addUserRole(username, 1);
    }

    public String login(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new BusinessException(Code.NOT_FOUND_USER);
        }
        if (!user.getHashedPassword().equals(sha256(password))) {
            throw new BusinessException(Code.FAIL_WRONG_PASSWORD);
        }
        JwtData jwtData = new JwtData();
        jwtData.setUsername(username);
        String jwtData_str;
        try {
            jwtData_str = objectMapper.writeValueAsString(jwtData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return JwtUtils.generateToken(jwtData_str, secret);
    }
}
