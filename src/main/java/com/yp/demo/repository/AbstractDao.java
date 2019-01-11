package com.yp.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AbstractDao<T extends Serializable, ID extends Serializable> {
	private Class<T> clazz;

	@Autowired
	@Qualifier(value = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@SuppressWarnings("unchecked")
	
	public List<T> findAll() {
		return this.sessionFactory.getCurrentSession()
				.createQuery("from " + this.clazz.getName()).list();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T)this.sessionFactory.getCurrentSession().get(clazz, id);
	}

	public void save(T entity) {
		this.sessionFactory.getCurrentSession().save(entity);
	}

	public void update(T entity) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		this.sessionFactory.getCurrentSession().delete(entity);
	}
	@SuppressWarnings("unchecked")
	public void deleteById (ID id) {
		T entity = (T)this.sessionFactory.getCurrentSession().get(clazz, id);
		this.sessionFactory.getCurrentSession().delete(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
