package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserOptcartMapper;
import com.foodject.vo.UserOptcartVO;

@Service
public class UserOptcartBiz implements Biz<Integer, UserOptcartVO> {

	@Autowired
	UserOptcartMapper dao;
	
	@Override
	public void register(UserOptcartVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(UserOptcartVO v) throws Exception {
				
	}

	@Override
	public void remove(Integer k) throws Exception {
				
	}

	@Override
	public UserOptcartVO get(Integer k) throws Exception {
		return null;
	}
	
	public List<UserOptcartVO> get_byCtid(Integer k) throws Exception {
		return dao.select_byCtid(k);
	}

	@Override
	public List<UserOptcartVO> get() throws Exception {
		return dao.selectall();
	}


}
