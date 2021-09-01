package com.umbc.retail.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umbc.retail.entity.DynamicSqlEntity;

import com.umbc.retail.repository.DynamicSqlRepository;

@Service
public class DynamicSqlDataDaoImpl implements DynamicSqlDataDao {

	@Autowired
	DynamicSqlRepository dynamicSqlRepository;
	
	

	@Override
	public Optional<DynamicSqlEntity> getDynamicSql(Integer sqlId) {
		return dynamicSqlRepository.findById(sqlId);
	}

	
}
