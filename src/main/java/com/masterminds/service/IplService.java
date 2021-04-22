package com.masterminds.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.PointsTable;
import com.masterminds.entity.UserInfo;

public interface IplService {

	public void saveOrUpdate(UserInfo userInfo);
	
	public UserInfo getById(Long id);
	
	public List<UserInfo> getAll();

	public void deleteById(Long id);
	
	public UserInfo getByUsernameAndPassword(String username, String password);

	public PlayerInfo getPlayerById(Long id);
	
	public void saveOrUpdate(PlayerInfo playerInfo);

	public List<PlayerInfo> getAllPlayers(String participantName);

	public void deletePlayerById(Long id);

	public void approveUserById(Long id);

	public void rejectUserById(Long id);
	
	public List<PlayerInfo> excelToIpl(MultipartFile file);

	public List<PointsTable> getAllParticipants(List<PlayerInfo> players);
	
}
