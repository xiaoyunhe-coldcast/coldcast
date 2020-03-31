package com.coldcast.controller;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.bean.user;
@RestController
public class webchartController {
	
	@GetMapping("/login")
	public Object getRequest() {
		Map<String, Object> map = new Hashtable<String, Object>();
		List<user> list1 = new ArrayList<user>();
		List<user> list2 = new ArrayList<user>();
		user user = new user();
		user.setAge(1);
		user.setName("ee");
		list1.add(user);
		list2.add(user);
		map.put("list1", list1);
		map.put("list2", list2);
		return map;
	}
	
}
