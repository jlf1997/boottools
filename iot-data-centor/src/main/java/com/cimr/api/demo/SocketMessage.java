package com.cimr.api.demo;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.cimr.api.code.model.Message;

public class SocketMessage {
	
	
	  public SocketMessage() {
		//直接设置需要获取实时数据
		  realTime = true;
		  deadLine = new Date();
		  
		
	  }
	    private String message;

	    public String date;
	    
	    public String num;
	    
	    
	    
		//请求过期时间
		private Date deadLine;
		//是否需要获取实时数据
		private boolean realTime = false;
		public Date getDeadLine() {
			return deadLine;
		}
		public void setDeadLine(Date deadLine) {
			this.deadLine = deadLine;
		}
		public boolean isRealTime() {
			return realTime;
		}
		public void setRealTime(boolean realTime) {
			this.realTime = realTime;
		} 
		
		/**
		 * 判断数据是否过期
		 * @return
		 */
		public boolean isExpired() {
			return new Date().after(deadLine);
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
}
