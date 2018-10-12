package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city", schema = SchemaConstant.DMS_SCHEMA)
public class City extends DMSEntity<Long, City> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public City() {
    }

    /**
     * serialVersionUID.
     */




    @Id
    @Column(name = "id", unique = true, nullable = false)

    private Long id;

    @Column(name = "city_name",nullable = false,length =100)
    private String cityName;

    @Column(name = "city_code", nullable = false, length = 10)
    private String cityCode;

    @Column(name = "state_id",nullable = false)
    private Long stateID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getStateID() {
        return stateID;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }
}