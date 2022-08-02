package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserOptcartVO;

@Repository
@Mapper
public interface UserOptcartMapper {

	public void insert(UserOptcartVO obj) throws Exception;

	public List<UserOptcartVO> select_byCtid(int id) throws Exception;
	public List<UserOptcartVO> selectall() throws Exception;
	

	

}




