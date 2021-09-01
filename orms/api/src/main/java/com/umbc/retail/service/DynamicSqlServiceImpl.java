package com.umbc.retail.service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umbc.retail.dao.DynamicSqlDataDao;
import com.umbc.retail.entity.DynamicSqlEntity;

@Service
public class DynamicSqlServiceImpl implements DynamicSqlService {

	@Autowired
	DynamicSqlDataDao dynamicSqlDataDao;
	
	 
	@PersistenceContext(unitName ="EntityManager")
	private EntityManager dynamicEntityManager;
	
	@Override
	public Object runDynamicSql(int sqlId) throws Exception {
		List<String> columnList =null;
		List<Object> Obj = null;
		Integer customerId =1;
		Map<String,String> defaultDataElements = new HashMap<>();
		try {
	
			Optional<DynamicSqlEntity> dynamicSqlEntity = dynamicSqlDataDao.getDynamicSql(sqlId);
			
			if(null==dynamicSqlEntity) {
				
			}else {
				String sqlColumns = dynamicSqlEntity.get().getSqlColumns();
				String sqlStatement = dynamicSqlEntity.get().getSqlStatement();
				Integer numberOfParameters = dynamicSqlEntity.get().getNumberOfParameters();
			
				columnList = Stream.of(sqlColumns.split(",")).collect(Collectors.toList());
			
				
				
				if(numberOfParameters==0) {
					Obj = getDynamicSqlData(sqlStatement);
				}else if(numberOfParameters==1) {
					Obj = getDynamicSqlData(sqlStatement,customerId);
				}
			}
			
			// Construct the Response Json Object
			for (int i = 0; i < columnList.size(); i++) {
				// System.out.println(" The COLUMN NAME :: "+ colList.get(i) );
				// System.out.println(" The COLUMN VALUE :: "+ Obj.get(0));
				if(Obj==null || Obj.size()==0) {
					defaultDataElements.put(columnList.get(i), "");
				}else {
					if(columnList.size()==1) {
						if (Obj.get(0)==null) {
							defaultDataElements.put(columnList.get(i), " ");
						}else if(Obj.get(0).toString().equalsIgnoreCase(".00")){
							defaultDataElements.put(columnList.get(i), "0.00");
						}else {
							defaultDataElements.put(columnList.get(i), Obj.get(0).toString());	
						}
					}else {
						if(Array.get(Obj.get(0), i)==null) {
							defaultDataElements.put(columnList.get(i), " ");
						}else if(Array.get(Obj.get(0), i).toString().equalsIgnoreCase(".00")) {
							defaultDataElements.put(columnList.get(i), "0.00");
						}else {
							defaultDataElements.put(columnList.get(i), Array.get(Obj.get(0), i).toString());
						}
					}
				}
			}

			
	
			int sat;
			sat=1;
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return defaultDataElements ;
	}

	
	private List<Object> getDynamicSqlData(String sqlStatement ){
		Query query = dynamicEntityManager.createNativeQuery(sqlStatement);
		return query.getResultList();
	}
	
	private List<Object> getDynamicSqlData(String sqlStatement,Integer parm1 ){
		Query query = dynamicEntityManager.createNativeQuery(sqlStatement).setParameter(1, parm1);
		
		return query.getResultList();
	}
	
	
}
