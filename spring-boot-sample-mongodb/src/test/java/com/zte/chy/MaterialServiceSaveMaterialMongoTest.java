package com.zte.chy;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zte.chy.model.Material;
import com.zte.chy.mongodb.service.MaterialService;

public class MaterialServiceSaveMaterialMongoTest {
	@Autowired
	private MaterialService materialService;

	@Test
	public void test() {
		String barcode = "705610400210";
		String materialcode = "122098231274";
		Date createTime = new Date();
		Material material = new Material(barcode, materialcode, createTime);
		Material materialResponse = materialService.saveMaterialMongo(material);
		System.out.println(materialResponse.toString());

	}
}
