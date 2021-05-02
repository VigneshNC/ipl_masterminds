package com.masterminds.dao;

import java.util.List;

import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.UserInfo;

public interface IplDAO {

	public void saveOrUpdate(UserInfo userInfo);
	
	public UserInfo getById(Long id);
	
	public List<UserInfo> getAll();

	public void deleteById(Long id);
	
	public UserInfo getByUsernameAndPassword(String username, String password);

	public void saveOrUpdate(PlayerInfo playerInfo);

	public List<PlayerInfo> getAllPlayers(String colName, String value);

	public PlayerInfo getPlayerById(Long id);

	public void deletePlayerById(Long id);

	public void approveUserById(Long id);

	public void rejectUserById(Long id);

	public List<UserInfo> getAllOnlineUsers();

}
