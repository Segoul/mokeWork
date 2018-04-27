package com.moke.mokeWork.bean.entity.rdb;

import lombok.Data;

/**
 * 系统地域表
 * mk_sys_region
 * @author Segoul
 *
 */

@Data
public class SysRegion {

	private Long id;  //自增ID
	
	private String name;  //名称
	
	private Integer type;  //类型 1：省 2：市 3：区县
	
	private String remark;  //备注
	
	private Long parentId;  //父ID
}
