package id.co.infotech.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected CriteriaBuilder createEntityCriteriaBuilder(){
		return getSession().getCriteriaBuilder();
	}

	public List<T> findAllOrderByAsc(String property) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);
		cq.select(root);
		cq.orderBy(builder.asc(root.get(property)));
		
		return getSession().createQuery(cq).getResultList();
	}

	public List<T> findAllOrderByDesc(String property) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);
		cq.select(root);
		cq.orderBy(builder.desc(root.get(property)));
		
		return getSession().createQuery(cq).getResultList();
	}

	public T find(String property, String value) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);
		cq.where(builder.and(builder.equal(root.get(property), value)));

		return (T) createQuery(cq);
	}

	public T createQuery(CriteriaQuery<T> cq){
		return getSession().createQuery(cq).getSingleResult();
	}

}
