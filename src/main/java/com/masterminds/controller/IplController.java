package com.masterminds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.entity.UserInfo;
import com.masterminds.service.IplService;

@RestController
@RequestMapping("ipl")
public class IplController {
	
	@Autowired
	IplService iplService;

	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping
	public ModelAndView home() {
		return new ModelAndView("ipl_home");
	}
	
	@GetMapping("logout")
	public ModelAndView logout() {
		return new ModelAndView("ipl_home");
	}
	
	@GetMapping("register")
	public ModelAndView register() {
		return new ModelAndView("user");
	}
	
	@PostMapping("save")
	public String saveUserInfo(@RequestBody String userInfoStr) {
		System.out.println("userInfoStr: " + userInfoStr);
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserInfo userInfo = mapper.readValue(userInfoStr, UserInfo.class);
			if (userInfo != null) {
				System.out.println("UserInfo: " + mapper.writeValueAsString(userInfo));
				iplService.saveOrUpdate(userInfo);
				result = mapper.writeValueAsString(userInfo);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("user/{id}/view")
	public ModelAndView getUserById(@PathVariable Long id) {
		ModelAndView userView = new ModelAndView("profile");
		UserInfo userInfo = iplService.getById(id);
		userView.addObject("userData", userInfo);
		return userView;
	}
	
	@GetMapping("user/{id}/edit")
	public ModelAndView editUserById(@PathVariable Long id) {
		ModelAndView userView = new ModelAndView("user");
		UserInfo userInfo = iplService.getById(id);
		userView.addObject("userData", userInfo);
		return userView;
	}
	
	@GetMapping("users")
	public ModelAndView getAllUsers() throws JsonProcessingException {
		ModelAndView usersView = new ModelAndView("users");
		List<UserInfo> usersInfo = iplService.getAll();
		System.out.println("Users: " + mapper.writeValueAsString(usersInfo));
		usersView.addObject("usersData", usersInfo);
		return usersView;
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteByUserId(@PathVariable Long id) {
		try {
			iplService.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@GetMapping("rules")
	public ModelAndView getRules() {
		return new ModelAndView("rules");
	}
	
	@GetMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("authenticate")
	public Long authenticate(@RequestParam String username, @RequestParam String password) {
		UserInfo user = iplService.getByUsernameAndPassword(username, password);
		if (user != null) {
			return user.getId();
		}
		return 0L;
	}
	
}
