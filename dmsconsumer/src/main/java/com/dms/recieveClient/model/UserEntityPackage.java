package com.dms.recieveClient.model;

import com.dms.recieveClient.auth.PermissionAssign;
import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_entitypackage", schema = SchemaConstant.DMS_SCHEMA)
@NamedNativeQueries({@NamedNativeQuery(
        name = "getUserEntityPackage",
        query = "select * from user_entitypackage where user_entitypackage.id NOT IN (select m.userEntityPackage_id from menu m where m.userEntityPackage_id IS NOT NULL)",
        resultClass = UserEntityPackage.class
)})
public class UserEntityPackage extends DMSEntity<Long, UserEntityPackage> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USERENTITYPACKAGE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "entityname", length=100)
    private String entityname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userpackage_id", nullable = false)
    private UserPackage userPackage;

    @Transient
    private PermissionAssign permissionAssign;

    @Column(name = "type")
    private boolean type;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityname() {
        return entityname;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname;
    }

    public UserPackage getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(UserPackage userPackage) {
        this.userPackage = userPackage;
    }

    public PermissionAssign getPermissionAssign() {
        return permissionAssign;
    }

    public void setPermissionAssign(PermissionAssign permissionAssign) {
        this.permissionAssign = permissionAssign;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}