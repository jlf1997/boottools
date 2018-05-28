package com.cimr.api.code.service.configs;

public interface MessageHandle {
	
	
	/**
	 * 缓存kafka 消息
	 * @param message
	 */
	public void saveMessage(String message);
	
	
	/**
	 * 处理kafka消息
	 * @param message
	 */
	public void hanleMessage();
	
	
	/**
	 * 获取实时数据
	 */
	public void getRealData(String telIds);
	
	

	

}
