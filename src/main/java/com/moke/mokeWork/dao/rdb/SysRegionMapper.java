package com.moke.mokeWork.dao.rdb;

import java.util.List;

import com.moke.mokeWork.bean.dto.sysRegion.QueryRegionByTypePageDto;
import com.moke.mokeWork.bean.entity.rdb.SysRegion;

/**
 * 系统地域mapper
 * @author Segoul
 *
 */

public interface SysRegionMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysRegion record);

    int insertSelective(SysRegion record);

    SysRegion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRegion record);

    int updateByPrimaryKey(SysRegion record);
    
    /**
	 * 根据父ID查询地域信息
	 * @param parentId
	 * @return
	 */
	List<SysRegion> selectSysRegionByParentId(Long parentId);
	
	/**
	 * 根据类型查询地域信息
	 * @param type
	 * @return
	 */
	List<SysRegion> selectSysRegionByType(Integer type);
	
	/**
	 * 根据地域类型分页查询地域信息
	 * @param form
	 * @return
	 */
	List<SysRegion> selectSysRegionByTypePage(QueryRegionByTypePageDto form);
}