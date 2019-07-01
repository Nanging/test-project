package com.sd.demo.service.Impl;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.sd.demo.dao.ApplyDao;
import com.sd.demo.dao.ApplyStateDao;
import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Apply;
import com.sd.demo.entity.ApplyState;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.ApplyService;
import com.sd.demo.web.ApplyItem;
@Service
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyDao applyDao;
	
	@Autowired
	private ApplyStateDao applyStateDao;
	
	@Autowired 
	private PlaceDao placeDao;
	
	@Autowired
	private SysUserDao userDao;
	
	@Override
	public boolean setConfirm(int id) {
		Integer ID = id;
		Apply apply = null;
		Optional<Apply> applyOptional =  applyDao.findById(ID.longValue());	
		if (applyOptional.isPresent()) {
			apply = applyOptional.get();
		}
		if (apply == null ) {
			return false;
		}
		apply.setState(applyStateDao.getOne((long)2));
		applyDao.saveAndFlush(apply);
		return true;
	}
	@Override
	public boolean setRefuse(int id) {
		Integer ID = id;
		Apply apply = null;
		Optional<Apply> applyOptional =  applyDao.findById(ID.longValue());	
		if (applyOptional.isPresent()) {
			apply = applyOptional.get();
		}
		if (apply == null ) {
			return false;
		}
		apply.setState(applyStateDao.getOne((long)3));
		applyDao.saveAndFlush(apply);
		return true;
	}
	
	@Override
	public List<ApplyItem> getApplyByPlace(int placeid) {
		Place place = placeDao.getOne((long)placeid);
		List<Apply> applies =  applyDao.findByPlace(place);
		List<ApplyItem> resultList = new ArrayList<>();
		for (Apply apply : applies) {
			ApplyItem item = new ApplyItem();
			item.setId(apply.getId().intValue());
			item.setApplier(apply.getApplier().getUsername());
			item.setPlacename(apply.getPlace().getName());
			item.setPlaceid(apply.getPlace().getId().intValue());
			item.setStartTime(apply.getStartTime());
			item.setState(apply.getState().getState());
			item.setTime(apply.getTime());
			resultList.add(item);
		}
		return resultList;
	}
	
	@Override
	public Apply getApplyDetail(int id) {
		Integer ID = id;
		Apply apply = null;
		Optional<Apply> applyOptional =  applyDao.findById(ID.longValue());	
		if (applyOptional.isPresent()) {
			apply = applyOptional.get();
		}
		return apply;
	}
	@Override
	public Apply addApply(Long placeid,Timestamp startTime,int time,int applierid) {
		if(placeid <= 0 || applierid <= 0)return null;
		if(!(userDao.existsById((long)applierid)) || !(placeDao.existsById((long)placeid)))return null;
		System.out.println("before");
		SysUser applier = userDao.getOne((long)applierid);
		System.out.println("after");
		if (applier == null) {
			return null;
		}
		System.out.println(applier.getId());
		System.out.println("next");
		Apply apply = new Apply();
		Place place = placeDao.getOne(placeid);
		apply.setPlace(place);
		apply.setStartTime(startTime);
		apply.setTime(time);
		apply.setApplier(applier);
		ApplyState state = applyStateDao.getOne((long)1);
		apply.setState(state);
		applyDao.saveAndFlush(apply);
		return applyDao.getOne(apply.getId());
	}
	
	@Override
	public Apply modifyApplyDetail(Apply apply) {
		applyDao.saveAndFlush(apply);
		return applyDao.getOne(apply.getId());
	}
	@Override
	public Boolean deleteApply(int id) {
		Integer ID = id;
		if(id <= 0 )return false;
		System.out.println("before");
		if(!applyDao.existsById(ID.longValue()))return false;
		Apply apply = applyDao.getOne(ID.longValue());
		System.out.println("after");
		if(apply == null)return false;
		System.out.println(apply.getId());
		System.out.println("next");
		applyDao.delete(apply);
		Optional<Apply> applyOptional =  applyDao.findById(ID.longValue());	
		if (applyOptional.isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Page<Apply> getList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return applyDao.findAll(pageable);
	}
	
	@Override
	public Map<String, Object> getApplyList(int page, int size) {
		Map<String, Object>attr = new HashMap<String, Object>();
		Page<Apply> applys = getList(page, size);
		attr.put("applys", applys.getContent());
		attr.put("total", applys.getNumber());
		attr.put("page", page);
		return attr;
	}
	
}
