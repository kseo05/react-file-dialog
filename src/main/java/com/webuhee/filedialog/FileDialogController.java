package com.webuhee.filedialog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/file-dialog")
public class FileDialogController {
	@Autowired
	FileDialogService svc;

	@RequestMapping("/storage/**")
	@ResponseBody
	public String getFileList(HttpServletRequest request) {
		String path = request.getRequestURI();
		ObjectMapper objMapper = new ObjectMapper();
		
		return path;
	}
}
