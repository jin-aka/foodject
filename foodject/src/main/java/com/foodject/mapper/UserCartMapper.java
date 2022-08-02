package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserCartVO;

@Repository
@Mapper
public interface UserCartMapper {

	public void insert(UserCartVO obj) throws Exception;
	public void updateNum(UserCartVO obj) throws Exception;
	public void delete(int id) throws Exception;
	public UserCartVO select(int id) throws Exception;
	public List<UserCartVO> selectall() throws Exception;
	
	public UserCartVO selectForInsert(UserCartVO obj) throws Exception;
	

}




