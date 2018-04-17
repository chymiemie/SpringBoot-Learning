package com.zte.chy.mongodb.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zte.chy.common.CommonUtils;
import com.zte.chy.model.Material;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	/**
	 * 保存条码和物料代码的对应关系
	 * 
	 * @param material
	 * @return
	 */
	public Material saveMaterialMongo(Material material) {
		return materialRepository.save(material);
	}

	/**
	 * 根据条码查询物料代码的对应关系
	 * 
	 * @param barcode
	 * @return
	 */
	public List<Material> findByBarcode(String barcode) {
		return materialRepository.findByBarcode(barcode);
	}

	/**
	 * 根据物料代码查询条码的对应关系
	 * 
	 * @param materialCode
	 * @return
	 */
	public List<Material> findByMaterial(String materialCode) {
		return materialRepository.findByMaterial(materialCode);
	}

	/**
	 * 根据【物料代码】、【 开始时间】、【截止时间】 查询 物料代码 与条码的对应关系
	 * 
	 * @param materialCode
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public List<Material> findByMaterialAndCreateTimeRange(String materialCode, String beginTime, String endTime)
			throws ParseException {
		List<Material> list = materialRepository.findByMaterialAndCreateTimeRange(materialCode,
				CommonUtils.getFormatDate(beginTime), CommonUtils.getFormatDate(endTime));
		return list;
	}

	/**
	 * // 根据【物料代码】、【 开始时间】来查询 物料代码和 条码的对应关系
	 * 
	 * @param materiaCode
	 * @param beginTime
	 * @return
	 * @throws ParseException
	 */

	public List<Material> findByMaterialAndCreateTimeAfter(String materiaCode, String beginTime) throws ParseException {
		List<Material> list = materialRepository.findByMaterialAndCreateTimeAfter(materiaCode,
				CommonUtils.getFormatDate(beginTime));
		return list;
	}

	/**
	 * // 根据【物料代码】、【 截止时间】来查询 物料代码和条码的对应关系
	 * 
	 * @param materiaCode
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public List<Material> findByMaterialAndCreateTimeBefore(String materiaCode, String endTime) throws ParseException {
		List<Material> list = materialRepository.findByMaterialAndCreateTimeBefore(materiaCode,
				CommonUtils.getFormatDate(endTime));
		return list;
	}

	/**
	 * // 根据【物料代码】、【 开始时间】、【截止时间】， 来查询 物料代码和 条码的对应关系 带分页功能
	 * 
	 * @param materialPage
	 * @return
	 * @throws ParseException
	 */
	public Page<Material> findByMaterialAndCreateTimeRange(MaterialPage materialPage) throws ParseException {
		Pageable pageRequest = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		Page<Material> page = materialRepository.findByMaterialAndCreateTimeRange(materialPage.getMaterialcode(),
				CommonUtils.getFormatDate(materialPage.getStartTime()),
				CommonUtils.getFormatDate(materialPage.getEndTime()), pageRequest);
		return page;

	}

	/**
	 * // 根据【物料代码】、【 开始时间】， 来查询 物料代码和 开始时间的对应关系 带分页功能
	 * 
	 * @param materialPage
	 * @return
	 * @throws ParseException
	 */
	public Page<Material> findByMaterialAndCreateTimeAfter(MaterialPage materialPage) throws ParseException {
		Pageable pageRequest = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		Page<Material> page = materialRepository.findByMaterialAndCreateTimeAfter(materialPage.getMaterialcode(),
				CommonUtils.getFormatDate(materialPage.getStartTime()), pageRequest);

		return page;
	}

	/**
	 * // 根据【物料代码】、【截止时间】， 来查询 物料代码和 条码的对应关系 带分页功能
	 * 
	 * @param materialPage
	 * @return
	 * @throws ParseException
	 */
	public Page<Material> findByMaterialAndCreateTimeBefore(MaterialPage materialPage) throws ParseException {
		Pageable pageRequest = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		Page<Material> page = materialRepository.findByMaterialAndCreateTimeBefore(materialPage.getMaterialcode(),
				CommonUtils.getFormatDate(materialPage.getEndTime()), pageRequest);

		return page;

	}

	/**
	 * 根据条码查询 条码和物料代码的对应关系 分页功能
	 * 
	 * @param materialPage
	 * @return
	 * @throws ParseException
	 */
	public Page<Material> findByBarcode(MaterialPage materialPage) throws ParseException {
		Pageable pageRequest = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		Page<Material> page = materialRepository.findByBarcode(materialPage.getBarcode(), pageRequest);
		return page;

	}

	/**
	 * // 根据物料代码查询 条码和物料代码的对应关系 分页功能
	 * 
	 * @param materialPage
	 * @return
	 */
	public Page<Material> findByMaterial(MaterialPage materialPage) {
		Pageable pageRequest = new PageRequest(materialPage.getPageNum(), materialPage.getPageSize());
		Page<Material> page = materialRepository.findByMaterial(materialPage.getMaterialcode(), pageRequest);
		return page;
	}

}
