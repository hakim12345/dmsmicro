package com.dms.recieveClient.auth;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERMISSION", schema= SchemaConstant.DMS_SCHEMA)
public class Permission extends DMSEntity<Long, Permission> {
    @Id
    @Column(name = "PERMISSION_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_PERMISSION_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_PERMISSION_GEN")
    private Long id;
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "permission")
    private List<PermissionAssign> listOfPermissionAssign = new ArrayList<PermissionAssign>();


    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionAssign> getListOfPermissionAssign() {
        return listOfPermissionAssign;
    }

    public void setListOfPermissionAssign(List<PermissionAssign> listOfPermissionAssign) {
        this.listOfPermissionAssign = listOfPermissionAssign;
    }
}
