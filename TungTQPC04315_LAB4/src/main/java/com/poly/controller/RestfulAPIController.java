package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Student;
import com.poly.bean.StudentMap;
import com.poly.dao.StudentDAO;

@Controller
public class RestfulAPIController {
	
	@Autowired
	StudentDAO dao;

	@RequestMapping("/student/index")
	public String getIndex(Model m) {
		Student student = new Student("","",0.0,true,"vn");
		m.addAttribute("form", student);
		StudentMap map = dao.findAll();
		m.addAttribute("items", map);
		return "student/index";
	}
	
	@RequestMapping("/student/edit/{key}")
	public String edit(Model m, @PathVariable("key") String key) {
		m.addAttribute("key", key);
		Student student = dao.findByKey(key);
		m.addAttribute("form", student);
		StudentMap map = dao.findAll();
		m.addAttribute("items", map);
		return "student/index";
	}
	
	@RequestMapping("/student/add")
	public String Add(Student data) {
		dao.add(data);
		return "redirect:/student/index";
	}
	
	@RequestMapping("/student/update/{key}")
	public String update(@PathVariable("key") String key, Student data) {
		dao.update(key, data);
		return "redirect:/student/edit/" + key;
	}
	
	@RequestMapping("/student/delete/{key}")
	public String delete(@PathVariable("key") String key) {
		dao.delete(key);
		return "redirect:/student/index";
	}
	
	
	
}
