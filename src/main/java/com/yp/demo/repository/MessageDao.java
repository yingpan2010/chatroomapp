package com.yp.demo.repository;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.yp.demo.domain.Message;

@Repository
public class MessageDao extends AbstractDao<Message, Integer>{
    public MessageDao(){
    	setClazz(Message.class);
    }
    public List<Message> findByUsername(String username) {
		String hql = "FROM Scores s WHERE s.ticker = :username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Message> results = (List<Message>) query.list();
		return results;
	}
}
