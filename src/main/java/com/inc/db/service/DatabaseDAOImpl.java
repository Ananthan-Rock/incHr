package com.inc.db.service;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/**
 * @author Ananthan
 */
public class DatabaseDAOImpl implements DatabaseDAO {
	private static Logger log = Logger.getLogger(DatabaseDAOImpl.class.getName());

	private JdbcTemplate jdbcTemplate;

	public final void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public final void executeDeleteQuery(String query) {
		try {
			jdbcTemplate.update(query);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final void executeInsertQuery(String query) {
		try {
			jdbcTemplate.update(query);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final List<Map<String, Object>> executeQueryGetResult(String query) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			rows = jdbcTemplate.queryForList(query);
		} catch (Exception e) {
			log.error(e);
		}
		return rows;
	}

	public final void executeUpdateQuery(String query) {
		try {
			jdbcTemplate.update(query);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final int executeQueryGetSingleInt(String query) {
		int result = 0;
		try {
			result = jdbcTemplate.queryForObject(query, Integer.class);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * It will give object in of requested type
	 * 
	 * @author Kurra Raghu
	 * @param query
	 * @param classOfT
	 *            (Integer.class,Double.class)
	 * @return
	 */
	public final <T> T executeQueryGetSingleObject(String query, Class<T> classOfT) {
		try {
			return jdbcTemplate.queryForObject(query, classOfT);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public final void batchUpdateQuery(String[] queryArray) {
		try {
			jdbcTemplate.batchUpdate(queryArray);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public final String executeQueryGetSingleString(String query) {
		String result = "";
		try {
			result = (String) jdbcTemplate.queryForObject(query, String.class);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	public final int executePreparedStmt(String query, Object[] objects) {
		return jdbcTemplate.update(query, objects);
	}

	public final int[] executeBatchPreparedStmt(String query, List<Object[]> objects) {
		return jdbcTemplate.batchUpdate(query, objects);
	}

	public final void executeSqlScript(File inputFile) {
		String delimiter = ";";
		// Create scanner
		Scanner scanner = null;
		try {
			scanner = new Scanner(inputFile, "UTF-8").useDelimiter(delimiter);
			// Loop through the SQL file statements
			while (scanner.hasNext()) {
				// Get statement
				String rawStatement = scanner.next().trim();
				if (!rawStatement.isEmpty()) {
					jdbcTemplate.execute(rawStatement);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Unable to run sql script : <br/>   " + e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	public final int executeQueryGetAutoIncrementId(final String query, final Object[] objects) {
		int result = 0;
		KeyHolder holder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					int i = 0;
					for (Object arg : objects) {
						if (arg instanceof String) {
							String str = String.valueOf(arg);
							ps.setString(++i, str);
						} else if (arg instanceof Integer) {
							ps.setInt(++i, (Integer) arg);
						} else if (arg == null) {
							ps.setNull(++i, Types.NULL);
						} else if (arg instanceof Long) {
							ps.setLong(++i, (Long) arg);
						} else if (arg instanceof Boolean) {
							ps.setBoolean(++i, (Boolean) arg);
						} else if (arg instanceof Double) {
							ps.setDouble(++i, (Double) arg);
						} else if (arg instanceof Float) {
							ps.setFloat(++i, (Float) arg);
						} else if (arg instanceof Timestamp) {
							ps.setTimestamp(++i, new Timestamp(((Timestamp) arg).getTime()));
						} else if (arg instanceof Date) {
							ps.setTimestamp(++i, new Timestamp(((Date) arg).getTime()));
						} else {
							String str = String.valueOf(arg);
							ps.setString(++i, str);
						}
					}
					return ps;
				}
			}, holder);
			result = holder.getKey().intValue();
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

}
