package com.cimr.api.code.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimr.api.code.model.Message;
import com.cimr.api.code.service.RealTimeDateService;
import com.cimr.api.code.service.configs.MessageHandle;
import com.cimr.api.code.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(description="指令相关操作",tags= {"code"})
@RestController
@RequestMapping("/code")
public class SendCodeController {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(SendCodeController.class);

	@Autowired
	private KafkaTemplate<String,Object> KafkaTemplate;
	
	@Autowired
	private MessageHandle handle;
	
	@Autowired
	private RealTimeDateService realTimeDateService;
	
	//应用向终端发送的topic
	private String TOPIC_APP_TO_TER = "SYS_MANAGE_CENTER";
	
	

	@ApiOperation(value = "应用向终端发指令", notes = "cmdContents与telIds均以逗号隔开"			
			)
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "cmdType", value = "指令类型", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "cmdTitle", value = "指令标题", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "cmdContents", value = "指令内容", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "telIds", value = "终端编号id", required = false)
	}) 
	@RequestMapping(value="/app/ter/code",method=RequestMethod.GET)
	public String sendCode(@RequestParam("cmdType") int cmdType,
			@RequestParam("cmdTitle") int cmdTitle,
			@RequestParam("cmdContents") String cmdContents,
			@RequestParam("telIds") String telIds) {
		Message message = null;
		try {
			message = MessageUtil.getMessage(90,1,cmdType, cmdTitle, cmdContents, telIds);
			String messageJson=message.toJson();
			log.debug("message:"+messageJson);
			KafkaTemplate.send(TOPIC_APP_TO_TER,messageJson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "faile";
		}
		return "success";
	}
	
	
	
	@ApiOperation(value = "应用设置终端调试", notes = "cmdContents与telIds均以逗号隔开"			
			)	
	@RequestMapping(value="/app/ter/debug",method=RequestMethod.GET)
	public String sendDebug(
			@RequestParam("telIds") String telIds) {
		Message message = null;
		try {
			message = MessageUtil.getMessage(90,2,null, null, null, telIds);
			String messageJson=message.toJson();
			log.debug("message:"+messageJson);
			KafkaTemplate.send(TOPIC_APP_TO_TER,messageJson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "faile";
		}
		return "success";
	}
	
	
	@ApiOperation(value = "应用设置终端解除调试", notes = "cmdContents与telIds均以逗号隔开"			
			)	
	@RequestMapping(value="/app/ter/endDebug",method=RequestMethod.GET)
	public String sendEndDebug(
			@RequestParam("telIds") String telIds) {
		Message message = null;
		try {
			message = MessageUtil.getMessage(90,3,null, null, null, telIds);
			String messageJson=message.toJson();
			log.debug("message:"+messageJson);
			KafkaTemplate.send(TOPIC_APP_TO_TER,messageJson);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "faile";
		}
		return "success";
	}
	
	
	
	@ApiOperation(value = "获取实时数据"			
			)	
	@RequestMapping(value="/app/ter/realData",method=RequestMethod.GET)
	public List<String> sendCodeToGetRealData(
			@RequestParam("telIds") String telIds) {
	
		handle.getRealData(telIds);
		return realTimeDateService.getInfoByTerId(telIds);
	}
	
	
	
//	/*
//	 *测试接受数据
//	 */
//	 @Scheduled(fixedRate = 10)
//	   private  void callback() throws Exception {
//		 String str = "{\"consumerId\":\"iot\",\"data\":{\"cmdTitle\":2,\"cmdContent\":\"ISIkJg==\",\"cmdType\":30,\"telIds\":\"[\\\"TEL0000001\\\"]\"},\"msgId\":5481,\"msgTime\":1527130382348,\"producerId\":\"TEL0000001\",\"title\":1,\"type\":90,\"version\":1}";
//		 log.debug("发送消息"+str);
//		 KafkaTemplate.send("DATA_PUBLISH", str);
//	   }
	
}