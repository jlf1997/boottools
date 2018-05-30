package com.cimr.api.history.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cimr.api.code.model.Terminal_1_Info;
import com.cimr.api.history.dao.RealdataSignalHistoryDao;
import com.cimr.api.history.model.Terminal_1_Info_History;

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
	
	/**
	 * 通过信号id 开始时间 结束时间 查询对应历史记录
	 * 获取特点的位置信息
	 * @param termialId 终端id
	 * @param beg 开始时间
	 * @param end 结束时间
	 * @return
	 */
	public List<Terminal_1_Info_History> findAllLocationByTime(String termialId,Long beg,Long end){
		
		//TODO 转换获取位置信息
		List<Map<String,Object>> res =  realdataSignalHistoryDao.findLocationByTerminalId(termialId,beg,end);
		//选择对应的dao  
		List<Terminal_1_Info_History> list = new ArrayList<>();
		res.forEach(action->{
			Terminal_1_Info_History info = new Terminal_1_Info_History();
			info.setLocation(action);
			info.setTime(action);
			info.setTerminalId(action);
			list.add(info);
		});
		return list;
	} 
	
	 
		
}
