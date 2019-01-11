package com.note.common.freemarker.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	// 国际化
	
	/**
	 * 国际化
	 * 第一步，在SpringMVC配置文件中，配置 SessionLocaleResolver 和 LocaleChangeInterceptor
	 * 第二步，准备语言文件，i18n_en_US.properties 和 i18n_zh_CN.properties
	 * 第三步，目标方法中，参数加入Locale对象。
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(Locale locale) {
//		String val = messageSource.getMessage("file", null, locale);
//		System.out.println(val);
		return "fileUpload";
	}
	
	// MultipartFile 上传文件必用的变量类型
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file,
			Map<String, Object> map, HttpServletRequest request) {
		InputStream in = null;
		OutputStream out = null;
		String fileName = file.getOriginalFilename(); // 获取文件名
		try {
			String realPath = request.getServletContext().getRealPath("uploads/");
			in = file.getInputStream();
            byte[] buffer = new byte[1024];
            String filePath = realPath + "/" + fileName; // 文件上传路径
            out = new FileOutputStream(filePath);  
            int len = 0;  
            while ((len = in.read(buffer)) != -1) {  
                out.write(buffer, 0, len);
            }  
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.close();
				}
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		map.put("fileName", fileName);
		return "fileUpload";
	}
	
	// 不适合大文件的下载，适用于简单的下载场景。
	@RequestMapping("/downLoadFile")
	public ResponseEntity<byte[]> downLoadFile(@RequestParam("fileName") String fileName, HttpSession session) {
		byte [] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = null;
		ResponseEntity<byte[]> response = null;
		try {
			in = servletContext.getResourceAsStream("/uploads/"+fileName);
			body = new byte[in.available()];
			in.read(body);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename="+fileName);
			HttpStatus statusCode = HttpStatus.OK;
			response = new ResponseEntity<byte[]>(body, headers, statusCode);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	
}
