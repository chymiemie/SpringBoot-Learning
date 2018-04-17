package com.zte.chy;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.zte.chy.model.Material;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.service.MaterialService;

public class MaterialServiceFindByMaterialTest {
	@Autowired
	private MaterialService materialService;

	@Test
	public void test() {
		String materialCode = "122098231274";
		System.out.println("==========>    materialService.findByMaterial(materialCode);");
		List<Material> list = materialService.findByMaterial(materialCode);
		list.stream().forEachOrdered(System.out::println);

		System.out.println("==========>   materialService.findByMaterial(materialPage);");
		MaterialPage materialPage = new MaterialPage();
		materialPage.setMaterialcode(materialCode);
		Page<Material> page = materialService.findByMaterial(materialPage);
		page.forEach(System.out::println);

	}
}
