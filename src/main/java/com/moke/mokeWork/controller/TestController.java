package com.moke.mokeWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moke.mokeWork.service.TestService;

/**
 * 测试类
 * @author Segoul
 *
 */

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
}
