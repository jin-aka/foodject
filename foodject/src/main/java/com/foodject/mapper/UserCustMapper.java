package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserCustVO;

@Repository
@Mapper
public interface UserCustMapper {

	public void insert(UserCustVO obj) throws Exception;
	public void delete(String id) throws Exception;
	public void update(UserCustVO obj) throws Exception;
	public UserCustVO select(String id) throws Exception;
	public List<UserCustVO> selectall() throws Exception;
	public void updatests(UserCustVO obj) throws Exception;
	
}
