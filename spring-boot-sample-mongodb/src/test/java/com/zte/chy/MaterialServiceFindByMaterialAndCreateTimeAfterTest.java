package com.zte.chy;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.zte.chy.model.Material;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.service.MaterialService;

public class MaterialServiceFindByMaterialAndCreateTimeAfterTest {
	@Autowired
	private MaterialService materialService;

	@Test
	public void test() throws ParseException {
		String barcode = "705610400211";
		String startTime = "2017-02-27 10:50:49.227";
		String endTime = "2017-03-27 10:50:49.227";
		String materialcode = "122098231274";
		System.out.println("==========>   materialService.findByMaterialAndCreateTimeAfter(materialcode, startTime);");
		List<Material> list = materialService.findByMaterialAndCreateTimeAfter(materialcode, startTime);
		list.parallelStream().forEachOrdered(System.out::println);

		System.out.println("==========>   materialService.findByMaterialAndCreateTimeAfter(materialPage);");
		MaterialPage materialPage = new MaterialPage(materialcode, startTime, endTime, barcode);
		Page<Material> page = materialService.findByMaterialAndCreateTimeAfter(materialPage);
		page.forEach(System.out::println);

	}
}
