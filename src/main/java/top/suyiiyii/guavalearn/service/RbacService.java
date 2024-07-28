package top.suyiiyii.guavalearn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.suyiiyii.guavalearn.mapper.RbacMapper;

import java.util.List;

@Slf4j
@Service
public class RbacService {

    RbacMapper rbacMapper;

    public RbacService(RbacMapper rbacMapper) {
        this.rbacMapper = rbacMapper;
    }

    public boolean checkPermission(String username, String permissionName) {
        log.info("Checking permission for user: " + username + ", permission: " + permissionName);
        List<Integer> roleIds = rbacMapper.getRoleIdsByUsername(username);
        roleIds.add(1);
        for (int roleId : roleIds) {
            int[] permissionIds = rbacMapper.getPermissionIdsByRoleId(roleId);
            for (int permissionId : permissionIds) {
                if (permissionName.equals(rbacMapper.getPermissionNameById(permissionId))) {
                    return true;
                }
            }
        }
        return false;
    }

}
