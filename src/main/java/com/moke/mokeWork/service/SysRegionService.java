package com.moke.mokeWork.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.moke.mokeWork.bean.dto.sysRegion.QueryRegionByTypePageDto;
import com.moke.mokeWork.bean.entity.rdb.SysRegion;

/**
 * 系统地域Service
 * @author Segoul
 *
 */

public interface SysRegionService {

	/**
	 * 根据父ID查询地域信息
	 * @param parentId
	 * @return
	 */
	List<SysRegion> findSysRegionByParentId(Long parentId);
	
	/**
	 * 根据地域ID查询地域信息
	 * @param id
	 * @return
	 */
	SysRegion findSysRegionById(Long id);
	
	/**
	 * 根据类型查询地域信息
	 * @param type
	 * @return
	 */
	List<SysRegion> findSysRegionByType(Integer type);
	
	/**
	 * 根据地域类型分页查询地域信息
	 * @param form
	 * @return
	 */
	PageInfo<SysRegion> findSysRegionByTypePage(QueryRegionByTypePageDto form);
	
	/**
	 * 新增系统地域
	 * @param info
	 * @return
	 */
	int insertSysRegion(SysRegion record);
	
	/**
	 * 根据ID删除系统地域
	 * @param id
	 * @return
	 */
	int deleteSysRegion(Long id);
}
