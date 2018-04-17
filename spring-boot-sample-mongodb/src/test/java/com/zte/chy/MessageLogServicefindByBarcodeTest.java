package com.zte.chy;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zte.chy.model.ControlMessageLog;
import com.zte.chy.mongodb.service.MessageLogService;

public class MessageLogServicefindByBarcodeTest {
	@Autowired
	private MessageLogService messageLogService;

	@Test
	public void test() {

		String barcode = "123456789000";
		List<ControlMessageLog> list = messageLogService.findByBarcode(barcode);
		list.stream().forEachOrdered(System.out::println);
	}
}
