package com.moke.mokeWork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

/**
 * 主页相关controller
 * @author Segoul
 *
 */

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@ApiOperation(value = "跳转到主页",httpMethod = "GET", notes="跳转到主页")
	@RequestMapping(value = "/enterIndex",method = RequestMethod.GET)
	public String enterIndex(){
		System.out.println("123456");
		return "index";
	}
}
