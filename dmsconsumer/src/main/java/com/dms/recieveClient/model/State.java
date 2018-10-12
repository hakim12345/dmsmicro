package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;

@Entity
@Table(name = "state", schema = SchemaConstant.DMS_SCHEMA)
public class State extends DMSEntity<Long, State> {

    public State() {
    }

    /**
     *
     */
    private static final long serialVersionUID = -1741746803848267673L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "state_name", length=100)
    private String stateName;

    @Column(name = "country_id")
    private Long countryId;

    @Transient
    private Country country;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}

