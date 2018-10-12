package com.dms.recieveClient.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Transactional
public abstract class BaseAbstract {


    public void addSuccess(Map<String,Object> resMap){
        resMap.put("status", true);
    }

    public ResponseEntity<Map<String, Object>> renderResponse(Map<String,Object> resMap) {
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }

}
