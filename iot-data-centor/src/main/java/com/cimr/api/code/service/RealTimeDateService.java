package com.cimr.api.code.service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cimr.api.code.dao.TerRealDataDao;
import com.cimr.api.code.service.configs.MessageHandle;
import com.cimr.api.comm.model.TerimalModel;

@Service
public class RealTimeDateService {
	
	private static final Logger log = LoggerFactory.getLogger(RealTimeDateService.class);

	
	
	
	@Autowired
	private TerRealDataDao terRealDataDao;
	
	@Autowired
	private  MessageHandle handle;
	
	

	
	private static ReentrantLock lock = new ReentrantLock();
	
	

	
	/**
	 * 定时处理收到的消息
	 * @throws Exception
	 */
	  @Scheduled(fixedRate = 5000)
	   private  void callback() throws Exception {
		  handle.hanleMessage();
	   }
	
    
    
      
	  /**
	   * 处理订阅消息，接受到消息后，对应更新数据，websocket推送的消息对应自动更新
	   * @param message
	   */
	  @KafkaListener(topics = {"DATA_PUBLISH"})
	  public void updateData(String message)  {
		   //更新数据
		  log.debug("开始接受kafka数据:"+message);
		  handle.saveMessage(message);
	  }
	  
	  
//	  /**
//	   * 查询redis 中的设备实时数据
//	   * @param ids
//	   * @return
//	   */
//	  public List<String> getInfoByTerId(String ids) {
//		  return terRealDataDao.getInfosByTerIds(ids);
//	  }




	public List<String> getInfoByTerId(List<TerimalModel> termimals) {
		// TODO Auto-generated method stub
		return terRealDataDao.getInfosByTerIds(termimals);
	}
	  
	
	  
	  
    
   
}
