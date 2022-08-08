package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.UserCustMapper;
import com.foodject.vo.UserCustVO;

@Service
public class UserCustBiz implements Biz<String, UserCustVO>{
	
	@Autowired
	UserCustMapper dao;

	@Override
	public void register(UserCustVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(UserCustVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public UserCustVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<UserCustVO> get() throws Exception {
		return dao.selectall();
	}
	
	public void modifysts(UserCustVO v) throws Exception{
		dao.updatests(v);
	}


}
