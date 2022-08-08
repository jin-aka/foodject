package com.foodject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.foodject.vo.UserOrdersMyVO;

@Repository
@Mapper
public interface UserOrdersMapper {
	
	public List<UserOrdersMyVO> selectod(String uid) throws Exception;
	public List<UserOrdersMyVO> selectodde(int oid) throws Exception;
	public UserOrdersMyVO selectodinfo(Integer id) throws Exception;
	public List<Integer> selectoddeid(int oid) throws Exception;
	public UserOrdersMyVO selectoddemenu(int deid) throws Exception;
	public List<UserOrdersMyVO> selectoddeopt(int deid) throws Exception;
	public UserOrdersMyVO selectallprice(int oid) throws Exception;

}
