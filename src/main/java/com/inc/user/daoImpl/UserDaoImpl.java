/**
 * 
 */
package com.inc.user.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inc.user.dao.UserDao;
import com.inc.user.model.User;


/**
 * @author Ananthan
 *
 */
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public List<User> getUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery(" from User ").list();
		for(User p : userList){
			logger.info("User List::"+p);
		}
		return userList;
	}

}
