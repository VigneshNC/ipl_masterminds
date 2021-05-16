package com.masterminds.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("ipl/authenticate")
	public Long authenticate(@RequestParam String username, @RequestParam String password) {
		UserInfo user = iplService.getByUsernameAndPassword(username, password);
		if (user != null) {
			if ("requestor".equals(user.getRole())) {
				return 1L;
			}
			user.setOnline(true);
			iplService.saveOrUpdate(user);
			return user.getId();
		}
		return 0L;
	}

	@PostMapping("ipl/save")
	public Map<String, String> saveUserInfo(@RequestBody String userInfoStr)
			throws JsonMappingException, JsonProcessingException {
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
					} else if (user.getTeamName() != null
							&& user.getTeamName().equalsIgnoreCase(userInfo.getTeamName())) {
						resultMap.put("status", "duplicate");
						resultMap.put("reason", "teamname");
						return resultMap;
					}
				}
			}

			userInfo.setRole("requestor");
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
		List<PlayerInfo> playersInfo = iplService.getAllPlayers(null, null);
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

	@PostMapping("ipl/importplayers")
	public String importPlayers(@RequestBody MultipartFile file) throws IOException {
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
			}
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@GetMapping("ipl/bid")
	public ModelAndView bid(HttpServletRequest request) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView("bid");
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
					List<PlayerInfo> players = iplService.getAllPlayers("owner", userInfo.getUsername());
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
		mv.addObject("onlineUsers", onlineUsers);
		mv.addObject("offlineUsers", offlineUsers);
		mv.addObject("totalBidUsers", onlineUsers.size() + offlineUsers.size());

		List<PlayerInfo> players = iplService.getAllPlayers("owner", "Unsold");
		if (players != null && players.size() > 0) {
			Long priority = 1L;
			for (PlayerInfo playerInfo : players) {
				if (priority.longValue() == playerInfo.getPriority().longValue()) {
					mv.addObject("bidPlayer", playerInfo);
					playerInfo.setUserOrder(bidUserId);
					iplService.saveOrUpdate(playerInfo);
					break;
				}
				priority++;
			}
		}
		
		Object userIdObj = request.getSession(false).getAttribute("userId");
		if (userIdObj != null) {
			Long userId = (Long) userIdObj;
			UserInfo userInfo = iplService.getById(userId);
			mv.addObject("online", userInfo.getOnline());
			mv.addObject("currentUserId", userId);
		}
		
		return mv;
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
				
				List<PlayerInfo> players = iplService.getAllPlayers("owner", "Unsold");
				if (players != null && players.size() > 0) {
					Long priority = 1L;
					for (PlayerInfo playerInfo : players) {
						if (priority.longValue() == playerInfo.getPriority().longValue()) {
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

}
