/**
 * 
 */
package com.inc.user.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inc.user.dao.UserDao;
import com.inc.user.model.User;
import com.inc.user.service.UserService;

/**
 * @author Ananthan
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public List<User> getUsers(){
		return this.userDao.getUsers();
	}
}
