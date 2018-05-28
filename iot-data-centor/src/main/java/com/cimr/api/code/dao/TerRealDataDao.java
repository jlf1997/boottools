package com.cimr.api.code.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TerRealDataDao {
	
	
	
	
	@Autowired
    private RedisTemplate<String, String> redisTemplate ;
    
    public List<String> getInfosByTerIds(String ids){
    	String[] idsStr = ids.split(",");
    	List<String> res = new ArrayList<>();
    	for(String id:idsStr) {
    		res.add(redisTemplate.opsForValue().get("NEW_DATA_2:"+id));
    	}
    	
    	return res;
    }
}
