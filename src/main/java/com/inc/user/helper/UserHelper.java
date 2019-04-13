/**
 * 
 */
package com.inc.user.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.inc.db.service.DatabaseQueryHelper;
import com.inc.user.model.User;

/**
 * @author Ananthan
 *
 */
public class UserHelper {
	
private static DatabaseQueryHelper query = new DatabaseQueryHelper();
	
	/**
	 * 
	 * @return
	 */
	public List<User> getUsers() {
		StringBuilder qry = new StringBuilder();
		List<User> userList = new ArrayList<User>();
		qry.append("SELECT userId, firstName, lastName, email FROM user ");
		List<Map<String, Object>> result = query.executeQueryGetResult(qry.toString());
		if (!result.isEmpty()) {
			for (Map<String, Object> rs : result) {
				User user = new User();
				user.setUserId(Integer.parseInt(rs.get("userId").toString()));
				user.setFirstName(rs.get("firstName").toString());
				user.setLastName(rs.get("lastName").toString());
				user.setEmail(rs.get("email").toString());
				userList.add(user);
			}
		}
		return userList;
	}

}
