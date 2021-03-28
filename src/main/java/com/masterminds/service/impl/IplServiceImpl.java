package com.masterminds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masterminds.dao.IplDAO;
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
	
}
