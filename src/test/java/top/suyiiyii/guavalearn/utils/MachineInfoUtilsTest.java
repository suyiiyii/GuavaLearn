package top.suyiiyii.guavalearn.utils;

import com.jcraft.jsch.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.suyiiyii.guavalearn.models.Remote;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MachineInfoUtilsTest {


    Session session;
    @Value("${remote.host}")
    private String host;
    @Value("${remote.port}")
    private int port;
    @Value("${remote.user}")
    private String user;
    @Value("${remote.password}")
    private String password;


    @BeforeEach
    public void setUp() {
        var remote = new Remote();
        remote.setHost(host);
        remote.setPort(port);
        remote.setUser(user);
        remote.setPassword(password);
        session = JschUtils.getSession(remote);
    }

    @Test
    void free() {
        int free = MachineInfoUtils.free(session);
        assert free > 0;
        System.out.println(free);
    }

}