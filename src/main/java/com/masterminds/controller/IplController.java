package com.masterminds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.UserInfo;
import com.masterminds.service.IplService;

@RestController
//@RequestMapping("ipl")
public class IplController {
	
	@Autowired
	IplService iplService;

	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping({"/", "/ipl"})
	public ModelAndView origin() {
		return new ModelAndView("ipl_home");
	}
	
//	@GetMapping("/ipl")
//	public ModelAndView home() {
//		return new ModelAndView("ipl_home");
//	}
	
	@GetMapping("ipl/logout")
	public ModelAndView logout() {
		return new ModelAndView("ipl_home");
	}
	
	@GetMapping("ipl/register")
	public ModelAndView register() {
		return new ModelAndView("user");
	}
	
	@PostMapping("ipl/save")
	public String saveUserInfo(@RequestBody String userInfoStr) {
		System.out.println("userInfoStr: " + userInfoStr);
		String result = "";
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
	
	@GetMapping("ipl/user/view/{id}")
	public ModelAndView getUserById(@PathVariable Long id) {
		ModelAndView userView = new ModelAndView("profile");
		UserInfo userInfo = iplService.getById(id);
		userView.addObject("userData", userInfo);
		return userView;
	}
	
	@GetMapping("ipl/user/edit/{id}")
	public ModelAndView editUserById(@PathVariable Long id) {
		ModelAndView userView = new ModelAndView("user");
		UserInfo userInfo = iplService.getById(id);
		userView.addObject("userData", userInfo);
		return userView;
	}
	
	@GetMapping("ipl/users")
	public ModelAndView getAllUsers() throws JsonProcessingException {
		ModelAndView usersView = new ModelAndView("users");
		List<UserInfo> usersInfo = iplService.getAll();
		System.out.println("Users: " + mapper.writeValueAsString(usersInfo));
		usersView.addObject("usersData", usersInfo);
		return usersView;
	}
	
	@DeleteMapping("ipl/delete/{id}")
	public String deleteByUserId(@PathVariable Long id) {
		try {
			iplService.deleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@GetMapping("ipl/rules")
	public ModelAndView getRules() {
		return new ModelAndView("rules");
	}
	
	@GetMapping("ipl/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("ipl/authenticate")
	public Long authenticate(@RequestParam String username, @RequestParam String password) {
		UserInfo user = iplService.getByUsernameAndPassword(username, password);
		if (user != null) {
			return user.getId();
		}
		return 0L;
	}

	@GetMapping("ipl/playersList")
	public ModelAndView getPlayers() throws JsonProcessingException {
		ModelAndView playersView = new ModelAndView("players");
		List<PlayerInfo> playersInfo = iplService.getAllPlayers();
		System.out.println("Players: " + mapper.writeValueAsString(playersInfo));
		playersView.addObject("playersData", playersInfo);
		return playersView;
	}
	
	@GetMapping("ipl/player")
	public ModelAndView addPlayer() {
		ModelAndView playerView = new ModelAndView("player");
		List<String> roles = new ArrayList<>();
		roles.add("Batsman");
		roles.add("Bowler");
		roles.add("All-Rounder");
		playerView.addObject("roles", roles);
		return playerView;
	}
	
	@GetMapping("ipl/player/edit/{id}")
	public ModelAndView editPlayer(@PathVariable Long id) {
		ModelAndView playerView = new ModelAndView("player");
		PlayerInfo playerInfo = iplService.getPlayerById(id);
		try {
			System.out.println("Player: " + mapper.writeValueAsString(playerInfo));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		playerView.addObject("playerData", playerInfo);
		return playerView;
	}
	
	@PostMapping("ipl/savePlayer")
	public String savePlayer(@RequestBody String playerInfoStr) {
		System.out.println("userInfoStr: " + playerInfoStr);
		String result = "";
		try {
			PlayerInfo playerInfo = mapper.readValue(playerInfoStr, PlayerInfo.class);
			if (playerInfo != null) {
				System.out.println("playerInfo: " + mapper.writeValueAsString(playerInfo));
				iplService.saveOrUpdate(playerInfo);
				result = mapper.writeValueAsString(playerInfo);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@DeleteMapping("ipl/player/delete/{id}")
	public String deletePlayerById(@PathVariable Long id) {
		try {
			iplService.deletePlayerById(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
}
