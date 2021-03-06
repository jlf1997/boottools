package com.cimr.api.history.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cimr.api.history.config.HistoryMongoConfig;
import com.cimr.boot.mongodb.MongoDbBaseFinder;
import com.cimr.util.MapResultUtils;


@Repository
public class RealdataSignalHistoryDao {
	/**
	 * 
	 */
	private final String COLLECTION_NAME = "REALDATA_SIGNAL_";
	
	private final String COLLECTION_NAME_LOCATION = "LocationTest";
	
	@Autowired
	@Qualifier(HistoryMongoConfig.MONGO_TEMPLATE)
	protected MongoTemplate histroyTemp;
	
	/**
	 * 通过信号id 以及 终端编号查询对应历史记录
	 * @param singal
	 * @param terid
	 * @return
	 */
	public List<Map<String,Object>> findAllByTerId(String singal,String terid){
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);
		Query query = new Query();
		if(terid!=null || "".equals(terid)) {
			Criteria criteria = Criteria.where("_id").regex(terid+"*");
			query.addCriteria(criteria);
		}
		return finder.findAll(query,COLLECTION_NAME+singal);
	}
	
	/**
	 * 通过信号id 开始时间 结束时间 查询对应历史记录
	 * @param singal
	 * @param beg
	 * @param beg
	 * @return
	 */
	public List<Map<String,Object>> findAllByTime(String singal,Long beg,Long end){
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);
		Query query = new Query();
		Criteria criteria = Criteria.where("gatherMsgTime").gte(beg).lte(end);
		query.addCriteria(criteria);
		return finder.findAll(query,COLLECTION_NAME+singal);
	} 
	
	
	/**
	 * 通过信号查询对应历史数据
	 * @param singal
	 * @return
	 */
	public List<Map<String,Object>> findAllBySingal(String singal){
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);		
		return finder.findAll(COLLECTION_NAME+singal);
	}
	
	
	/**
	 * 
	 * @param terminalId
	 * @param beg
	 * @param end
	 * @return
	 */
	public List<Map<String,Object>> findLocationByTerminalId(String terminalId,Long beg,Long end){
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);	
		Query query = new Query();
		Criteria criteria = Criteria.where("gatherMsgTime").gte(beg).lte(end);
		query.addCriteria(criteria);
		//按照时间排序
		query.with(new Sort(Sort.Direction.ASC, "gatherMsgTime"));
		return finder.findAll(query,COLLECTION_NAME_LOCATION);
	}

	public List<Map<String, Object>> findAllDataByTimeAndSingal(String singal, String terid, Long beg, Long end,String[] sortBy,String sortType,int type,String... fields) {
		MongoDbBaseFinder finder = new MongoDbBaseFinder(histroyTemp);	
		Query query = new Query();
		Criteria criteriaTime =null;
		if(terid!=null || "".equals(terid)) {
			Criteria criteria = Criteria.where("_id").regex(terid+"*");
			query.addCriteria(criteria);
		}
		
//		criteriaTime = Criteria.where("_id").regex("^"+terid+"_"+);
		//时间查询
		if(beg==null || beg<0) {
			beg = 0L;
		}
		
		criteriaTime = Criteria.where("gatherMsgTime").gte(new Date(beg));
		if(end!=null && end>0) {
			criteriaTime.lte(new Date(end));
		}	
		if(criteriaTime!=null) {
			query.addCriteria(criteriaTime);
		}
		
		//按照时间排序
		if(sortBy!=null && sortBy.length>0) {
			Direction direction = null;
			if("DESC".equals(sortType)) {
				direction = Sort.Direction.DESC;
			}else {
				direction = Sort.Direction.ASC;
			}
			query.with(new Sort(direction, sortBy));
		}
		
		List<Map<String, Object>> res = finder.findAll(query,COLLECTION_NAME+singal);
		List<Map<String, Object>> out = new ArrayList<>();
		res.forEach(action->{
			out.add(MapResultUtils.getList(action, fields, type));
		});
		
		return out;
		
		
	}
}
