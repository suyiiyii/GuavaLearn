package top.suyiiyii.guavalearn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    int id;
    String username;
    String hashedPassword;
    String email;
    String phone;
}
