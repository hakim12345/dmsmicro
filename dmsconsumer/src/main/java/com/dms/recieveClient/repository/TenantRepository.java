package com.dms.recieveClient.repository;

import com.dms.recieveClient.model.TravelOperator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TravelOperator, Long> {

}
