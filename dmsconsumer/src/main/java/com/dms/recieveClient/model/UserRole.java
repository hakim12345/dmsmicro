package com.dms.recieveClient.model;

import com.dms.recieveClient.auth.Permission;
import com.dms.recieveClient.auth.PermissionRoll;
import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_role", schema = SchemaConstant.DMS_SCHEMA)
public class UserRole extends DMSEntity<Long, UserRole> {

    /**
     *
     */
    private static final long serialVersionUID = 1746670176577489041L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_ROLE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "role", unique = true, nullable = false, length = 45)
    private String role;

    @Column(name = "description")
    @Lob
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Permission> permissions;

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE})
	@Column(name = "menu_id")
	private Set<Menu> menus;*/

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userRole")
    private List<PermissionRoll> permissionRoll = new ArrayList<PermissionRoll>();

    public UserRole() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PermissionRoll> getPermissionRoll() {
        return permissionRoll;
    }

    public void setPermissionRoll(List<PermissionRoll> permissionRoll) {
        this.permissionRoll = permissionRoll;
    }

	/*public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}*/

}