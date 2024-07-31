package top.suyiiyii.guavalearn.models;


import lombok.Data;


@Data
public class Remote {
    private int port = 22;
    private String identity;
    private String host;
    private String user;
    private String password;
    private String passphrase;
}