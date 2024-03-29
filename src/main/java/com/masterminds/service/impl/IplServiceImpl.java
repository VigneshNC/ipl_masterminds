package com.masterminds.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masterminds.dao.IplDAO;
import com.masterminds.entity.PickedPlayer;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.PointsTable;
import com.masterminds.entity.UserInfo;
import com.masterminds.entity.UserSession;
import com.masterminds.service.IplService;

@Service
public class IplServiceImpl implements IplService {

	@Autowired
	IplDAO iplDAO;
	
//	New start
	
	@Override
	public UserInfo getByEmailAndPassword(String email, String password) {
		return iplDAO.getByEmailAndPassword(email, password);
	}

//	New end

	@Override
	public void saveOrUpdate(UserInfo userInfo) {
		iplDAO.saveOrUpdate(userInfo);
	}

	@Override
	public UserInfo getById(Long id) {
		return iplDAO.getById(id);
	}

	@Override
	public List<UserInfo> getAll() {
		return iplDAO.getAll();
	}

	@Override
	public void deleteById(Long id) {
		iplDAO.deleteById(id);
	}

	@Override
	public UserInfo getByUsernameAndPassword(String username, String password) {
		return iplDAO.getByUsernameAndPassword(username, password);
	}

	@Override
	public PlayerInfo getPlayerById(Long id) {
		return iplDAO.getPlayerById(id);
	}

	@Override
	public void saveOrUpdate(PlayerInfo playerInfo) {
		iplDAO.saveOrUpdate(playerInfo);
	}

	@Override
	public List<PlayerInfo> getAllPlayers(String colName, String value, String orderColName) {
		return iplDAO.getAllPlayers(colName, value, orderColName);
	}

	@Override
	public void deletePlayerById(Long id) {
		iplDAO.deletePlayerById(id);
	}

	@Override
	public void approveUserById(Long id) {
		iplDAO.approveUserById(id);
	}

	@Override
	public void rejectUserById(Long id) {
		iplDAO.rejectUserById(id);
	}

	public String importPlayers(MultipartFile file) {

		return "";
	}

