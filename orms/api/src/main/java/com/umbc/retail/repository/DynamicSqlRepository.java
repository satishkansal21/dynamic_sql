package com.umbc.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umbc.retail.entity.DynamicSqlEntity;

@Repository
public interface DynamicSqlRepository extends JpaRepository<DynamicSqlEntity, Integer> {

	
			
	
}
