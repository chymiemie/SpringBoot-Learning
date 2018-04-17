package com.zte.chy.control;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zte.chy.model.Material;
import com.zte.chy.mongodb.service.MaterialService;
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
@Api(tags = "物料代码与条码相关操作的Controller")
public class MaterialControl {

	@Autowired
	private MaterialService materialService;

	static final Logger logger = Logger.getLogger(MaterialControl.class);

	/**
	 * 保存物料代码与条码的对应关系 consumes：
	 * 指定处理请求的提交内容类型（Content-Type），例如application/json,text/html;
	 * produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
	 * 参考地址：http://blog.csdn.net/walkerjong/article/details/7994326
	 * 
	 * @param material
	 * @return
	 * @throws Exception
	 *             improved
	 */
	@ApiOperation(value = "保存条码与物料代码对应关系", notes = "根据传入 MaterialMongo对象来保存barcode与materialcode的关系")
	@RequestMapping(value = "/material", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public String saveMaterial(
			@ApiParam(required = true, name = "条码和物料代码对应关系", value = "materialMongo") @RequestBody Material materialMongo)
			throws InvalidRequestException {
		if (StringUtils.isEmpty(materialMongo.getBarcode()) || StringUtils.isEmpty(materialMongo.getMaterial())
				|| (materialMongo.getCreateTime() == null)) {
			throw new InvalidRequestException("barcode , materialcode , creatTime 不能为  Null !!!!");
		}
		Material material = materialService.saveMaterialMongo(materialMongo);
		return JSON.toJSONString(material);

	}

	/**
	 * 根据条码获取物料代码
	 * 
	 * @param barcode
	 * @param request
	 * @return improved
	 */
	@ApiOperation(value = "根据条码获取物料代码对应关系", notes = "根据条码查询对应的物料代码的关系")
	@RequestMapping(value = "/material/barcode", method = RequestMethod.GET, produces = { "application/json" })
	public String getMaterialByBarcode(
			@ApiParam(required = true, name = "barcode", value = "barcode") @RequestParam String barcode) {
		List<Material> material = materialService.findByBarcode(barcode);
		return JSON.toJSONString(material);
	}

	/**
	 * 返回物料代码和条码的对应关系
	 * 
	 * @param material
	 * @return improved
	 * @throws ParseException
	 * @throws Exception
	 */
	@ApiOperation(value = "根据输入条码、物料代码、时间三者进行查询", notes = "根据输入的条码、物料代码、起始时间与结束时间进行查询")
	@RequestMapping(value = "/material", method = RequestMethod.GET, produces = { "application/json" })
	public String getMaterialList(
			@ApiParam(required = true, name = "materialcode", value = "物料代码") @RequestParam String materialcode,
			@ApiParam(required = false, name = "barcode", value = "条码") @RequestParam(required = false) String barcode,
			@ApiParam(required = false, name = "startTime", value = "起始时间") @RequestParam(required = false) String startTime,
			@ApiParam(required = false, name = "endTime", value = "结束时间") @RequestParam(required = false) String endTime)
			throws InvalidRequestException, ParseException {
		if (StringUtils.isEmpty(materialcode)) {

			throw new InvalidRequestException("物料代码 materialcode 不能为 Null !!!");
			// 按条码查询
		} else if (StringUtils.isNotEmpty(barcode)) {
			List<Material> list = materialService.findByBarcode(barcode);
			return JSON.toJSONString(list);
		}
		// 根据 起始时间 和 结束时间 来查询
		else if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
			List<Material> list = materialService.findByMaterialAndCreateTimeRange(materialcode, startTime, endTime);
			return JSON.toJSONString(list);
		}
		// 按照物料代码和起始时间查询
		else if (StringUtils.isNotEmpty(startTime)) {
			List<Material> list = materialService.findByMaterialAndCreateTimeAfter(materialcode, startTime);
			return JSON.toJSONString(list);
		}
		// 按照物料代码和 截止时间查询
		else if (StringUtils.isNotEmpty(endTime)) {

			List<Material> list = materialService.findByMaterialAndCreateTimeBefore(materialcode, endTime);
			return JSON.toJSONString(list);
		}
		// 其他情况直接按照物料代码查询
		else {
			List<Material> list = materialService.findByMaterial(materialcode);
			return JSON.toJSONString(list);
		}
	}

	/**
	 * 根据物料代码 与时间的分布 来查询 物料代码和条码的对应关系
	 * 
	 * @param materialPage
	 * @return
	 * @throws ParseException
	 */
	// 后台分页功能显示，后期如果需要则可取消注释
	/*
	 * @RequestMapping(value = "/material/search", method = RequestMethod.POST,
	 * produces = "application/json") public Page<MaterialMongo>
	 * getMaterial(@RequestBody MaterialPage materialPage) { try { // 判断物料代码是否为空
	 * if (StringUtils.isEmpty(materialPage.getMaterialcode())) { return null; }
	 * else if (StringUtils.isNoneEmpty(materialPage.getBarcode())) {
	 * Page<MaterialMongo> page = materialService.findByBarcode(materialPage);
	 * return page; } // 根据 起始时间 和 结束时间 来查询 else if
	 * (StringUtils.isNotEmpty(materialPage.getStartTime()) &&
	 * StringUtils.isNotEmpty(materialPage.getEndTime())) { Page<MaterialMongo>
	 * page = materialService.findByMaterialAndCreateTimeRange(materialPage);
	 * return page; } // 按照物料代码和起始时间查询 else if
	 * (StringUtils.isNotEmpty(materialPage.getStartTime())) {
	 * Page<MaterialMongo> page =
	 * materialService.findByMaterialAndCreateTimeAfter(materialPage); return
	 * page; } // 按照物料代码和 截止时间查询 else if
	 * (StringUtils.isNotEmpty(materialPage.getEndTime())) { Page<MaterialMongo>
	 * page = materialService.findByMaterialAndCreateTimeBefore(materialPage);
	 * return page; } // 其他情况直接按照物料代码查询 else { Page<MaterialMongo> page =
	 * materialService.findByMaterial(materialPage); return page; } } catch
	 * (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return null; }
	 */

}