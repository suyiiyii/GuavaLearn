package top.suyiiyii.guavalearn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RbacPermission {
    int id;
    String permissionName;
    String permissionDescription;
}
