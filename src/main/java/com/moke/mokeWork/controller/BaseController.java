package com.moke.mokeWork.controller;

import com.moke.mokeWork.common.utils.CopyPropertiesUtil;

/**
 * 公共方法
 * @author Segoul
 *
 */

public class BaseController {

	/**
	 * 对象参数复制
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public static void copyProperties(Object from, Object to) throws Exception {  
		CopyPropertiesUtil.copyPropertiesExclude(from, to, null);  
	}
}
