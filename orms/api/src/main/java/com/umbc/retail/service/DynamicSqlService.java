package com.umbc.retail.service;

import java.util.List;

import com.umbc.retail.entity.DynamicSqlEntity;


public interface DynamicSqlService {

	Object runDynamicSql(int sqlId) throws Exception;

}