	public List<PlayerInfo> excelToIpl(MultipartFile mFile) {
		try {
			Workbook workbook = new XSSFWorkbook(mFile.getInputStream());

			Sheet sheet = workbook.getSheet("List of Players");
			Iterator<Row> rows = sheet.iterator();

			List<PlayerInfo> players = new ArrayList<>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				PlayerInfo player = new PlayerInfo();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						player.setPlayerId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						player.setPlayerName(currentCell.getStringCellValue());
						break;

					case 2:
						player.setRole(currentCell.getStringCellValue());
						break;

					case 3:
						player.setIplTeam(currentCell.getStringCellValue());
						break;

					case 4:
						player.setNationality(currentCell.getStringCellValue());
						break;

					case 5:
						player.setOwner(currentCell.getStringCellValue());
						break;
						
					case 6:
						player.setBid(currentCell.getNumericCellValue());
						break;

					case 7:
						player.setPoints((long) currentCell.getNumericCellValue());
						break;

					case 8:
						player.setMatch1((long) currentCell.getNumericCellValue());
						break;

					case 9:
						player.setMatch2((long) currentCell.getNumericCellValue());
						break;

					case 10:
						player.setMatch3((long) currentCell.getNumericCellValue());
						break;

					case 11:
						player.setMatch4((long) currentCell.getNumericCellValue());
						break;

					case 12:
						player.setMatch5((long) currentCell.getNumericCellValue());
						break;

					case 13:
						player.setMatch6((long) currentCell.getNumericCellValue());
						break;

					case 14:
						player.setMatch7((long) currentCell.getNumericCellValue());
						break;

					case 15:
						player.setMatch8((long) currentCell.getNumericCellValue());
						break;

					case 16:
						player.setMatch9((long) currentCell.getNumericCellValue());
						break;

					case 17:
						player.setMatch10((long) currentCell.getNumericCellValue());
						break;

					case 18:
						player.setMatch11((long) currentCell.getNumericCellValue());
						break;

					case 19:
						player.setMatch12((long) currentCell.getNumericCellValue());
						break;

					case 20:
						player.setMatch13((long) currentCell.getNumericCellValue());
						break;

					case 21:
						player.setMatch14((long) currentCell.getNumericCellValue());
						break;

					case 22:
						player.setMatch15((long) currentCell.getNumericCellValue());
						break;

					case 23:
						player.setMatch16((long) currentCell.getNumericCellValue());
						break;

					case 24:
						player.setMatch17((long) currentCell.getNumericCellValue());
						break;

					case 25:
						player.setMatch18((long) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				players.add(player);
			}
			workbook.close();
			return players;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	
	@Override
	public List<PointsTable> getAllParticipants(List<PlayerInfo> players) {
		List<PointsTable> pointsTable = new ArrayList<>();
		Map<String, Long> participantMap = new HashMap<>();
		participantMap.put("Iyyappan", 0L);
		participantMap.put("Manoj", 0L);
		participantMap.put("Prem Kumar", 0L);
		participantMap.put("Siranjeevi", 0L);
		participantMap.put("Subash", 0L);
		participantMap.put("Venu", 0L);
		participantMap.put("Vignesh K", 0L);
		participantMap.put("Vignesh NC", 0L);
		if (players != null && players.size() > 0) {
			Long points = 0L;
			for (PlayerInfo playerInfo : players) {
				points = 0L;
				if (participantMap.containsKey(playerInfo.getOwner())) {
					points = participantMap.get(playerInfo.getOwner());
					points += playerInfo.getPoints();
					participantMap.put(playerInfo.getOwner(), points);
				}
			}
		}
		PointsTable point = null;
		for (String participant: participantMap.keySet()) {
			point = new PointsTable();
			point.setParticipant(participant);
			point.setPoints(participantMap.get(participant));
			pointsTable.add(point);
		}
		Collections.sort(pointsTable);
		return pointsTable;
	}

	@Override
	public List<UserInfo> getAllOnlineUsers() {
		return iplDAO.getAllOnlineUsers();
	}

	@Override
	public void saveOrUpdate(PickedPlayer pickedPlayer) {
		iplDAO.saveOrUpdate(pickedPlayer);
	}

	@Override
	public void saveOrUpdate(UserSession newSession) {
		iplDAO.saveOrUpdate(newSession);
	}

	@Override
	public UserSession getUserSessionBySessionId(String id) {
		return iplDAO.getUserSessionBySessionId(id);
	}

	@Override
	public PickedPlayer getUpdatedPickedPlayerInfo(Long bidPlayerId) {
		List<PickedPlayer> pickedPlayerInfos = iplDAO.getUpdatedPickedPlayerInfo(bidPlayerId);
		PickedPlayer updatedPickedPlayer = null;
		if (pickedPlayerInfos != null && pickedPlayerInfos.size() > 0) {
			Long currentBidPrice = 0L;
			for (PickedPlayer pickedPlayer : pickedPlayerInfos) {
				if (pickedPlayer.getPlayerPrice().endsWith("C")) {
					Long pickedPlayerPrice = Long.valueOf(pickedPlayer.getPlayerPrice().split("C")[1]);
					if (pickedPlayerPrice.longValue() > currentBidPrice.longValue()) {
						currentBidPrice = pickedPlayerPrice;
						updatedPickedPlayer = pickedPlayer;
					}
				}
			}
			if (updatedPickedPlayer != null) {
				currentBidPrice = 0L;
				for (PickedPlayer pickedPlayerInLakh : pickedPlayerInfos) {
					if (pickedPlayerInLakh.getPlayerPrice().endsWith("L")) {
						Long pickedPlayerPrice = Long.valueOf(pickedPlayerInLakh.getPlayerPrice().split("L")[1]);
						if (pickedPlayerPrice.longValue() > currentBidPrice.longValue()) {
							currentBidPrice = pickedPlayerPrice;
							updatedPickedPlayer = pickedPlayerInLakh;
						}
					}
				}
			}
		}
		return updatedPickedPlayer;
	}

	@Override
	public void savePickedPlayerInfo(Long bidPlayerId) {
//		List<UserInfo> onlineUsers = getAllOnlineUsers();
		List<UserInfo> allUsers = getAll();
		if (allUsers != null && allUsers.size() > 0) {
			PickedPlayer pickedPlayer = null;
			for (UserInfo userInfo : allUsers) {
				if (userInfo.getOnline() != null && userInfo.getOnline() && "participant".equals(userInfo.getRole())) {
					pickedPlayer = new PickedPlayer();
					pickedPlayer.setUserId(userInfo.getId());
					pickedPlayer.setPlayerId(bidPlayerId);
					pickedPlayer.setPlayerPrice("0L");
					pickedPlayer.setStarted(false);
					iplDAO.saveOrUpdate(pickedPlayer);
				}
			}
		}
	}

	@Override
	public PickedPlayer getPickedPlayerByUserIdAndPlayerId(Long userId, Long playerId) {
		return iplDAO.getPickedPlayerByUserIdAndPlayerId(userId, playerId);
	}

	@Override
	public PickedPlayer getCurrentTurnPlayer(Long bidPlayerId) {
		List<PickedPlayer> pickedPlayerInfos = iplDAO.getNotStartedPickedPlayers(bidPlayerId);
		if (pickedPlayerInfos != null && pickedPlayerInfos.size() > 0) {
			Collections.sort(pickedPlayerInfos);
			return pickedPlayerInfos.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<UserInfo> getAllRequestors() {
		return iplDAO.getAllRequestors();
	}
	
	@Override
	public List<UserInfo> getAllParticipants() {
		return iplDAO.getAllParticipants();
	}
	
}
