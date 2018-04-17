package com.zte.chy.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.zte.chy.model.ControlMessageLog;

public interface MessageLogRepository extends MongoRepository<ControlMessageLog, ObjectId> {

	List<ControlMessageLog> findById(String id);
	
	List<ControlMessageLog> findByBarcodeOrderByOutMqTimeAsc(String barcode);
	
	Page<ControlMessageLog> findByBarcode(String barcode, Pageable pageable);
}
