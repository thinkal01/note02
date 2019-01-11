package com.note.common.freemarker.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudyExceptionController {
	
	@RequestMapping("/exception")
	public String exception(){
		return "exception";
	}
	
	@RequestMapping("/simpleMappingExceptionResolver")
	public String simpleMappingExceptionResolver(@RequestParam("num") int num){
		String [] args = new String[10];
		System.out.println("通过配置bean，来处理某一种异常导致的所有问题。" + args[num]);
		return "exception";
	}
	
	@RequestMapping(value="/testResponseStatus")
	public String testResponseStatus(@RequestParam("num") Integer num){
		System.out.println("@ResponseStatus 自定义异常");
		if (0 == num) {
			throw new ResponseStatusException();
		}
		return "exception";
	}
	
	@RequestMapping("/testExceptionHandler")
	public String testExceptionHandler(@RequestParam("num") Integer num){
		System.out.println("@ExceptionHandler - result: " + (10 / num));
		return "exception";
	}

}
