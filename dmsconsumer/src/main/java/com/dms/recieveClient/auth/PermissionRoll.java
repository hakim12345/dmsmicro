package com.dms.recieveClient.auth;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;
import com.dms.recieveClient.model.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PERMISSION_ROLL", schema = SchemaConstant.DMS_SCHEMA)
public class PermissionRoll extends DMSEntity<Long, PermissionRoll> {


    @Id
    @Column(name = "PERMISSIONROLL_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_PERMISSION_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSIONROLL_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_PERMISSION_GEN")
    private Long id;

    @NotNull
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "Permission_Status")
    private String PermissionStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "permissionRoll")
    private List<PermissionPro> PermissionProList = new ArrayList<PermissionPro>();

    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole userRole;

    @Column(name = "User_Role_Type_Id")
    private Long userRoleTypeId;

    public Long getUserRoleTypeId() {
        return userRoleTypeId;
    }

    public void setUserRoleTypeId(Long userRoleTypeId) {
        this.userRoleTypeId = userRoleTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPermissionStatus() {
        return PermissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        PermissionStatus = permissionStatus;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<PermissionPro> getPermissionProList() {
        return PermissionProList;
    }

    public void setPermissionProList(List<PermissionPro> permissionProList) {
        PermissionProList = permissionProList;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

