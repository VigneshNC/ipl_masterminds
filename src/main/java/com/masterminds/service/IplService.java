package com.masterminds.service;

import java.util.List;

import com.masterminds.entity.UserInfo;

public interface IplService {

	public void saveOrUpdate(UserInfo userInfo);
	
	public UserInfo getById(Long id);
	
	public List<UserInfo> getAll();

	public void deleteById(Long id);
	
	public UserInfo getByUsernameAndPassword(String username, String password);
	
}
