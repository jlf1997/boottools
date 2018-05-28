package com.cimr.api.code.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cimr.api.comm.model.TerimalModel;


@Repository
public class TerRealDataDao {
	
	
	
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate ;
    
	/**
	 * 
	 * @param ids
	 * @return
	 */
    public List<String> getInfosByTerIds(List<TerimalModel> ters){
    	List<String> res = new ArrayList<>();
    	for(TerimalModel ter:ters) {
    		String out = redisTemplate.opsForValue().get("NEW_DATA_2:"+ter.getTerId());
    		if(out!=null) {
    			res.add(out);
    		}
    	}    	
    	return res;
    }
}
