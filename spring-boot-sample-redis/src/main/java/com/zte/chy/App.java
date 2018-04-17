package com.zte.chy;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Administrator
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Resource(name = "redisTemplate")
	ValueOperations<String, String> valOpts;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOpts;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOpts;

	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> zSetOpts;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOpts;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		//template.setValueSerializer(jackson2JsonRedisSerializer);
		//template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer());

		template.afterPropertiesSet();
		return template;
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setId("111111");
		person.setName("AAAA");
		person.setAge(1111);

		String jsonStringPerson = JSON.toJSONString(person);
		valOpts.set("value", jsonStringPerson);
		System.out.println("valOpts操作成功，且只能操作String类型的key-value");

		List<String> list = new ArrayList<String>();
		list.add("18900000000");
		list.add("19000000000");
		list.add("19100000000");
		list.add("19200000000");
		String jsonStringList = JSON.toJSONString(list);
		listOpts.leftPush("list1", jsonStringList);
		listOpts.leftPushAll("list2", list);

		Set<String> set = new HashSet<String>();
		set.add("18900000000");
		set.add("19000000000");
		set.add("19100000000");
		set.add("19200000000");
		String jsonStringSet = JSON.toJSONString(set);
		setOpts.add("set1", jsonStringSet);
		String[] arr = new String[set.size()];
		arr = set.toArray(arr);
		setOpts.add("set2", arr);

		String jsonStringMap = JSON.toJSONString(person);
		// 第一个参数是存入redis中的key(由redistemplate定义为String),
		// 第二个参数和第三个参数是代表整个hashMap，存入redis中的value(由redistemplate定义为String)
		// 第二个参数表示hashMap中的key值，
		// 第三个参数表示hashMap中的vaule值
		hashOpts.put("hashMap1", "111111", jsonStringMap);
		Map<String, String> allMap = new HashMap<String, String>();
		allMap.put("cid", "WE50000000CU");
		allMap.put("amount", "699");
		allMap.put("currency", "USD");
		allMap.put("tag", "华为科技");
		allMap.put("type", "1");
		allMap.put("content", "1");
		allMap.put("address", "未来城");
		allMap.put("person", jsonStringPerson);
		hashOpts.putAll("hashMap2", allMap);
		redisTemplate.opsForHash().putAll("hashMap3", allMap);
		/*Map<String, Map<String, String>> mapList = new HashMap<String, Map<String,String>>();
		Map<String, String> map = new HashMap<String,String>();
		map.put("tag", "华为科技");
		mapList.put("mapList", map);
		hashOpts.putAll("mapList", mapList);*/
		/*List<Map<String, String>> listMap = new ArrayList<>();
		listMap.add(allMap);
		listOpts.leftPushAll("listMap", listMap);*/
	}
}
