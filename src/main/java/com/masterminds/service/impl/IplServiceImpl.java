package com.masterminds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masterminds.dao.IplDAO;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.UserInfo;
import com.masterminds.service.IplService;

@Service
public class IplServiceImpl implements IplService {

	@Autowired
	IplDAO iplDAO;

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
	public List<PlayerInfo> getAllPlayers() {
		return iplDAO.getAllPlayers();
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
	
}
