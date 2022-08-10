package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.HostManagerVO;

@Repository
@Mapper
public interface HostManagerMapper {

	public void insert(HostManagerVO obj) throws Exception;
	public void updatests(HostManagerVO obj) throws Exception;
	public void update(HostManagerVO obj) throws Exception;
	public HostManagerVO select(String id) throws Exception;
	public List<HostManagerVO> selectall() throws Exception;
	public void selectpwd(HostManagerVO obj) throws Exception;
	public void updatepwd(HostManagerVO obj) throws Exception;
}
