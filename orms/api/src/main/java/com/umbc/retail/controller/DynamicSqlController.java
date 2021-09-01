package com.umbc.retail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umbc.retail.service.DynamicSqlService;

@CrossOrigin
@RestController
public class DynamicSqlController {
	/*
	 * 
	 */
	@Autowired
	DynamicSqlService dynamicSqlService;
	


	@RequestMapping(value = "/dynamicsql/{sqlId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveAllCustomersById(
    		@PathVariable(name="sqlId") Integer sqlId) throws Exception{
        return new ResponseEntity<Object> (dynamicSqlService.runDynamicSql(sqlId),HttpStatus.OK);
    }
	

	
	
	
	
}
