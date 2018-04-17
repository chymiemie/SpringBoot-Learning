package com.zte.chy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.zte.chy.model.ControlMessageLog;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.service.MessageLogService;

public class MessageLogServicefindByBarcodePageTest {
	@Autowired
	private MessageLogService messageLogService;

	@Test
	public void test(){

	MaterialPage materialPage = new MaterialPage();
	materialPage.setBarcode("123456789000");
	materialPage.setEndTime("2017-03-06 10:51:12.855");
	materialPage.setMaterialcode("111111");
	materialPage.setPageNum(1);
	materialPage.setPageSize(5);
	materialPage.setStartTime("2017-03-06 10:51:12.855");

	Page<ControlMessageLog> controlMessageLog = messageLogService.findByBarcodePage(materialPage);
	System.out.println(JSON.toJSONString(controlMessageLog));

	}
}
