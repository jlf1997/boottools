package com.cimr.api.code.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.stereotype.Repository;

import com.cimr.api.code.model.Terminal_1_Info;
import com.cimr.api.comm.model.TerimalModel;
import com.cimr.boot.redis.RedisTemplateConfig;
import com.cimr.boot.redis.utils.MyHashMapper;


@Repository
public class TerRealDataDao {
	
	
	
	
	@Autowired
    private RedisTemplate<String, HashMap> redisTemplate ;
	
	@Autowired
    private HashMapper mapper;
    
	 
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
    public List<HashMap> getInfosByTerIds(List<TerimalModel> ters){
    	List<HashMap> res = new ArrayList<>();
    	for(TerimalModel ter:ters) {
    		HashMap out = redisTemplate.opsForValue().get("NEW_DATA_2:"+ter.getTerId());
    		if(out!=null) {
    			res.add(out);
    		}
    	}    	
    	return res;
    }

    
    /**
     * 
     * @param termimals
     * @return
     */
	public List<Terminal_1_Info> getLocationInfosByTerIds(List<TerimalModel> termimals) {
		// TODO Auto-generated method stub
		List<HashMap> res = new ArrayList<>();
    	for(TerimalModel ter:termimals) {
    		
    		
    		HashMap out = redisTemplate.opsForValue().get("NEW_DATA_1:"+ter.getTerId());
    		if(out!=null) {
    			res.add(out);
    		}
    	}    	
    	List<Terminal_1_Info> infos = new ArrayList<>();
    	res.forEach(action->{
    		infos.add(new Terminal_1_Info(action));
    	});
    	return infos;
	}
	
	
}
