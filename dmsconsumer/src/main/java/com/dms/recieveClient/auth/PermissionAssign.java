package com.dms.recieveClient.auth;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;
import com.dms.recieveClient.model.UserEntityPackage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERMISSION_ASSIGN", schema = SchemaConstant.DMS_SCHEMA)
public class PermissionAssign extends DMSEntity<Long, PermissionAssign> {
    @Id
    @Column(name = "PERMISSION_ASSIGN_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_PERMISSION_ASSIGN_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSION_ASSIGN_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_PERMISSION_ASSIGN_GEN")
    private Long id;

    @NotNull
    private Boolean canRead = Boolean.FALSE;
    private Boolean canWrite = Boolean.FALSE;
    private Boolean canCreate = Boolean.FALSE;
    private Boolean canRemove = Boolean.FALSE;
    private Boolean canExport = Boolean.FALSE;

    /*
     * private String conditionTerm; private String conditionParams;
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_entity_package_id")
    private UserEntityPackage userEntityPackage;

    public PermissionAssign() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanRead() {
        if (this.canRead == null) {
            return Boolean.FALSE;
        }
        return this.canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanWrite() {
        if (this.canWrite == null) {
            return Boolean.FALSE;
        }
        return this.canWrite;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public Boolean getCanCreate() {
        if (this.canCreate == null) {
            return Boolean.FALSE;
        }
        return this.canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanRemove() {
        if (this.canRemove == null) {
            return Boolean.FALSE;
        }
        return this.canRemove;
    }

    public void setCanRemove(Boolean canRemove) {
        this.canRemove = canRemove;
    }

    public Boolean getCanExport() {
        if (this.canExport == null) {
            return Boolean.FALSE;
        }
        return this.canExport;
    }

    public void setCanExport(Boolean canExport) {
        this.canExport = canExport;
    }

    /*
     * public String getConditionParams() { return this.conditionParams; }
     *
     * public void setConditionParams(String conditionParams) {
     * this.conditionParams = conditionParams; }
     *
     * public String getConditionTerm() { return conditionTerm; }
     *
     * public void setConditionTerm(String conditionTerm) { this.conditionTerm =
     * conditionTerm; }
     */

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public UserEntityPackage getUserEntityPackage() {
        return userEntityPackage;
    }

    public void setUserEntityPackage(UserEntityPackage userEntityPackage) {
        this.userEntityPackage = userEntityPackage;
    }
}