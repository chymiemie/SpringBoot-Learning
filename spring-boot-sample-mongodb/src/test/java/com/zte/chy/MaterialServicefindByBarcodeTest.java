package com.zte.chy;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zte.chy.App;
import com.zte.chy.model.Material;
import com.zte.chy.model.MaterialPage;
import com.zte.chy.mongodb.service.MaterialService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MaterialServicefindByBarcodeTest {
	@Autowired
	private MaterialService materialService;

	@Test
	public void test() throws Exception {
		String barcode = "705610400210";

		System.out.println("==========>    materialService.findByBarcode(barcode);");
		List<Material> list = materialService.findByBarcode(barcode);
		list.stream().forEach(System.out::println);
		MaterialPage materialPage = new MaterialPage();
		materialPage.setBarcode(barcode);

		System.out.println("==========>   materialService.findByBarcode(materialPage);");
		Page<Material> page = materialService.findByBarcode(materialPage);
		page.forEach(System.out::println);

	}

}
