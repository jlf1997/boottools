package com.cimr.api.propertiy;


/**
 * 指令相关配置
 * @author Administrator
 *
 */
public class CodeProperties {

	/**
	 * 订阅间隔 毫秒数
	 */
	private Long subscribeTimeMill = 5*60*1000L;
	
	private String callbackUrl = "/callback";
	
	
}
