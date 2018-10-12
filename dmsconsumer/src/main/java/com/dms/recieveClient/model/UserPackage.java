package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_package", schema = SchemaConstant.DMS_SCHEMA)
public class UserPackage extends DMSEntity<Long,UserPackage> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USERPACKAGE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "packagename", length = 100)
    private String packagename;

    @Column(name = "packagepath", length = 100)
    private String packagepath;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntityPackage> listOfuserEntityPackage = new ArrayList<UserEntityPackage>();


    public List<UserEntityPackage> getListOfuserEntityPackage() {
        return listOfuserEntityPackage;
    }

    public void setListOfuserEntityPackage(List<UserEntityPackage> listOfuserEntityPackage) {
        this.listOfuserEntityPackage = listOfuserEntityPackage;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getPackagepath() {
        return packagepath;
    }

    public void setPackagepath(String packagepath) {
        this.packagepath = packagepath;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }
}