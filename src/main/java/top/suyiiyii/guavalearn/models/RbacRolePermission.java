package top.suyiiyii.guavalearn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RbacRolePermission {
    int id;
    int RoleId;
    int permissionId;
}
