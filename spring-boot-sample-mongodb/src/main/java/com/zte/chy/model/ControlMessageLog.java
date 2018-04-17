package com.zte.chy.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ControlMessageLog {

	// 主键
	@Id
	private ObjectId id;

	// 条码
	@Indexed
	private String barcode;

	// 入库时间
	@Indexed
	private Date createTime;

	// 出MQ时间
	private Date outMqTime;

	// 队列名称
	private String queueName;

	// 入redis时间
	private Date intoRedisTime;

	// 入参日志
	private String log;

	// 条码最终运行的状态
	private String state;

	// 备注
	private String remark;

}
