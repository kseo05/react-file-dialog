package com.webuhee.filedialog;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/file-dialog")
public class FileDialogController {
	@Autowired
	FileDialogService svc;
	
	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping("/storage/**")
	@ResponseBody
	public String getFileList(HttpServletRequest request) throws Exception {
		String result;
		String path = request.getRequestURI().substring("/file-dialog/storage".length());
		path = URLDecoder.decode(path, "UTF-8");
		
		try {
			result = objectMapper.writeValueAsString(svc.getFileList(path));
		} catch (Exception e) {
			throw e;
		}
		
		return result;
	}
}
