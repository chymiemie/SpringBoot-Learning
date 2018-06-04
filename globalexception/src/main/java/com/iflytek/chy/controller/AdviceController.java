package com.iflytek.chy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iflytek.chy.exception.MyException;

@RestController
@RequestMapping(value = "/advice1")
public class AdviceController {

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String test1() {
		throw new MyException("advice1 - exception1");
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String test2() {
		throw new MyException("advice1 - exception2");
	}
}
