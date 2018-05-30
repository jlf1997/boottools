package com.cimr.api.code.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cimr.api.code.model.Terminal_1_Info;
import com.cimr.api.code.service.RealTimeDateService;
import com.cimr.api.comm.model.TerimalModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(description="实时数据相关操作",tags= {"latestData"})
@RestController
@RequestMapping("/latest_data")
public class TerminalLastData {

	@Autowired
	private RealTimeDateService realTimeDateService;
	
	@ApiOperation(value = "获取最新数据"			
			)	
	@RequestMapping(value="/app/ter/info",method=RequestMethod.POST)
	public List<HashMap> getlastDate(
			@RequestBody List<TerimalModel> termimals) {
	
		return realTimeDateService.getInfoByTerId(termimals);
	}
	
	
	@ApiOperation(value = "获取最新位置数据"			
			)	
	
	@RequestMapping(value="/app/ter/location",method=RequestMethod.POST)
	public List<Terminal_1_Info> getlocationlastDate(
			@RequestBody List<TerimalModel> termimals) {
	
		return realTimeDateService.getLocationInfoByTerId(termimals);
	}
}
