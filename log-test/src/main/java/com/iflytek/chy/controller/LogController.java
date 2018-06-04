package com.iflytek.chy.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
	
		private final Logger logger = Logger.getLogger(LogController.class);

		@RequestMapping("log")
		public String getLogs() {
			logger.info("日志测试  log info");
			logger.error("日志测试  log info");
			logger.debug("日志测试  log info");
			return "OK";
		}
		
}
