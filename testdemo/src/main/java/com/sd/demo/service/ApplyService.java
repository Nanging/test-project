package com.sd.demo.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Apply;
import com.sd.demo.entity.Product;

public interface ApplyService {

	Apply addApply(Long placeid);

	Apply getApplyDetail(int id);

	Apply modifyApplyDetail(Apply apply);

	Boolean deleteApply(int id);

	Page<Apply> getList(int page, int size);

	Map<String, Object> getApplyList(int page, int size);

}
