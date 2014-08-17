package org.jeedevframework.core.common.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jeedevframework.core.common.dao.BaseDao;
import org.jeedevframework.core.common.domain.IDEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Repository
public class BaseDaoImpl<T extends IDEntity> implements BaseDao<T> {
	
	@PersistenceContext(unitName="jeedev")
	protected EntityManager entityManager;
	
	private Class<T> mapperClass;

	public void setMapperClass(Class<T> mapperClass) {
		this.mapperClass = mapperClass;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void save(T entity) {
		Date now = new Date();
		entity.setCreateDate(now);
		entity.setUpdateDate(now);
		this.entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entity.setUpdateDate(new Date());
		this.entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entity.setUpdateDate(new Date());
		this.entityManager.remove(entity);
	}

	@Override
	public T findById(Serializable primaryKey) {
		Assert.notNull(mapperClass);
		return this.entityManager.find(mapperClass, primaryKey);
	}

	@Override
	public List<T> findList(T entity) {
		//Table table = entity.getClass().getAnnotation(Table.class);
		//String tableName = table.name();
		String qlStrirng = "select c from "+mapperClass.getSimpleName() +" c where c.isDelete=:isDelete";
		Query query = this.entityManager.createQuery(qlStrirng);
		query.setParameter("isDelete", entity.isDelete());
		query.setFirstResult(entity.getPage());
		query.setMaxResults(entity.getRows());
		query.setHint("org.hibernate.cacheable", true);
		return query.getResultList(); 
	}

	public Class<? extends IDEntity> getMapperClass() {
		return mapperClass;
	}

}
