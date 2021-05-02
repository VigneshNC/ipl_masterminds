package com.masterminds.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.entity.BidParticipantInfo;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.PointsTable;
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
	public Map<String, String> saveUserInfo(@RequestBody String userInfoStr) throws JsonMappingException, JsonProcessingException {
		System.out.println("userInfoStr: " + userInfoStr);
		Map<String, String> resultMap = new HashMap<>();
		UserInfo userInfo = mapper.readValue(userInfoStr, UserInfo.class);
		if (userInfo != null) {
			List<UserInfo> users = iplService.getAll();
			if (users != null && users.size() > 0) {
				for (UserInfo user : users) {
					if (user.getUsername() != null && user.getUsername().equalsIgnoreCase(userInfo.getUsername())) {
						resultMap.put("status", "duplicate");
						resultMap.put("reason", "username");
						return resultMap;
					} else if (user.getTeamName() != null && user.getTeamName().equalsIgnoreCase(userInfo.getTeamName())) {
						resultMap.put("status", "duplicate");
						resultMap.put("reason", "teamname");
						return resultMap;
					}
				}
			}
			
			userInfo.setRole("requestor");
			System.out.println("UserInfo: " + mapper.writeValueAsString(userInfo));
			try {
				iplService.saveOrUpdate(userInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String userDataStr = mapper.writeValueAsString(userInfo);
			resultMap.put("status", "success");
			resultMap.put("userInfo", userDataStr);
		}
		return resultMap;
	}
	
	@GetMapping("ipl/user/view/{id}")
	public ModelAndView getUserById(@PathVariable Long id) {
		ModelAndView userView = new ModelAndView("profile");
		UserInfo userInfo = iplService.getById(id);
		if (userInfo != null) {
			userView.addObject("userData", userInfo);
			String userOrAdmin = "user";
			if ("admin".equals(userInfo.getUsername())) {
				userOrAdmin = "admin";
			}
			userView.addObject("userOrAdmin", userOrAdmin);
			userView.addObject("participantId", userInfo.getId());
		}
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
		List<UserInfo> authenticatedUsers = new ArrayList<>();
		if (usersInfo != null && usersInfo.size() > 0) {
			for (UserInfo userInfo : usersInfo) {
				if ("participant".equals(userInfo.getRole())) {
					authenticatedUsers.add(userInfo);
				}
			}
		}
		System.out.println("Users: " + mapper.writeValueAsString(authenticatedUsers));
		usersView.addObject("usersData", authenticatedUsers);
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
			if ("requestor".equals(user.getRole())) {
				return 1L;
			}
			return user.getId();
		}
		return 0L;
	}

	@GetMapping("ipl/playersList")
	public ModelAndView getPlayers() throws JsonProcessingException {
		ModelAndView playersView = new ModelAndView("players");
		List<PlayerInfo> playersInfo = iplService.getAllPlayers(null, null);
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
	
	@GetMapping("ipl/requestors")
	public ModelAndView requestedUsers() throws JsonProcessingException {
		ModelAndView usersView = new ModelAndView("requestors");
		List<UserInfo> usersInfo = iplService.getAll();
		List<UserInfo> requestedUsers = new ArrayList<>();
		if (usersInfo != null && usersInfo.size() > 0) {
			for (UserInfo userInfo : usersInfo) {
				if ("requestor".equals(userInfo.getRole())) {
					requestedUsers.add(userInfo);
				}
			}
		}
		System.out.println("Users: " + mapper.writeValueAsString(requestedUsers));
		usersView.addObject("usersData", requestedUsers);
		return usersView;
	}
	
	@PostMapping("ipl/user/approve/{id}")
	public String approveUserById(@PathVariable Long id) {
		try {
			iplService.approveUserById(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@PostMapping("ipl/user/reject/{id}")
	public String rejectUserById(@PathVariable Long id) {
		try {
			iplService.rejectUserById(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@PostMapping("ipl/importplayers")
	public String importPlayers(@RequestBody MultipartFile file) throws IOException {
		System.out.println("File: " + file.getOriginalFilename());
		System.out.println("File type: " + file.getContentType());
		List<PlayerInfo> players = iplService.excelToIpl(file);
		for (PlayerInfo playerInfo : players) {
			iplService.saveOrUpdate(playerInfo);
		}
		System.out.println("Imported Players size: " + players.size());
		return "";
	}
	
	@GetMapping("ipl/pointsTable")
	public ModelAndView getParticipants() throws JsonProcessingException {
		ModelAndView mv = new ModelAndView("points_table");
		List<PlayerInfo> players = iplService.getAllPlayers(null, null);
		if (players != null && players.size() > 0) {
			List<PointsTable> pointsTable = iplService.getAllParticipants(players);
			mv.addObject("pointsTable", pointsTable);
		}
		return mv;
	}
	
	@GetMapping("ipl/participantPoints/{participantName}")
	public ModelAndView showParticipantPoints(@PathVariable String participantName) {
		ModelAndView mv = new ModelAndView("players");
		List<PlayerInfo> players = iplService.getAllPlayers("owner", participantName);
		if (players != null && players.size() > 0) {
			mv.addObject("playersData", players);
			Long points = 0L;
			for (PlayerInfo playerInfo : players) {
				points += playerInfo.getPoints();
			}
			mv.addObject("totalPoints", points);
			mv.addObject("totalPlayers", players.size());
		}
		return mv;
	}
	
	@DeleteMapping("ipl/deleteAllPlayers")
	public String deleteAllPlayers() {
		try {
			List<PlayerInfo> players = iplService.getAllPlayers(null, null);
			if (players != null && players.size() > 0) {
				for (PlayerInfo playerInfo : players) {
					iplService.deletePlayerById(playerInfo.getId());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@GetMapping("ipl/bid")
	public ModelAndView bid() throws JsonProcessingException {
		ModelAndView mv = new ModelAndView("bid");
		List<UserInfo> usersInfo = iplService.getAll();
		List<BidParticipantInfo> onlineUsers = new ArrayList<>();
		List<BidParticipantInfo> offlineUsers = new ArrayList<>();
		if (usersInfo != null && usersInfo.size() > 0) {
			double remainingPrice = 0d;
			BidParticipantInfo onlineUser = null;
			BidParticipantInfo offlineUser = null;
			for (UserInfo userInfo : usersInfo) {
				if ("participant".equals(userInfo.getRole())) {
					List<PlayerInfo> players = iplService.getAllPlayers("owner", userInfo.getUsername());
					if (userInfo.getOnline() != null && userInfo.getOnline()) {
						onlineUser = new BidParticipantInfo();
						onlineUser.setUserId(userInfo.getId());
						onlineUser.setUsername(userInfo.getUsername());
						
						remainingPrice = 0d;
						if (players != null && players.size() > 0) {
							for (PlayerInfo playerInfo : players) {
								remainingPrice += playerInfo.getBid();
							}
						}
						onlineUser.setTotalPlayers(players.size());
						onlineUser.setRemainingPrice(String.format("%.2f", (120 - remainingPrice) < 0 ? 0 : (120 - remainingPrice)));
						onlineUsers.add(onlineUser);
					} else {
						offlineUser = new BidParticipantInfo();
						offlineUser.setUserId(userInfo.getId());
						offlineUser.setUsername(userInfo.getUsername());
						remainingPrice = 0d;
						if (players != null && players.size() > 0) {
							for (PlayerInfo playerInfo : players) {
								remainingPrice += playerInfo.getBid();
							}
						}
						offlineUser.setTotalPlayers(players.size());
						offlineUser.setRemainingPrice(String.format("%.2f", (120 - remainingPrice) < 0 ? 0 : (120 - remainingPrice)));
						offlineUsers.add(offlineUser);
					}
				}
			}
		}
		mv.addObject("onlineUsers", onlineUsers);
		mv.addObject("offlineUsers", offlineUsers);
		
		List<PlayerInfo> players = iplService.getAllPlayers("owner", "Unsold");
		System.out.println("players: " + mapper.writeValueAsString(players));
		int randomNum = ThreadLocalRandom.current().nextInt(1, players.size());
		System.out.println("randomNum: " + randomNum);
		mv.addObject("bidPlayer", players.get(randomNum));
		return mv;
	}
	
}
