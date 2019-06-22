package com.sd.demo.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.sd.demo.entity.Product;
import com.sd.demo.entity.Record;

public interface RecordService {

	Record getRecordDetail(int id);

	Record modifyRecordDetail(Record record);

	Boolean deleteRecord(int id);

	Map<String, Object> getRecordList(int page, int size);

	Record addRecord(Long borrowerid, Long productid, Timestamp starttime, Timestamp endtime);

	Page<Record> getUserList(int page, int size);

	Page<Record> getAdminList(int page, int size);

}
