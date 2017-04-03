package com.springmvc.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.employee.service.EmployeeService;
import com.springmvc.entity.Employee;

@Controller
@RequestMapping("/user")
public class EmployeeController {

	@Resource
	private EmployeeService employeeService;

	
	public EmployeeController() {
		super();
		System.out.println("controller--------------------------------");
	}

	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		
		request.setAttribute("userList", employeeService.getAllEmployee());	
		return "/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(Integer id,HttpServletRequest request){
		
		request.setAttribute("user", employeeService.findById(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(Employee user,HttpServletRequest request){
		
		employeeService.save(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(Integer id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		Employee employee=employeeService.findById(id);
		employeeService.delete(employee);
		result = "{\"result\":\"success\"}";
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(Employee user,HttpServletRequest request){
		System.out.println(user);
		employeeService.update(user);
		return "redirect:/user/getAllUser";
	}
}