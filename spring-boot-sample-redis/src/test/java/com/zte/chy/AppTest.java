package com.zte.chy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

	@Autowired(required = true)
	private RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redisTemplate")
	private ListOperations<String, List<String>> listOperations;
	
	
	@Test
	public void testListOperations(){
		List<List<String>> lists = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("18900000000");  
		list.add("19000000000");  
		list.add("19100000000");  
		list.add("19200000000");
		list.add("19300000000");
		list.add("19400000000");
		list.add("19500000000");
		list.add("19600000000");
		list.add("19700000000");
		
		lists.add(list);
		listOperations.leftPushAll("list1", lists);
		redisTemplate.opsForList().leftPushAll("list2", lists);
		
	}
	
}