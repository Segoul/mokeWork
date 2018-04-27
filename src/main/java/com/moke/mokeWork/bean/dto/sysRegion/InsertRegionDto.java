package com.moke.mokeWork.bean.dto.sysRegion;

import lombok.Data;

/**
 * 新增地域信息入参
 * @author Segoul
 *
 */

@Data
public class InsertRegionDto {

	private String name;  //名称
	
	private Integer type;  //类型 1：省 2：市 3：区县
	
	private String remark;  //备注-非必填字段
	
	private Long parentId;  //父ID
}
