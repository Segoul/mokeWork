package com.moke.mokeWork.bean.dto.sysRegion;

import lombok.Data;

/**
 * 根据地域类型分页查询地域信息入参
 * @author Segoul
 *
 */

@Data
public class QueryRegionByTypePageDto {

	private Integer currentPage;  //当前页
	
	private Integer pageSize;  //每页显示的总条数
	
	private Integer type;  //类型 1：省 2：市 3：区县
	
	private String name;  //区域名称-非必填字段
}
