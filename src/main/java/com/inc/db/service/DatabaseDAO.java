package com.inc.db.service;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Interface that holds the basic method signatures for all database operations.
 * 
 * @author Ananthan
 */
public interface DatabaseDAO {

	void executeInsertQuery(String query);

	void executeUpdateQuery(String query);

	void executeDeleteQuery(String query);

	List<Map<String, Object>> executeQueryGetResult(String query);

	int executeQueryGetSingleInt(String query);

	<T> T executeQueryGetSingleObject(String query, Class<T> classOfT);

	void batchUpdateQuery(String[] queryArray);

	String executeQueryGetSingleString(String query);

	int executePreparedStmt(String query, Object[] objects);

	void executeSqlScript(File inputFile);

	int executeQueryGetAutoIncrementId(String query, Object[] objects);

	int[] executeBatchPreparedStmt(String query, List<Object[]> objects);

}
