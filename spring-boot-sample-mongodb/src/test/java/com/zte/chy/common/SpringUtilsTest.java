package com.zte.chy.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zte.chy.App;
import com.zte.chy.mongodb.service.MaterialService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class SpringUtilsTest {

	@Test
	public void test() {
		MaterialService materialService = (MaterialService) SpringUtils.getApplicationContext()
				.getBean("materialService");
		System.out.println(materialService);
	}

}
