package com.umbc.retail.dao;

import java.util.List;
import java.util.Optional;

import com.umbc.retail.entity.DynamicSqlEntity;


public interface DynamicSqlDataDao {

	
	Optional<DynamicSqlEntity> getDynamicSql(Integer sqlId);


}
