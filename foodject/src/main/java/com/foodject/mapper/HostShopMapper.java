package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.HostShopVO;

@Repository
@Mapper
public interface HostShopMapper {

	public String insert(HostShopVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public void update(HostShopVO obj) throws Exception;
	public HostShopVO select(int id) throws Exception;
	public List<HostShopVO> selectall() throws Exception;
	public List<HostShopVO> selectmid(String obj) throws Exception;
	
	public int insertMarker(HostShopVO obj) throws Exception;
	public int insertShop(HostShopVO obj)throws Exception;
	
}
