package com.yqx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yqx.entity.Student;

@Controller
public class StudentController {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@RequestMapping("/index")
	public String index(@RequestParam(value = "name", required = false, defaultValue = "admin") String name,
			Map<String, Object> map) {
		System.out.println("姓名:" + name);
		map.put("name", name);
		return "index";
	}

	@RequestMapping("/queryById1/{id}.html")
	public String queryById(@PathVariable("id") int id) {
		System.out.println("编号:" + id);
		return "index";
	}

	@RequestMapping("/print")
	public void print() {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			// out.write("hello 张三");
			out.write("{\"name\":\"张三\",\"sex\":\"男\"}");
			// {"name":"张三","sex":"男"}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/queryById")
	@ResponseBody
	public Student queryById() {
		Student s = new Student();
		s.setId(1);
		s.setName("张三");
		s.setUsername("zhangsan");
		s.setPassword("123");
		s.setSex(1);
		s.setAge(20);
		s.setBirthday(new Date());
		s.setCreatTime(new Timestamp(System.currentTimeMillis()));
		return s;
	}

	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Student> queryAll() {
		List<Student> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Student s = new Student();
			s.setId(i);
			s.setName("张三");
			s.setUsername("zhangsan");
			s.setPassword("123");
			s.setSex(1);
			s.setAge(20);
			s.setBirthday(new Date());
			s.setCreatTime(new Timestamp(System.currentTimeMillis()));
			list.add(s);
		}
		return list;
	}
	
	//@ModelAttribute:每次开始之前加载这个方法
	@ModelAttribute
	public void initData(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("初始化参数");
		this.request = request;
		this.response = response;
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile files[]) {
		String path = request.getServletContext().getRealPath("/upload/");
		System.out.println("文件路径:" + path);

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				System.out.println("文件名称:" + file.getOriginalFilename());
				try {
					file.transferTo(new File(path, file.getOriginalFilename()));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "index";
	}

	@RequestMapping("/downloadFile")
	public void downloadFile(String fileName) {
		
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path, fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + "");
			byte[] b = new byte[2048];
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				os.write(b, 0, len);
			}
			fis.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String fileName) throws Exception {
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path, fileName);
		
		HttpHeaders headers = new HttpHeaders();
		//通知浏览器以attachment（下载方式）打开文件
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
		//application/cotet-stream,二进制流数据(最常见的文件下载)
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
	
	@ExceptionHandler
	public ModelAndView exceptionHandler(Exception ex) {
		//创建视图模型对象，指定跳转的视图界面
		ModelAndView mv = new ModelAndView("redirect:exception.jsp");
		//添加数据对象
		mv.addObject("exception", ex);
		return mv;
	}
}
