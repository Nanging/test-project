package com.sd.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Apply;
import com.sd.demo.entity.SysUser;
import com.sd.demo.web.ApplyItem;

public interface ApplyService {


	Apply getApplyDetail(int id);

	Apply modifyApplyDetail(Apply apply);

	Boolean deleteApply(int id);

	Page<Apply> getList(int page, int size);

	Map<String, Object> getApplyList(int page, int size);

	Apply addApply(Long placeid, Timestamp startTime, int time, SysUser applier);

	List<ApplyItem> getApplyByPlace(int long1);

	boolean setRefuse(int id);

	boolean setConfirm(int id);

}
