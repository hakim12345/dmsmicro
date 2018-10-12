package com.dms.recieveClient.repository;

import com.dms.recieveClient.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

}