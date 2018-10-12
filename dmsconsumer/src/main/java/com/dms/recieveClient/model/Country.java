package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country", schema = SchemaConstant.DMS_SCHEMA)
public class Country extends DMSEntity<Long, Country> {

    /**
     *
     */
    private static final long serialVersionUID = 3416993059455918148L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="country_name",length=100)
    private String countryName;

    public Country(){
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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