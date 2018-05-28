package com.cimr.api.history.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cimr.api.history.config.HistoryMongoConfig;
import com.cimr.api.history.dao.RealDataSignalHistoryDao;
import com.cimr.api.log.config.LogMongoConfig;
import com.cimr.boot.mongodb.MongoDbBaseFinder;

@Service
public class RealDataSignalHistoryService {

//	@Autowired
//	private HistroyDemoJpa histroyDemoJpa;
//	
//	
//	@Autowired
//	private LogDemoJpa logJap;
//	
//	@Autowired
//	@Qualifier(LogMongoConfig.MONGO_TEMPLATE)
//	private MongoTemplate logTemp;
	

	@Autowired
	private RealDataSignalHistoryDao realDataSignalHistoryDao;
	
	
	
	public List<Map<String,Object>> findAllByTerId(String singal,String terid){
		return realDataSignalHistoryDao.findAllByTerId(singal, terid);
	}
	
	

	
	 
		
}
