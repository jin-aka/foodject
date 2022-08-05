package com.foodject.mapper;

import java.util.ArrayList;
import java.util.List;

import com.foodject.vo.UserOrdersMyVO;
import com.foodject.vo.UserOrdersVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserOrdersMapper {
	
	public void insert(UserOrdersVO obj) throws Exception;
	public void delete(Integer id) throws Exception;
	public void update(UserOrdersVO obj) throws Exception;
	public UserOrdersVO select(Integer id) throws Exception;
	public List<UserOrdersVO> selectall() throws Exception;
	
	public void orderdelete(int oid) throws Exception;
	
	public List<UserOrdersMyVO> selectod(String uid) throws Exception;
	public List<UserOrdersMyVO> selectodde(int oid) throws Exception;
	public UserOrdersMyVO selectodinfo(Integer id) throws Exception;
	public List<Integer> selectoddeid(int oid) throws Exception;
	public UserOrdersMyVO selectoddemenu(int deid) throws Exception;
	public List<UserOrdersMyVO> selectoddeopt(int deid) throws Exception;
	public UserOrdersMyVO selectallprice(int oid) throws Exception;

}
