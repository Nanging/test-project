package com.sd.demo.service.Impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.ProductDao;
import com.sd.demo.dao.RecordDao;
import com.sd.demo.dao.RecordStateDao;
import com.sd.demo.dao.SysPermissionDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Place;
import com.sd.demo.entity.Product;
import com.sd.demo.entity.ProductState;
import com.sd.demo.entity.Record;
import com.sd.demo.entity.RecordState;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.RecordService;
import com.sd.demo.support.MyUserDetailsService;
@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	private RecordStateDao recordStateDao;
	
	@Override
	public Record getRecordDetail(int id) {
		Integer ID = id;
		Record record = null;
		Optional<Record> recordOptional =  recordDao.findById(ID.longValue());	
		if (recordOptional.isPresent()) {
			record = recordOptional.get();
		}
		return record;
	}
	
	@Override
	public Record addRecord(Long borrowerid,Long productid, Timestamp starttime,Timestamp endtime) {
		Record record = new Record();
		SysUser borrower = userDao.getOne(borrowerid);
		record.setBorrower(borrower);
		record.setStarttime(starttime);
		record.setEndtime(endtime);
		Product product = productDao.getOne(productid); 
		record.setProduct(product);
		RecordState state = recordStateDao.getOne((long)1);
		record.setState(state);
		recordDao.saveAndFlush(record);
		return recordDao.getOne(record.getId());
	}
	
	@Override
	public Record modifyRecordDetail(Record record) {
		recordDao.saveAndFlush(record);
		return recordDao.findById(record.getId()).get();
	}
	@Override
	public Boolean deleteRecord(int id) {
		Integer ID = id;
		Record record = recordDao.findById(ID.longValue()).get();
		recordDao.delete(record);
		Optional<Record> recordOptional =  recordDao.findById(ID.longValue());	
		if (recordOptional.isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Page<Record> getUserList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		SysUser user = userDao.findByUsername(MyUserDetailsService.getCurrentUser());
		System.out.println("user id "+user.getId());
		return recordDao.findByBorrower(user, pageable);
	}
	
	@Override
	public Page<Record> getAdminList(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return recordDao.findAll(pageable);
	}
	
	@Override
	public Map<String, Object> getRecordList(int page, int size) {
		Map<String, Object>attr = new HashMap<String, Object>();
		Page<Record> records = getUserList(page, size);
		attr.put("records", records.getContent());
		attr.put("total", records.getNumber());
		attr.put("page", page);
		return attr;
	}
	
}
