package top.suyiiyii.guavalearn.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.suyiiyii.guavalearn.models.Remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j
public class JschUtils {

    /**
     * 根据主机信息，创建一个会话
     *
     * @param remote 主机信息
     * @return 会话
     */
    public static Session getSession(Remote remote) {
        try {
            JSch jSch = new JSch();
            if (remote.getIdentity() != null && Files.exists(Paths.get(remote.getIdentity()))) {
                log.info("Using identity file: " + remote.getIdentity());
                jSch.addIdentity(remote.getIdentity(), "");
            }
            Session session = jSch.getSession(remote.getUser(), remote.getHost(), remote.getPort());
            session.setPassword(remote.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            return session;
        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建一个shell环境，返回输入输出流
     *
     * @param session 会话
     * @return 输入输出流
     */
    public static Map.Entry<InputStream, OutputStream> getShellStream(Session session) {
        try {
            var channel = session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
            return Map.entry(channel.getInputStream(), channel.getOutputStream());
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static CommandExec getCommandExec(Session session, String command) {
        try {
            var channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            StringBuilder output = new StringBuilder();

            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    output.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    int exitStatus = channel.getExitStatus();
                    return new CommandExec(command, exitStatus, output.toString());
                }
                try {
                    Thread.sleep(100);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws JSchException, IOException {
        var remote = new Remote();
//        remote.setHost("99.suyiiyii.top");
        remote.setHost("10.21.22.51");
        remote.setPort(2222);
        remote.setUser("root");
//        remote.setIdentity("C:/Users/suyiiyii/.ssh/id_rsa.pem");
        remote.setPassword("rootSuyiiiiyyiiiiiiiiyyyy");
        var session = JschUtils.getSession(remote);
        session.connect();
//        var channel = session.openChannel("shell");
//        channel.setInputStream(System.in);
//        channel.setOutputStream(System.out);
//        channel.connect();
        var channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand("ps -ef");
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();
        byte[] tmp = new byte[1024];

        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    /**
     * 命令执行对象
     * 保存执行命令的参数和结果（stdout & stderr）
     */
    @Data
    @AllArgsConstructor
    public static class CommandExec {
        String command;
        int exitStatus;
        String output;
    }
}
