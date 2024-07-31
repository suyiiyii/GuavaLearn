package top.suyiiyii.guavalearn.utils;

import com.jcraft.jsch.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.suyiiyii.guavalearn.models.Remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SpringBootTest
class JschUtilsTest {


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
    void getSession() {
    }

    @Test
    void getShellStream() throws IOException {
        var r = JschUtils.getShellStream(session);
        InputStream in = r.getKey();
        assert in != null;
        OutputStream out = r.getValue();
        assert out != null;

//        out.write("date".getBytes());
//        out.flush();
//
//        byte[] buffer = new byte[1024];
//        StringBuilder sb = new StringBuilder();
//        int len;
//        while ((len = in.read(buffer)) != -1) {
//            sb.append(new String(buffer, 0, len));
//            if (sb.toString().contains("UTC")) {
//                break;
//            }
//        }
    }

    @Test
    void getCommandExec() {
        var command = JschUtils.getCommandExec(session, "date");
        assert command.getCommand().equals("date");
        assert command.getOutput().contains("UTC");
    }
}