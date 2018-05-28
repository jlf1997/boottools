package com.cimr.api.history.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimr.api.history.service.RealDataSignalHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(description="历史记录相关查询",tags= {"history"})
@RestController
@RequestMapping("/history/realdata")
public class HistoryController {
  
	@Autowired
	private  RealDataSignalHistoryService histroyService;
	
	@ApiOperation(value = "根据信号id 获取终端编号查询对应历史数据", notes = ""			
			)	  
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "singal", value = "信号", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "String", name = "terid", value = "终端id", required = true)
		}) 
	@RequestMapping(value="/demo",method=RequestMethod.GET)
	public List<Map<String,Object>> findDevInfoById(@RequestParam String singal,
			@RequestParam String terid) {
		List<Map<String,Object>> list = histroyService.findAllByTerId(singal, terid);
		return list;
	}
	
	@ApiOperation(value = "根据时间点查询获取终端的历史数据", notes = ""			
			)
	public List<Map<String,Object>> findTersRealDataByTime(@RequestParam String singal,
			@RequestParam String terid
			) {
		
		
		return null;
	}

}
