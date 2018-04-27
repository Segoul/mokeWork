package com.moke.mokeWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moke.mokeWork.service.TestService;

/**
 * 测试类
 * @author Segoul
 *
 */

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
}
