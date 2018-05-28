package com.cimr.api.history.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cimr.api.history.config.HistoryMongoConfig;
import com.cimr.boot.mongodb.MongoDbBaseFinder;
import com.cimr.boot.mongodb.dao.MongoDbFindUtil;

@Repository
public class RealDataSignalHistoryDao {
	
	
	private final String COLLECTION_NAME = "REALDATA_SIGNAL_";
	
	
	
	@Autowired
	@Qualifier(HistoryMongoConfig.MONGO_TEMPLATE)
	protected MongoTemplate histroyTemp;
	
	public MongoDbFindUtil mMongoDbFindUtil = new MongoDbFindUtil(histroyTemp);

	public List<Map<String,Object>> findAllBySingal(String singal){

		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);
		
		return finder.findAll(COLLECTION_NAME+singal);
	}
	
	public List<Map<String,Object>> findAllByTerId(String singal,String terid){
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);
		 Criteria criteria = Criteria.where("_id").regex(terid+"*");
		Query query = new Query();
		query.addCriteria(criteria);
		//分页参数
//		final Pageable pageableRequest = new PageRequest(1, 2);
//		query.with(pageableRequest);
		return finder.findAll(query,COLLECTION_NAME+singal);
	}

	
	
}
