package com.masterminds.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterminds.dao.IplDAO;
import com.masterminds.entity.UserInfo;

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
			session.update(userInfo);
		} else {
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

}
