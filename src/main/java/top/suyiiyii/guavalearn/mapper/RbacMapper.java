package top.suyiiyii.guavalearn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RbacMapper {

    @Select("select permission_id from rbac_role_permission where role_id = #{roleId}")
    int[] getPermissionIdsByRoleId(int roleId);

    @Select("select role_id from rbac_user_role where username = #{username}")
    List<Integer> getRoleIdsByUsername(String username);

    @Select("select permission_name from rbac_permission where id = #{permissionId}")
    String getPermissionNameById(int permissionId);

    @Insert("insert into rbac_user_role (username, role_id) values (#{username}, #{roleId})")
    void addUserRole(String username, int roleId);

    @Insert("insert into rbac_role_permission (roleId, permission_id) values (#{roleId}, #{permissionId})")
    void addRolePermission(int roleId, int permissionId);

}
