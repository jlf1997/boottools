package com.cimr.api.history.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimr.api.history.dao.RealdataSignalHistoryDao;

@Service
public class RealDataSignalHistoryService {


	@Autowired
	protected RealdataSignalHistoryDao realdataSignalHistoryDao;
	
	/**
	 * 通过信号id 以及 终端编号查询对应历史记录
	 * @param singal
	 * @param terid
	 * @return
	 */
	public List<Map<String,Object>> findAllByTerId(String singal,String terid){
		return realdataSignalHistoryDao.findAllByTerId(singal, terid);
	}
	
	/**
	 * 通过信号id 开始时间 结束时间 查询对应历史记录
	 * @param singal
	 * @param beg
	 * @param beg
	 * @return
	 */
	public List<Map<String,Object>> findAllByTime(String singal,Long beg,Long end){
		return realdataSignalHistoryDao.findAllByTime(singal, beg, end);
	} 
	
	
	/**
	 * 通过信号查询对应历史数据
	 * @param singal
	 * @return
	 */
	public List<Map<String,Object>> findAllBySingal(String singal){
		return realdataSignalHistoryDao.findAllBySingal(singal);
	}
	
	

	
	 
		
}
