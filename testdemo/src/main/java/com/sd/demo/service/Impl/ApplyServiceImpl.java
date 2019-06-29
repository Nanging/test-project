package com.sd.demo.service.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.ApplyDao;
import com.sd.demo.dao.ApplyStateDao;
import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Apply;
import com.sd.demo.entity.ApplyState;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.ApplyService;
import com.sd.demo.support.MyUserDetailsService;
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
	public Apply addApply(Long placeid) {
		Apply apply = new Apply();
		Place place = placeDao.getOne(placeid);
		apply.setPlace(place);
		SysUser applier = userDao.findByUsername(MyUserDetailsService.getCurrentUser());
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
		Apply apply = applyDao.findById(ID.longValue()).get();
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
