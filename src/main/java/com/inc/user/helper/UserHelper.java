/**
 * 
 */
package com.inc.user.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	
	public static void iterateJsonObject(JSONObject jsonObj) {
		for (Object key : jsonObj.keySet()) {
			// based on you key types
			String keyStr = (String) key;
			Object keyvalue = jsonObj.get(keyStr);

			// Print key and value
			System.out.println("key: " + keyStr + " value: " + keyvalue);

			// for nested objects iteration if required
			if (keyvalue instanceof JSONObject)
				iterateJsonObject((JSONObject) keyvalue);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(new FileReader("F:\\JsonFile\\CW18_report.json"));
		// convert Object to JSONObject
		JSONObject jsonObject = (JSONObject) object;
		System.out.println(jsonObject);
		iterateJsonObject(jsonObject);
	}
}
