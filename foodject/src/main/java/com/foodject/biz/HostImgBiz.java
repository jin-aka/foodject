package com.foodject.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodject.frame.Biz;
import com.foodject.mapper.HostImgMapper;
import com.foodject.vo.HostImgVO;

@Service
public class HostImgBiz implements Biz<Integer, HostImgVO> {
	
	@Autowired
	HostImgMapper dao;

	@Override
	public void register(HostImgVO v) throws Exception {
		dao.insert(v);
		
	}

	@Override
	public void modify(HostImgVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HostImgVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<HostImgVO> get() throws Exception {
		return dao.selectall();
	}

}
