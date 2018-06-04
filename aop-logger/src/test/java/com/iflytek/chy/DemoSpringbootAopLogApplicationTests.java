package com.iflytek.chy;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iflytek.chy.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class DemoSpringbootAopLogApplicationTests {

	@Autowired
	private TestService testService;

	@Test
	public void contextLoads() {
		Map<String, Object> params = new HashMap<>();
		params.put("key1", "v1");
		params.put("key2", "v2");

		testService.insert(params, "000");
		testService.update("name", "id");
		testService.delete("leftso");
		testService.doError("leftso.com");
	}
}
