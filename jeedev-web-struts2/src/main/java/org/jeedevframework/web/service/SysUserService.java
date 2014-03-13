package org.jeedevframework.web.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jeedevframework.web.domain.SysUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
public class SysUserService {
    
    @PersistenceContext
    private EntityManager em;
    
    public SysUser findById(Integer id) {
    	String  sql = "select c from SysUser c where c.id=:id";
    	Query  query = em.createQuery(sql);       
    	query.setParameter("id", id);
        return (SysUser)query.getSingleResult();
    }

	public List<SysUser> findByUsername(String username) {
    	String  sql = "select c from SysUser c where c.username=:username";
    	Query  query = em.createQuery(sql); 
    	query.setParameter("username", username);
        return (List<SysUser>)query.getResultList();
    }

    public List<SysUser> find() {
    	String  sql = "select c from SysUser c";
    	Query  query =em.createQuery(sql); 
        return (List<SysUser>)query.getResultList();
    }
    
    public List<SysUser> findByNativeSQL() {
    	String  sql = "SELECT t.* FROM sys_user";
    	Query  query = em.createNativeQuery(sql);
        return (List<SysUser>)query.getResultList();
    }
 
    public List<SysUser> findByNativeSQLMappingClass() {
    	String  sql = "SELECT t.* FROM sys_user";
    	Query  query = em.createNativeQuery(sql, SysUser.class);
        return (List<SysUser>)query.getResultList();
    }
 
   /* public List<SysUser> findByNativeSQLMappingClass() {
    	String  sql = "SELECT t.* FROM sys_user";
    	Query  query = em.createNativeQuery(sqlString, resultSetMapping)
        return (List<SysUser>)query.getResultList();
    }*/
    
    public List<SysUser> find(String where) {
    	String  sql = "select c from SysUser c "+where;
    	Query  query = em.createQuery(sql);       
        query = em.createQuery(sql);        
        return (List<SysUser>)query.getResultList();
    }
 
    public SysUser find(String username,String password) {
        String sql = "select c from SysUser c where c.username =:username and c.password =:password";
        Query query = em.createQuery(sql); 
        query.setParameter("password", password);
        query.setParameter("username", username);  
        List list=query.getResultList();
        if(list.size()==0){
        	 return null;
        }
        return (SysUser)list.get(0);
    }

    
    @Transactional(propagation =Propagation.REQUIRED, rollbackFor = Exception.class)
    public SysUser update(SysUser user){
    	return em.merge(user);
    }
    
    @Transactional(propagation =Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(SysUser user){
    	em.remove(user);
    }
    
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
