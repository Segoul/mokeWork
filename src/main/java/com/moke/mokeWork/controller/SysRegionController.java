package com.moke.mokeWork.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.moke.mokeWork.bean.dto.sysRegion.InsertRegionDto;
import com.moke.mokeWork.bean.dto.sysRegion.QueryRegionByTypePageDto;
import com.moke.mokeWork.bean.entity.rdb.SysRegion;
import com.moke.mokeWork.common.constant.StatusCode;
import com.moke.mokeWork.common.rest.ResultData;
import com.moke.mokeWork.common.rest.ResultPage;
import com.moke.mokeWork.service.SysRegionService;

import io.swagger.annotations.ApiOperation;

/**
 * 系统地域相关controller
 * @author Segoul
 *
 */

@Controller
@RequestMapping("/sysRegion")
public class SysRegionController extends BaseController {

	@Autowired
	private SysRegionService sysRegionService;
	
	/**
	 * 根据父ID查询地域信息
	 * @param parentId
	 * @return
	 */
	@ApiOperation(value = "根据父ID查询地域信息",httpMethod = "GET", notes="根据父ID查询地域信息")
    @RequestMapping(value = "/queryRegionByParentId", method = RequestMethod.GET)
	@ResponseBody
	public ResultData queryRegionByParentId(Long parentId){
		
		//验证参数
		if(parentId == null){
			return ResultData.error(StatusCode.INVALID_PARAM);
		}
		
		List<SysRegion> result = sysRegionService.findSysRegionByParentId(parentId);
		
		return ResultData.one(result);
	}
	
	/**
	 * 根据地域ID查询地域信息
	 * @param regionId
	 * @return
	 */
	@ApiOperation(value = "根据地域ID查询地域信息",httpMethod = "GET", notes="根据地域ID查询地域信息")
    @RequestMapping(value = "/queryRegionById", method = RequestMethod.GET)
	@ResponseBody
	public ResultData queryRegionById(Long regionId){
		
		//验证参数
		if(regionId == null){
			return ResultData.error(StatusCode.INVALID_PARAM);
		}
		
		SysRegion result = sysRegionService.findSysRegionById(regionId);
		
		return ResultData.one(result);
	}
	
	/**
	 * 根据地域类型分页查询地域信息
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "根据地域类型分页查询地域信息",httpMethod = "POST", notes="根据地域类型分页查询地域信息")
    @RequestMapping(value = "/queryRegionByTypePage", method = RequestMethod.POST)
	@ResponseBody
	public ResultPage queryRegionByTypePage(QueryRegionByTypePageDto form){
		
		//验证参数
		if(form == null){
			return ResultPage.error(StatusCode.INVALID_PARAM);
		}
		
		Integer type = form.getType();  //类型 1：省 2：市 3：区县
		Integer currentPage = form.getCurrentPage();  //当前页
		Integer pageSize = form.getPageSize();  //每页显示的总条数
		String name = form.getName();  //区域名称

		if(currentPage == null || pageSize == null || type == null ){
			return ResultPage.error(StatusCode.INVALID_PARAM);
		}
		
		//分页查询地域信息
		PageInfo<SysRegion> pageList = sysRegionService.findSysRegionByTypePage(form);
		
		return ResultPage.list(pageList);
	}
	
	/**
	 * 根据地域ID查询删除地域信息
	 * @param regionId
	 * @return
	 */
	@ApiOperation(value = "根据地域ID查询删除地域信息",httpMethod = "GET", notes="根据地域ID查询删除地域信息")
    @RequestMapping(value = "/deleteRegionById", method = RequestMethod.GET)
	@ResponseBody
	public ResultData deleteRegionById(Long regionId){
		
		//验证参数
		if(regionId == null){
			return ResultData.error(StatusCode.INVALID_PARAM);
		}
		
		//首先查找该地域有无子地域，存在子地域则不能删除
		List<SysRegion> sonList = sysRegionService.findSysRegionByParentId(regionId);
		if(sonList != null && sonList.size() > 0){
			return ResultData.error(StatusCode.IS_EXIST_PARENT_SON);
		}
		
		//删除
		int count = sysRegionService.deleteSysRegion(regionId);
		if(count == 0){
			return ResultData.error(StatusCode.FAIL);
		}
		
		return ResultData.ok();
	}
	
	/**
	 * 新增地域信息
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "新增地域信息",httpMethod = "POST", notes="新增地域信息")
    @RequestMapping(value = "/insertRegion", method = RequestMethod.POST)
	@ResponseBody
	public ResultData insertRegion(InsertRegionDto form){
		
		//验证参数
		if(form == null){
			return ResultData.error(StatusCode.INVALID_PARAM);
		}
		
		String name = form.getName();  //名称
		Integer type = form.getType();  //类型 1：省 2：市 3：区县
		String remark = form.getRemark();  //备注-非必填字段
		Long parentId = form.getParentId();  //父ID
		
		if(StringUtils.isBlank(name) || type == null){
			return ResultData.error(StatusCode.INVALID_PARAM);
		}
		
		if(parentId == null){ //如果父ID为空
			if(type != 1){
				return ResultData.error(StatusCode.INVALID_PARAM);
			}else{
				parentId = Long.valueOf(0);
			}
		}
		
		try{
			SysRegion record = new SysRegion();
			copyProperties(form,record);  //复制对象值
			
			int count = sysRegionService.insertSysRegion(record);
			if(count == 0){
				return ResultData.error(StatusCode.FAIL);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResultData.ok();
	}
}
