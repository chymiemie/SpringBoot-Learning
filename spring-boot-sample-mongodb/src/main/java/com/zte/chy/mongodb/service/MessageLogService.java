package com.zte.chy.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zte.chy.model.ControlMessageLog;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.repository.MessageLogRepository;

@Service
public class MessageLogService {

	@Autowired
	private MessageLogRepository messageLogRepository;

	/**
	 * 保存日志消息
	 * 
	 * @param msgLogMongo
	 */
	public ControlMessageLog saveMsgLogMongo(ControlMessageLog msgLogMongo) {
		return messageLogRepository.save(msgLogMongo);

	}

	/**
	 * 根据Id查询管控日志消息
	 * @param id
	 * @return
	 */
	public List<ControlMessageLog> findById(String id) {
		return messageLogRepository.findById(id);
	}

	/**
	 * 根据条码查询管控日志信息
	 * 
	 * @param barcode
	 */
	public List<ControlMessageLog> findByBarcode(String barcode) {
		return messageLogRepository.findByBarcodeOrderByOutMqTimeAsc(barcode);
	}

	/**
	 * // 根据物料代码 查询日志记录，带分页功能
	 * 
	 * @param barcode
	 * @return
	 */
	public Page<ControlMessageLog> findByBarcodePage(MaterialPage materialPage) {
		Pageable page = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		return messageLogRepository.findByBarcode(materialPage.getBarcode(), page);
	}

}
