package com.note.common.freemarker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1 @ExceptionHandler 注解修饰的方法可以放在普通类中，也可以放在切面类中(@ControllerAdvice 注解修饰的类)。前者表示只处理当前类的异常，后者表示处理全局的异常。
 * 2 @ExceptionHandler 注解修饰的方法参数中，不能有Map，否则会提示：。若希望把异常信息返回给前端，可以使用ModelAndView
 * 3 @ExceptionHandler 注解修饰的多个方法中，优先级原则是就近原则(和异常精度越近的异常，优先执行)。
 */
@ControllerAdvice
public class StudyExceptionHandlerAdvice {
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleArithmeticException(Exception exception){
		System.out.println("ArithmeticException 出异常了: " + exception);
		ModelAndView mv = new ModelAndView("exception");
		mv.addObject("exception", "ArithmeticException 出异常了: " + exception);
		return mv;
	}
	
	/*@ExceptionHandler({RuntimeException.class})
	public ModelAndView handleRuntimeException(Exception exception){
		System.out.println("RuntimeException 出异常了: " + exception);
		ModelAndView mv = new ModelAndView("exception");
		mv.addObject("exception", "RuntimeException 出异常了: " + exception);
		return mv;
	}*/
	
}
