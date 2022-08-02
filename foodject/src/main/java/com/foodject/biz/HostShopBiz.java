package com.foodject.biz;

import java.util.List;

import com.foodject.frame.Biz;
import com.foodject.mapper.HostShopMapper;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostShopBiz implements Biz<Integer, HostShopVO> {

	@Autowired
	HostShopMapper dao;


	@Override
	public void register(HostShopVO v) throws Exception {
		dao.insert(v);	
	}

	@Override
	public void modify(HostShopVO v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public HostShopVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<HostShopVO> get() throws Exception {
		return dao.selectall();
	}

	public List<HostShopVO> getmid(String v) throws Exception{
		return dao.selectmid(v);
	}
	public int registerMarker(HostShopVO v) throws Exception {
		return dao.insertMarker(v);
	}
	public int registerShop(HostShopVO v) throws Exception {
		return dao.insertShop(v);
	}
}
