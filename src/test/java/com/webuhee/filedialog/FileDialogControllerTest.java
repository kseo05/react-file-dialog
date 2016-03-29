package com.webuhee.filedialog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.webuhee.filedialog.FileDialogController;
import com.webuhee.filedialog.FileDialogService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class FileDialogControllerTest {
	// TODO : NewsApiControllerTest 리마인드.
	private MockMvc mockMvc;
	
	@InjectMocks FileDialogController con = new FileDialogController();
	@Mock FileDialogService svc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(con).build();
	}
	
	@Test
	public void shouldGetFileListInDirectory() throws Exception {
		this.mockMvc.perform(get("/file-dialog/tree")).andExpect(status().isOk());
		this.mockMvc.perform(get("/file-dialog/tree/")).andExpect(status().isOk());
		this.mockMvc.perform(get("/file-dialog/tree/docs")).andExpect(status().isOk());
		this.mockMvc.perform(get("/file-dialog/tree/docs/")).andExpect(status().isOk());
		
		this.mockMvc.perform(get("/file-dialog/tree/../../../../../../root")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/tree/../../../../../../root/")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/tree///root")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/tree///root/")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/tree//!@$%^&*()_+``[]{}:;\"'<,>.?/��/root")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/tree//!@$%^&*()_+``[]{}:;\"'<,>.?/��/root/")).andExpect(status().is4xxClientError());
	}
}
