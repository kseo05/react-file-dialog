package com.webuhee.filedialog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/file-dialog")
public class FileDialogController {
	@RequestMapping("/")
	@ResponseBody
	String home() {
		   return "Hello World!";
	}
}
