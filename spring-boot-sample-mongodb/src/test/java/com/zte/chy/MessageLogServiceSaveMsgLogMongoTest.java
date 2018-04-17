package com.zte.chy;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zte.chy.model.ControlMessageLog;
import com.zte.chy.mongodb.service.MessageLogService;

public class MessageLogServiceSaveMsgLogMongoTest {
	@Autowired
	private MessageLogService messageLogSerivce;

	@Test
	public void test() {
		ControlMessageLog controlMessageLog = new ControlMessageLog();
		controlMessageLog.setBarcode("123456789000");
		controlMessageLog.setCreateTime(new Date());
		controlMessageLog.setIntoRedisTime(new Date());
		controlMessageLog.setLog("hhhhh");
		controlMessageLog.setOutMqTime(new Date());
		controlMessageLog.setQueueName("yyyyy");
		controlMessageLog.setRemark("ccccc");
		controlMessageLog.setState("kkkkkk");

		ControlMessageLog msgLog = messageLogSerivce.saveMsgLogMongo(controlMessageLog);
		System.out.println(JSON.toJSONString(msgLog));
	}
}
