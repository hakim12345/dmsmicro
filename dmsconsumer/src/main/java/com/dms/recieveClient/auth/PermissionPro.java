package com.dms.recieveClient.auth;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;

@Entity
@Table(name = "permission_pro", schema = SchemaConstant.DMS_SCHEMA)
public class PermissionPro extends DMSEntity<Long, PermissionPro> {

    @Id
    @Column(name = "PERMISSIONPRO_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_PERMISSION_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSIONPRO_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_PERMISSION_GEN")
    private Long id;

	/*@Column(name = "Permission_Roll_Ids")
    private Long PermissionRollIds;*/

    @Column(name = "EntityId")
    private Long entityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Permission_Roll_Ids", nullable = false)
    private PermissionRoll permissionRoll;

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_Roll_Ids",nullable = false)
	private UserRole userRole;*/

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public PermissionRoll getPermissionRoll() {
        return permissionRoll;
    }

    public void setPermissionRoll(PermissionRoll permissionRoll) {
        this.permissionRoll = permissionRoll;
    }

    /*public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
*/
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

	/*
	public Long getPermissionRollIds() {
		return PermissionRollIds;
	}

	public void setPermissionRollIds(Long permissionRollIds) {
		PermissionRollIds = permissionRollIds;
	}*/


    public PermissionRoll getpermissionRoll() {
        return permissionRoll;
    }

    public Long getentityId() {
        return entityId;
    }

    public void setentityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setpermissionRoll(PermissionRoll permissionRoll) {
        this.permissionRoll = permissionRoll;
    }
}

