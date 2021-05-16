package com.masterminds.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.dao.IplDAO;
import com.masterminds.entity.PickedPlayer;
import com.masterminds.entity.PlayerInfo;
import com.masterminds.entity.UserInfo;
import com.masterminds.entity.UserSession;

@Repository
@Transactional
public class IplDAOImpl implements IplDAO {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(UserInfo userInfo) {
		Session session = sessionFactory.getCurrentSession();
		if (userInfo.getId() != null) {
			userInfo.setModifiedDate(new Date());
			session.update(userInfo);
		} else {
			userInfo.setCreatedDate(new Date());
			session.save(userInfo);
		}
		session.flush();
	}
	
	@Override
	public UserInfo getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		UserInfo userInfo = session.get(UserInfo.class, id);
		return userInfo;
	}
	
	@Override
	public List<UserInfo> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserInfo.class);
		List<UserInfo> userList = criteria.list();
		return userList;
	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getById(id));
		session.flush();
	}

	@Override
	public UserInfo getByUsernameAndPassword(String username, String password) {
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserInfo.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		return (UserInfo) criteria.uniqueResult();
	}

	@Override
	public void saveOrUpdate(PlayerInfo playerInfo) {
		Session session = sessionFactory.getCurrentSession();
		if (playerInfo.getId() != null) {
			playerInfo.setModifiedDate(new Date());
			session.update(playerInfo);
		} else {
			playerInfo.setCreatedDate(new Date());
			session.save(playerInfo);
		}
		session.flush();
	}

	@Override
	public List<PlayerInfo> getAllPlayers(String colName, String value) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PlayerInfo.class);
		if (colName != null && !colName.isEmpty() && value != null && !value.isEmpty()) {
			criteria.add(Restrictions.eq(colName, value));
		}
		List<PlayerInfo> playerList = criteria.list();
		return playerList;
	}

	@Override
	public PlayerInfo getPlayerById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		PlayerInfo playerInfo = session.get(PlayerInfo.class, id);
		return playerInfo;
	}

	@Override
	public void deletePlayerById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getPlayerById(id));
		session.flush();
	}

	@Override
	public void approveUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		UserInfo userInfo = getById(id);
		if (userInfo != null) {
			userInfo.setRole("participant");
			userInfo.setModifiedDate(new Date());
			session.update(userInfo);
			session.flush();
		}
	}

	@Override
	public void rejectUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		UserInfo userInfo = getById(id);
		if (userInfo != null) {
			userInfo.setRole("requestor");
			userInfo.setModifiedDate(new Date());
			session.update(userInfo);
			session.flush();
		}
	}

	@Override
	public List<UserInfo> getAllOnlineUsers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserInfo.class);
		criteria.add(Restrictions.eq("online", true));
		List<UserInfo> userList = criteria.list();
		return null;
	}

	@Override
	public void saveOrUpdate(PickedPlayer pickedPlayer) {
		Session session = sessionFactory.getCurrentSession();
		if (pickedPlayer.getId() != null) {
			pickedPlayer.setModifiedDate(new Date());
			session.update(pickedPlayer);
		} else {
			pickedPlayer.setCreatedDate(new Date());
			session.save(pickedPlayer);
		}
		session.flush();
	}

	@Override
	public void saveOrUpdate(UserSession newSession) {
		Session session = sessionFactory.getCurrentSession();
		if (newSession.getId() != null) {
			newSession.setModifiedDate(new Date());
			session.update(newSession);
		} else {
			newSession.setCreatedDate(new Date());
			session.save(newSession);
		}
		session.flush();
	}

	@Override
	public UserSession getUserSessionBySessionId(String id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserSession.class);
		criteria.add(Restrictions.eq("sessionId", id));
		return (UserSession) criteria.uniqueResult();
	}

	@Override
	public List<PickedPlayer> getUpdatedPickedPlayerInfo(Long bidPlayerId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PickedPlayer.class);
		criteria.add(Restrictions.eq("playerId", bidPlayerId));
		return criteria.list();
	}
	
	@Override
	public PickedPlayer getPickedPlayerByUserIdAndPlayerId(Long userId, Long playerId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PickedPlayer.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("playerId", playerId));
		return (PickedPlayer) criteria.uniqueResult();
	}

}
