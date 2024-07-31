package top.suyiiyii.guavalearn.utils;

import com.jcraft.jsch.Session;

public class MachineInfoUtils {
    public static int free(Session session) {
        var result = JschUtils.getCommandExec(session, "free | awk '/Mem:/{print $2}'");
        return Integer.parseInt(result.getOutput().trim());
    }
}
