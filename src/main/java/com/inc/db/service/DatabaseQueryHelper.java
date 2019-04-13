package com.inc.db.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ananthan
 */
public class DatabaseQueryHelper {
	private static Logger log = Logger.getLogger(DatabaseQueryHelper.class.getName());
	private static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private DatabaseDAO databaseDAO = (DatabaseDAO) context.getBean("databaseDAO");

	public final void executeInsertQuery(String query) {
		try {
			databaseDAO.executeInsertQuery(query);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final void executeUpdateQuery(String query) {
		try {
			databaseDAO.executeUpdateQuery(query);
		} catch (Exception e) {
			log.error(e);
		}
	}
	

	public final void executeDeleteQuery(String query) {
		try {
			databaseDAO.executeDeleteQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final List<Map<String, Object>> executeQueryGetResult(String query) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		try {
			long st = System.currentTimeMillis();
			result = databaseDAO.executeQueryGetResult(query);
			long et = System.currentTimeMillis();
			if ((et - st) > 3000) {
				sendMail((et - st), query);
			}

		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	public final int executeQueryGetSingleInt(String query) {
		int res = 0;
		try {
			res = databaseDAO.executeQueryGetSingleInt(query);
		} catch (Exception e) {
			log.error(e);
		}
		return res;
	}
	
	public final <T> T executeQueryGetSingleObject(String query, Class<T> classOfT) {
		try {
			return databaseDAO.executeQueryGetSingleObject(query, classOfT);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public final void batchUpdateQuery(String[] queryArray) {
		try {
			databaseDAO.batchUpdateQuery(queryArray);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final String execeuteQueryGetSingleString(String query) {
		String res = "";
		try {
			res = databaseDAO.executeQueryGetSingleString(query);
		} catch (Exception e) {
			log.error(e);
		}
		return res;
	}

	public final int executePreparedStatemet(String query, Object[] object) {
		int status = 0;
		try {
			status = databaseDAO.executePreparedStmt(query, object);
		} catch (Exception e) {
			log.error(e);
		}
		return status;
	}

	public final int[] executeBatchPreparedStmt(String query, List<Object[]> objects) {
		int[] status = null;
		try {
			status = databaseDAO.executeBatchPreparedStmt(query, objects);
		} catch (Exception e) {
			log.error(e);
		}
		return status;
	}

	public final int executeQueryGetAutoIncrementId(String query, Object[] object) {
		int res = 0;
		try {
			res = databaseDAO.executeQueryGetAutoIncrementId(query, object);
		} catch (Exception e) {
			log.error(e);
		}
		return res;

	}

	public final String executeSqlScript(File inputFile) {
		try {
			databaseDAO.executeSqlScript(inputFile);
		} catch (Exception e) {
			log.error(e);
			return e.getMessage();

		}
		return "Success";
	}

	private void sendMail(long timeTaken, String qry) {
		// send mail
		System.out.println("qry-" + qry + " - Time taken : " + timeTaken);
	}

}
