package com.dms.recieveClient.service.impl;

import com.dms.recieveClient.generic.DMSEntityServiceImpl;
import com.dms.recieveClient.model.TravelOperator;
import com.dms.recieveClient.repository.TenantRepository;
import com.dms.recieveClient.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("tenantServices")
public class TenantServiceImpl extends DMSEntityServiceImpl<Long, TravelOperator> implements TenantService {


    private TenantRepository tenantRepository;

    public TenantServiceImpl() {

    }

    public TenantServiceImpl(TenantRepository tenantRepository) {
        super(tenantRepository);
        this.tenantRepository = tenantRepository;
    }
}
