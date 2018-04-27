package com.moke.mokeWork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moke.mokeWork.bean.dto.sysRegion.QueryRegionByTypePageDto;
import com.moke.mokeWork.bean.entity.rdb.SysRegion;
import com.moke.mokeWork.dao.rdb.SysRegionMapper;
import com.moke.mokeWork.service.SysRegionService;

/**
 * 系统地域ServiceImpl
 * @author Segoul
 *
 */

@Service
public class SysRegionServiceImpl implements SysRegionService {

	@Autowired
	private SysRegionMapper sysRegionMapper;
	
	/**
	 * 根据父ID查询地域信息
	 * @param parentId
	 * @return
	 */
	@Override
	public List<SysRegion> findSysRegionByParentId(Long parentId){
		List<SysRegion> list = sysRegionMapper.selectSysRegionByParentId(parentId);
		return list;
	}
	
	/**
	 * 根据地域ID查询地域信息
	 * @param id
	 * @return
	 */
	@Override
	public SysRegion findSysRegionById(Long id){
		SysRegion temp = sysRegionMapper.selectByPrimaryKey(id);
		return temp;
	}
	
	/**
	 * 根据类型查询地域信息
	 * @param type
	 * @return
	 */
	@Override
	public List<SysRegion> findSysRegionByType(Integer type){
		List<SysRegion> list = sysRegionMapper.selectSysRegionByType(type);
		return list;
	}
	
	/**
	 * 根据地域类型分页查询地域信息
	 * @param form
	 * @return
	 */
	@Override
	public PageInfo<SysRegion> findSysRegionByTypePage(QueryRegionByTypePageDto form){
		PageHelper.startPage(form.getCurrentPage(), form.getPageSize());
		List<SysRegion> list = sysRegionMapper.selectSysRegionByTypePage(form);
		PageInfo<SysRegion> result = new PageInfo<SysRegion>(list);
		return result;
	}
	
	/**
	 * 新增系统地域
	 * @param info
	 * @return
	 */
	public int insertSysRegion(SysRegion record){
		int count = sysRegionMapper.insertSelective(record);
		return count;
	}
	
	/**
	 * 根据ID删除系统地域
	 * @param id
	 * @return
	 */
	public int deleteSysRegion(Long id){
		int count = sysRegionMapper.deleteByPrimaryKey(id);
		return count;
	}
}
