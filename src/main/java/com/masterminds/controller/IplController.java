package com.masterminds.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.entity.BidParticipantInfo;
import com.masterminds.entity.PickedPlayer;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.PointsTable;
import com.masterminds.entity.UserInfo;
import com.masterminds.service.IplService;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins = "https://ipl-masterminds-angular.herokuapp.com/", allowCredentials = "true", allowedHeaders = {
//		"Origin", "X-Requested-With", "Content-Type", "Accept", "content-type", "application/json" }, methods = {
//				RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.PATCH,
//				RequestMethod.DELETE })
@CrossOrigin
@RequestMapping("ipl")
public class IplController {

	@Autowired
	IplService iplService;

	ObjectMapper mapper = new ObjectMapper();

	@GetMapping({ "/", "/ipl", "ipl/logout" })
	public ModelAndView origin(HttpServletRequest request) {
		return new ModelAndView("ipl_home");
	}

	@GetMapping("ipl/register")
	public ModelAndView register() {
		return new ModelAndView("user");
	}
	
	@GetMapping("ipl/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@GetMapping("/authenticate")
	public String authenticate(@RequestParam String email, @RequestParam String password) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
//		UserInfo user = iplService.getByUsernameAndPassword(username, password);
		UserInfo user = iplService.getByEmailAndPassword(email, password);
//		if (user != null) {
//			if ("requestor".equals(user.getRole())) {
//				return 1L;
//			}
//			user.setOnline(true);
//			iplService.saveOrUpdate(user);
//			return user.getId();
//		}
//		return 0L;
		if (user != null) {
			resultMap.put("status", "success");
			resultMap.put("userId", user.getId());
		} else {
			resultMap.put("status", "failed");
			resultMap.put("reason", "Invalid username or password!");
		}
		return mapper.writeValueAsString(resultMap);
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdateUserInfo(@RequestBody UserInfo userInfo) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
//		System.out.println("userInfoStr: " + userInfoStr);
		System.out.println("userinfo: " + mapper.writeValueAsString(userInfo));
		try {
//			UserInfo userInfo = mapper.readValue(userInfoStr, UserInfo.class);
			if (userInfo != null) {
				if (userInfo.getId() == null) {
					List<UserInfo> users = iplService.getAll();
					if (users != null && users.size() > 0) {
						for (UserInfo user : users) {
							if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(userInfo.getEmail())) {
								resultMap.put("status", "failed");
								resultMap.put("reason", "Email already exists!");
								return mapper.writeValueAsString(resultMap);
	//						} else if (user.getTeamName() != null
	//								&& user.getTeamName().equalsIgnoreCase(userInfo.getTeamName())) {
	//							resultMap.put("status", "duplicate");
	//							resultMap.put("reason", "teamname");
	//							return resultMap;
							}
						}
					}
				}
				if (userInfo.getRole() == null || userInfo.getRole().isEmpty()) {
					userInfo.setRole("requestor");
				}
				try {
					iplService.saveOrUpdate(userInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultMap.put("status", "success");
				resultMap.put("userInfo", userInfo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", ex.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}

	@GetMapping("ipl/user/view/{id}")
	public ModelAndView getUserById(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView userView = new ModelAndView("profile");
		UserInfo userInfo = iplService.getById(id);
		if (userInfo != null) {
			userView.addObject("userData", userInfo);
			String userOrAdmin = "user";
			if ("admin".equals(userInfo.getUsername())) {
				userOrAdmin = "admin";
			}
			userView.addObject("userOrAdmin", userOrAdmin);
			request.getSession(false).setAttribute("userId", userInfo.getId());
		}
		return userView;
	}

//	@GetMapping("ipl/user/edit/{id}")
	@GetMapping("/user/{id}")
	public String editUserById(@PathVariable Long id) throws JsonProcessingException {
//		ModelAndView userView = new ModelAndView("user");
		Map<String, Object> resultMap = new HashMap<>();
		UserInfo userInfo = iplService.getById(id);
		if (userInfo != null) {
			resultMap.put("status", "success");
			resultMap.put("userInfo", userInfo);
		} else {
			resultMap.put("status", "failed");
			resultMap.put("reason", "Invalid user id!");
		}
//		userView.addObject("userData", userInfo);
//		return userView;
		return mapper.writeValueAsString(resultMap);
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
		usersView.addObject("usersData", authenticatedUsers);
		return usersView;
	}

	@DeleteMapping("ipl/delete/{id}")
	public String deleteByUserId(@PathVariable Long id) {
		try {
			iplService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@GetMapping("ipl/rules")
	public ModelAndView getRules(HttpServletRequest request) {
		return new ModelAndView("rules");
	}

	@GetMapping("ipl/playersList")
	public ModelAndView getPlayers() throws JsonProcessingException {
		ModelAndView playersView = new ModelAndView("players");
		List<PlayerInfo> playersInfo = iplService.getAllPlayers(null, null, null);
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
		playerView.addObject("playerData", playerInfo);
		return playerView;
	}

	@PostMapping("ipl/savePlayer")
	public String savePlayer(@RequestBody String playerInfoStr) {
		String result = "";
		try {
			PlayerInfo playerInfo = mapper.readValue(playerInfoStr, PlayerInfo.class);
			if (playerInfo != null) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@GetMapping("ipl/requestors")
	public ModelAndView requestedUsers() {
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
		usersView.addObject("usersData", requestedUsers);
		return usersView;
	}

	@PostMapping("ipl/user/approve/{id}")
	public String approveUserById(@PathVariable Long id) {
		try {
			iplService.approveUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@PostMapping("ipl/user/reject/{id}")
	public String rejectUserById(@PathVariable Long id) {
		try {
			iplService.rejectUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@PostMapping("/importplayers")
	public String importPlayers(@RequestBody MultipartFile file) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			if (file != null) {
				List<PlayerInfo> players = iplService.excelToIpl(file);
				if (players != null && players.size() > 0) {
					Collections.shuffle(players);
					Long priority = 1L;
					for (PlayerInfo playerInfo : players) {
						if ("Unsold".equals(playerInfo.getOwner())) {
							playerInfo.setPriority(priority);
							priority++;
						}
						iplService.saveOrUpdate(playerInfo);
					}
					resultMap.put("status", "success");
					resultMap.put("reason", "Players are imported successfully");
					List<PlayerInfo> savedPlayers = iplService.getAllPlayers(null, null, null);
					resultMap.put("players", savedPlayers);
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", ex.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}

	@GetMapping("ipl/pointsTable")
	public ModelAndView getParticipants() throws JsonProcessingException {
		ModelAndView mv = new ModelAndView("points_table");
		List<PlayerInfo> players = iplService.getAllPlayers(null, null, null);
		if (players != null && players.size() > 0) {
			List<PointsTable> pointsTable = iplService.getAllParticipants(players);
			mv.addObject("pointsTable", pointsTable);
		}
		return mv;
	}

	@GetMapping("ipl/participantPoints/{participantName}")
	public ModelAndView showParticipantPoints(@PathVariable String participantName) {
		ModelAndView mv = new ModelAndView("players");
		List<PlayerInfo> players = iplService.getAllPlayers("owner", participantName, null);
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

	@DeleteMapping("/deleteAllPlayers")
	public String deleteAllPlayers() throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			List<PlayerInfo> players = iplService.getAllPlayers(null, null, null);
			if (players != null && players.size() > 0) {
				for (PlayerInfo playerInfo : players) {
					iplService.deletePlayerById(playerInfo.getId());
				}
				resultMap.put("status", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", e.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}

	@GetMapping("bid")
	public Map<String, Object> bid(HttpServletRequest request) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
//		ModelAndView mv = new ModelAndView("bid");
		List<UserInfo> usersInfo = iplService.getAll();
		List<BidParticipantInfo> onlineUsers = new ArrayList<>();
		List<BidParticipantInfo> offlineUsers = new ArrayList<>();
		Long bidUserId = 0L;
		if (usersInfo != null && usersInfo.size() > 0) {
			double remainingPrice = 0d;
			BidParticipantInfo onlineUser = null;
			BidParticipantInfo offlineUser = null;
			for (UserInfo userInfo : usersInfo) {
				if ("participant".equals(userInfo.getRole())) {
					List<PlayerInfo> players = iplService.getAllPlayers("owner", userInfo.getUsername(), null);
					if (userInfo.getOnline() != null && userInfo.getOnline()) {
						if (bidUserId.longValue() == 0L || (userInfo.getId().longValue() < bidUserId.longValue())) {
							bidUserId = userInfo.getId();
						}
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
						onlineUser.setRemainingPrice(
								String.format("%.2f", (120 - remainingPrice) < 0 ? 0 : (120 - remainingPrice)));
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
						offlineUser.setRemainingPrice(
								String.format("%.2f", (120 - remainingPrice) < 0 ? 0 : (120 - remainingPrice)));
						offlineUsers.add(offlineUser);
					}
				}
			}
		}
//		mv.addObject("onlineUsers", onlineUsers);
//		mv.addObject("offlineUsers", offlineUsers);
//		mv.addObject("totalBidUsers", onlineUsers.size() + offlineUsers.size());
		resultMap.put("onlineUsers", onlineUsers);
		resultMap.put("offlineUsers", offlineUsers);

		List<PlayerInfo> players = iplService.getAllPlayers("owner", "Unsold", "priority");
		System.out.println("players size: " + players.size());
		if (players != null && players.size() > 0) {
			Long priority = 1L;
			for (PlayerInfo playerInfo : players) {
				System.out.println("player info priority: " + playerInfo.getPriority());
				System.out.println("current priority: " + priority);
				if (playerInfo.getPriority() != null && priority.longValue() == playerInfo.getPriority().longValue()) {
					playerInfo.setUserOrder(bidUserId);
//					mv.addObject("bidPlayer", playerInfo);
					resultMap.put("bidPlayer", playerInfo);
					iplService.saveOrUpdate(playerInfo);
					break;
				}
				priority++;
			}
		}
		
//		Object userIdObj = request.getSession(false).getAttribute("userId");
//		if (userIdObj != null) {
//			Long userId = (Long) userIdObj;
//			UserInfo userInfo = iplService.getById(userId);
//			mv.addObject("online", userInfo.getOnline());
//			mv.addObject("currentUserId", userId);
//		}
		
//		return mv;
		return resultMap;
	}
	
	@PutMapping("ipl/pickPlayer")
	public String pickPlayer(@RequestBody String pickPlayer) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		System.out.println("pickPlayer: " + pickPlayer);
		if (!pickPlayer.isEmpty()) {
			String pickPlayerStr = URLDecoder.decode(pickPlayer.substring(0, pickPlayer.length() - 1), "UTF-8");
			System.out.println("Decoded pickPlayer: " + pickPlayerStr);
			Map userMap = mapper.readValue(pickPlayerStr, Map.class);
			if (userMap != null && userMap.size() > 0) {
				Long playerId = (Long) userMap.get("playerId");
				Long userId = (Long) userMap.get("userId");
				Long nextUserId = (Long) userMap.get("nextUserId");
				PickedPlayer pickedPlayer = iplService.getPickedPlayerByUserIdAndPlayerId(userId, playerId);
				pickedPlayer.setPlayerPrice((String) userMap.get("price"));
				pickedPlayer.setStarted(false);
//				iplService.saveOrUpdate(pickedPlayer);
				
				List<PlayerInfo> players = iplService.getAllPlayers("owner", "Unsold", null);
				if (players != null && players.size() > 0) {
					Long priority = 1L;
					for (PlayerInfo playerInfo : players) {
						if (playerInfo.getPriority() != null && priority.longValue() == playerInfo.getPriority().longValue()) {
							playerInfo.setUserOrder(nextUserId);
							iplService.saveOrUpdate(playerInfo);
							break;
						}
						priority++;
					}
				}
				
				return "success";
			}
		}
		return "failed";
	}
	
	@GetMapping("ipl/getUpdatedPickedPlayerInfo/{bidPlayerId}")
	public PickedPlayer getUpdatedPickedPlayerInfo(@PathVariable Long bidPlayerId) {
		return iplService.getUpdatedPickedPlayerInfo(bidPlayerId);
	}
	
	@PostMapping("ipl/savePickedPlayerInfo/{bidPlayerId}")
	public String savePickedPlayerInfo(@PathVariable Long bidPlayerId) {
		iplService.savePickedPlayerInfo(bidPlayerId);
		return "success";
	}
	
	@GetMapping("ipl/getCurrentTurnPlayer/{bidPlayerId}")
	public PickedPlayer getCurrentTurnPlayer(@PathVariable Long bidPlayerId) {
		PickedPlayer currentTurnPlayer = iplService.getCurrentTurnPlayer(bidPlayerId);
		if (currentTurnPlayer != null) {
			return currentTurnPlayer;
		} else {
			return null;
		}
	}
	
	@GetMapping("/getAllRequestors")
	public String getAllRequestors() throws JsonProcessingException {
		return mapper.writeValueAsString(iplService.getAllRequestors());
	}
	
	@GetMapping("/getAllParticipants")
	public String getAllParticipants() throws JsonProcessingException {
		return mapper.writeValueAsString(iplService.getAllParticipants());
	}
	
	@PutMapping("/approveRequestor")
	public String approveRequestor(@RequestBody UserInfo userInfo) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			userInfo.setRole("participant");
			iplService.saveOrUpdate(userInfo);
			resultMap.put("status", "success");
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", ex.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}
	
	@DeleteMapping("/deleteParticipant")
	public String deleteParticipant(@RequestParam Long participantId) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			iplService.deleteById(participantId);
			resultMap.put("status", "success");
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", ex.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}
	
	@PutMapping("/rejectParticipant")
	public String rejectParticipant(@RequestBody UserInfo userInfo) throws JsonProcessingException {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			userInfo.setRole("requestor");
			iplService.saveOrUpdate(userInfo);
			resultMap.put("status", "success");
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("status", "failed");
			resultMap.put("reason", ex.getMessage());
			return mapper.writeValueAsString(resultMap);
		}
		return mapper.writeValueAsString(resultMap);
	}
	
	@GetMapping("/getAllPlayers")
	public String getAllPlayers() throws JsonProcessingException {
		return mapper.writeValueAsString(iplService.getAllPlayers(null, null, null));
	}
	
	@GetMapping("/getAllPointsTable")
	public List<PointsTable> getAllPointsTable() throws JsonProcessingException {
		List<PointsTable> pointsTable = new ArrayList<>();
		List<PlayerInfo> players = iplService.getAllPlayers(null, null, null);
		if (players != null && players.size() > 0) {
			pointsTable = iplService.getAllParticipants(players);
		}
		return pointsTable;
	}
	
}
