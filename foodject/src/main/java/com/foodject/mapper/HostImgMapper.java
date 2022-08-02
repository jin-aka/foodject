package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.HostImgVO;

@Repository
@Mapper
public interface HostImgMapper {

	public void insert(HostImgVO obj) throws Exception;
	public void update(HostImgVO obj) throws Exception;
	
	public HostImgVO select(int id) throws Exception;
	public List<HostImgVO> selectall() throws Exception;
	
}
