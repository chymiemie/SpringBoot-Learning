package com.zte.chy.control;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.zte.chy.model.ControlMessageLog;
import com.zte.chy.mongodb.service.MessageLogService;
import com.zte.chy.exception.InvalidRequestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ApiParam 和 @ApiImplicitParam区别
 *           参考地址：http://www.cnblogs.com/softidea/p/6251249.html
 * @author 10191029
 *
 */

@RestController
@RequestMapping(value = "/api/dal")
@Api(tags = "管控日志消息相关操作的Controller")
public class MessageLogConrtol {
	static final Logger logger = Logger.getLogger(MessageLogConrtol.class);

	@Autowired
	private MessageLogService messageLogService;

	/**
	 * 根据条码获取日志信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据ObjectId获取管控日志消息", notes = "根据ObjectId查询管控日志消息")
	@RequestMapping(value = "/messagelog/id", method = RequestMethod.GET, produces = { "application/json" })
	public String getControlMessageLogById(
			@ApiParam(required = true, name = "id", value = "ObjecId") @RequestParam String id) {
		List<ControlMessageLog> msgLogList = messageLogService.findById(id);
		return JSON.toJSONString(msgLogList);
	}

	/**
	 * 根据条码获取日志信息
	 * 
	 * @param barcode
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据条码获取管控日志消息", notes = "根据条码查询管控日志消息")
	@RequestMapping(value = "/messagelog", method = RequestMethod.GET, produces = { "application/json" })
	public String getControlMessageLogByBarcode(
			@ApiParam(required = true, name = "barcode", value = "barcode") @RequestParam String barcode) {
		List<ControlMessageLog> msgLogList = messageLogService.findByBarcode(barcode);
		return JSON.toJSONString(msgLogList);
	}

	/**
	 * 保存日志信息
	 * 
	 * @param controlMessageLog
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "保存管控日志消息", notes = "保存管控日志消息")
	@RequestMapping(value = "/messagelog", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public String saveControlMessageLog(
			@ApiParam(required = true, name = "管控日志消息", value = "controlMessageLog") @RequestBody ControlMessageLog controlMessageLog)
			throws InvalidRequestException {
		if (StringUtils.isEmpty(controlMessageLog.getBarcode()) || (controlMessageLog.getCreateTime() == null)
				|| controlMessageLog.getOutMqTime() == null || StringUtils.isEmpty(controlMessageLog.getQueueName())
				|| controlMessageLog.getIntoRedisTime() == null || StringUtils.isEmpty(controlMessageLog.getLog())
				|| StringUtils.isEmpty(controlMessageLog.getState())) {
			throw new InvalidRequestException(
					"barcode,createTime,outMqTime,queueName,intoRedisTime,log,state 输入参数中 不能为 Null !!! ");
		}
		ControlMessageLog msgLog = messageLogService.saveMsgLogMongo(controlMessageLog);
		return JSON.toJSONString(msgLog);
	}

}