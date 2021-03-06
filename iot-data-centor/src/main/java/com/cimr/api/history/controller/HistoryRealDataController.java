package com.cimr.api.history.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
public class HistoryRealDataController {
  
	@Autowired
	private  RealDataSignalHistoryService histroyService;
	/**
	 * 根据信号id 获取终端编号查询对应历史数据
	 * @param singal
	 * @param terid
	 * @return
	 */
	@ApiOperation(value = "根据信号id 查询对应终端的所有历史数据", notes = "查询所有字段"			
			)	  
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "singal", value = "信号", required = true),
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "terid", value = "终端id", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "beg", value = "开始时间", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "end", value = "结束时间", required = false),
		@ApiImplicitParam(paramType = "query", allowMultiple = true,dataType = "string", name = "sortBy", value = "排序字段", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "string", name = "sortType", value = "排序字段", required = false,allowableValues="ASC,DESC")
		}) 
	@RequestMapping(value= "/app/{terid}/{singal}/all",method=RequestMethod.GET)
	public List<Map<String,Object>> findTersAllRealData(
			@PathVariable("singal") String singal,
			@PathVariable(value="terid",required=true) String terid,
			@RequestParam(name="beg",required=false) Long beg,
			@RequestParam(name="end",required=false) Long end,
			@RequestParam(name="sortBy",required=false) String[] sortBy,
			@RequestParam(name="sortType",required=false) String sortType
			) {
		List<Map<String,Object>> list = histroyService.findTersAllRealData(singal, terid,beg,end,sortBy,sortType);
		return list;
	}
	
	/**
	 * 根据信号id 获取终端编号查询对应历史数据
	 * 只查询给定的字段
	 * @param singal
	 * @param terid
	 * @return
	 */
	@ApiOperation(value = "根据信号id 查询对应终端的所有历史数据", notes = "可通过fields指定需要的字段，为空时查询所有字段"			
			)	  
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "singal", value = "信号", required = true),
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "terid", value = "终端id", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "beg", value = "开始时间", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "end", value = "结束时间", required = false),
		@ApiImplicitParam(paramType = "query", allowMultiple = true,dataType = "string", name = "sortBy", value = "排序字段", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "string", name = "sortType", value = "排序类型", required = false,allowableValues="ASC,DESC"),
		@ApiImplicitParam(paramType = "query", allowMultiple = true,dataType = "string", name = "fields", value = "查询的字段", required = false)
		}) 
	@RequestMapping(value= "/app/{terid}/{singal}/include",method=RequestMethod.GET)
	public List<Map<String,Object>> findTersRealDataIncludeFields(
			@PathVariable("singal") String singal,
			@PathVariable(value="terid",required=true) String terid,
			@RequestParam(name="beg",required=false) Long beg,
			@RequestParam(name="end",required=false) Long end,
			@RequestParam(name="sortBy",required=false) String[] sortBy,
			@RequestParam(name="sortType",required=false) String sortType,
			@RequestParam(name="fields",required=false) String[] fields
			) {
		List<Map<String,Object>> list = histroyService.findTersAllRealDataInclude(singal, terid, beg, end, sortBy, sortType, fields);
		return list;
	}
	
	/**
	 * 根据信号id 获取终端编号查询对应历史数据
	 * 只查询给定的字段
	 * @param singal
	 * @param terid
	 * @return
	 */
	@ApiOperation(value = "根据信号id 查询对应终端的所有历史数据", notes = "可通过fields指定需要过滤的字段，为空时查询所有字段"			
			)	  
	@ApiImplicitParams({ 
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "singal", value = "信号", required = true),
		@ApiImplicitParam(paramType = "path", dataType = "String", name = "terid", value = "终端id", required = true),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "beg", value = "开始时间", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "end", value = "结束时间", required = false),
		@ApiImplicitParam(paramType = "query", allowMultiple = true,dataType = "string", name = "sortBy", value = "排序字段", required = false),
		@ApiImplicitParam(paramType = "query", dataType = "string", name = "sortType", value = "排序类型", required = false,allowableValues="ASC,DESC"),
		@ApiImplicitParam(paramType = "query", allowMultiple = true,dataType = "string", name = "fields", value = "排除的字段", required = false)
	})  
	@RequestMapping(value= "/app/{terid}/{singal}/exclude",method=RequestMethod.GET)
	public List<Map<String,Object>> findTersRealDataExcludeFields(
			@PathVariable("singal") String singal,
			@PathVariable(value="terid",required=true) String terid,
			@RequestParam(name="beg",required=false) Long beg,
			@RequestParam(name="end",required=false) Long end,
			@RequestParam(name="sortBy",required=false) String[] sortBy,
			@RequestParam(name="sortType",required=false) String sortType,
			@RequestParam(name="fields",required=false) String[] fields
		){
		List<Map<String,Object>> list = histroyService.findTersAllRealDataExculde(singal, terid, beg, end, sortBy, sortType, fields);
		return list;
	}
	
	
	
	


}
