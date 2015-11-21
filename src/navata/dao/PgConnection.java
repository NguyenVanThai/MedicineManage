package navata.dao;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;


public class PgConnection {

	private Properties			props					= new Properties();

	public static final String	PATH_PROPERTIES_FILE	= "conf/config.properties";

	private String				SERVER					= "";
	private String				PORT					= "";
	private String				DATABASE				= "";
	private String				SCHEMA					= "";
	private String				USERNAME				= "";
	private String				PASSWORD				= "";
	private Connection			con;

	public void loadConnectionFromParameters(String server, String port, String database, String schema, String Username, String Password) {
		SERVER = server;
		PORT = port;
		DATABASE = database;
		SCHEMA = schema;
		USERNAME = Username;
		PASSWORD = Password;
	}

	public void loadConnectionFromProperties(String propertiesName) {
		try {
			final Path path = Paths.get(propertiesName);
			if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
				props.load(new FileInputStream(propertiesName));
			} else {
				props.load(getClass().getClassLoader().getResourceAsStream(propertiesName));
			}
			SERVER = props.getProperty("db.default.server");
			PORT = props.getProperty("db.default.port");
			DATABASE = props.getProperty("db.default.database");
			SCHEMA = props.getProperty("db.default.schema");
			USERNAME = props.getProperty("db.default.username");
			PASSWORD = props.getProperty("db.default.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			String conString = "jdbc:postgresql://" + SERVER + ":" + PORT + "/" + DATABASE + "";
			return DriverManager.getConnection(conString, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return null;
	}

	public CachedRowSet retrieve(String query, Object[] params) {
		try (Connection con = getConnection()) {
			CachedRowSet cached = new CachedRowSetImpl();
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (PreparedStatement ps = con.prepareStatement(query)) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								ps.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								ps.setLong(i + 1, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								ps.setInt(i + 1, Integer.parseInt((params[i].toString())));
							else
								ps.setObject(i + 1, params[i]);
						}
					}
					cached.populate(ps.executeQuery());
					return cached;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object retrieveSingleField(String query, Object[] params) {
		try {
			try (Connection con = getConnection()) {
				try (Statement st = con.createStatement()) {
					st.execute("set search_path = " + SCHEMA);
					try (PreparedStatement ps = con.prepareStatement(query)) {
						if (params != null) {
							for (int i = 0; i < params.length; i++) {
								if (params[i] instanceof Timestamp)
									ps.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
								else if (params[i] instanceof Long)
									ps.setLong(i + 1, Long.parseLong((params[i].toString())));
								else if (params[i] instanceof Integer)
									ps.setInt(i + 1, Integer.parseInt((params[i].toString())));
								else
									ps.setObject(i + 1, params[i]);
							}
						}
						ResultSet rs = ps.executeQuery();
						rs.next();
						return rs.getObject(1);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}

	public int execute(String query, Object[] params) {
		int result = -1;
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (PreparedStatement ps = con.prepareStatement(query)) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								ps.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								ps.setLong(i + 1, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								ps.setInt(i + 1, Integer.parseInt((params[i].toString())));
							else
								ps.setObject(i + 1, params[i]);
						}
					}
					result = ps.executeUpdate();
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public int[] executeMultiple(String query, List<Object[]> listParams) {
		int result[] = null;
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (PreparedStatement ps = con.prepareStatement(query)) {

					for (Object[] params : listParams) {
						if (params != null) {
							for (int i = 0; i < params.length; i++) {
								if (params[i] instanceof Timestamp)
									ps.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
								else if (params[i] instanceof Long)
									ps.setLong(i + 1, Long.parseLong((params[i].toString())));
								else if (params[i] instanceof Integer)
									ps.setInt(i + 1, Integer.parseInt((params[i].toString())));
								else
									ps.setObject(i + 1, params[i]);
							}
							ps.addBatch();
						}
					}
					result = ps.executeBatch();
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public CachedRowSet functionSetOffRetrieve(String query, Object[] params) {
		CachedRowSet cached = null;
		try (Connection con = getConnection()) {
			cached = new CachedRowSetImpl();
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (PreparedStatement ps = con.prepareStatement(String.format("%s %s", "select * from", query))) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								ps.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								ps.setLong(i + 1, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								ps.setInt(i + 1, Integer.parseInt((params[i].toString())));
							else
								ps.setObject(i + 1, params[i]);
						}
					}
					cached.populate(ps.executeQuery());
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return cached;
	}

	public CachedRowSet functionCursorRetrieve(String functionName, Object[] params) {
		CachedRowSet cached = null;
		try (Connection con = getConnection()) {
			cached = new CachedRowSetImpl();
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (CallableStatement proc = con.prepareCall("{? = call " + functionName + "}")) {
					proc.registerOutParameter(1, Types.OTHER);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								proc.setTimestamp(i + 2, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								proc.setLong(i + 2, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								proc.setInt(i + 2, Integer.parseInt((params[i].toString())));
							else
								proc.setObject(i + 2, params[i]);
						}
					}
					proc.execute();
					cached.populate((ResultSet) proc.getObject(1));
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return cached;
	}

	public long functionRetrieveSingle(String functionName, Object[] params) {
		long result = -1;
		try {
			if (con == null || con.isClosed())
				con = getConnection();
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (CallableStatement proc = con.prepareCall("{? = call " + SCHEMA + "." + functionName + "}")) {
					proc.registerOutParameter(1, Types.BIGINT);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								proc.setTimestamp(i + 2, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								proc.setLong(i + 2, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								proc.setInt(i + 2, Integer.parseInt((params[i].toString())));
							else
								proc.setObject(i + 2, params[i]);
						}
					}
					proc.execute();
					result = proc.getLong(1);
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public boolean functionRetrieveSingleBoolean(String functionName, Object[] params) {
		boolean result = false;
		try {
			if (con == null || con.isClosed())
				con = getConnection();
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (CallableStatement proc = con.prepareCall("{? = call " + SCHEMA + "." + functionName + "}")) {
					proc.registerOutParameter(1, Types.BOOLEAN);
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								proc.setTimestamp(i + 2, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								proc.setLong(i + 2, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								proc.setInt(i + 2, Integer.parseInt((params[i].toString())));
							else
								proc.setObject(i + 2, params[i]);
						}
					}
					proc.execute();
					result = proc.getBoolean(1);
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public void Close() {
		try {
			if (!con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

	public int functionExecute(String functionName, Object[] params) {
		int result = -1;
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (CallableStatement proc = con.prepareCall("{call " + functionName + " }")) {
					if (params != null) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof Timestamp)
								proc.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
							else if (params[i] instanceof Long)
								proc.setLong(i + 1, Long.parseLong((params[i].toString())));
							else if (params[i] instanceof Integer)
								proc.setInt(i + 1, Integer.parseInt((params[i].toString())));
							else if (params[i] instanceof Double)
								proc.setDouble(i + 1, Double.parseDouble((params[i].toString())));
							else
								proc.setObject(i + 1, params[i]);
						}
					}
					proc.executeUpdate();
					result = 1;
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public int[] functionExecuteMultiple(String functionName, List<Object[]> listParams) {
		int result[] = { -1 };
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			try (Statement st = con.createStatement()) {
				st.execute("set search_path = " + SCHEMA);
				try (CallableStatement proc = con.prepareCall("{call " + functionName + " }")) {
					if (listParams != null) {
						for (Object[] params : listParams) {
							for (int i = 0; i < params.length; i++) {
								if (params[i] instanceof Timestamp)
									proc.setTimestamp(i + 1, Timestamp.valueOf(params[i].toString()));
								else if (params[i] instanceof Long)
									proc.setLong(i + 1, Long.parseLong((params[i].toString())));
								else if (params[i] instanceof Integer)
									proc.setInt(i + 1, Integer.parseInt((params[i].toString())));
								else if (params[i] instanceof Double)
									proc.setDouble(i + 1, Double.parseDouble((params[i].toString())));
								else
									proc.setObject(i + 1, params[i]);
							}
							proc.addBatch();
						}
					}
					result = proc.executeBatch();
				}
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	public void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				if (ignoreSQLException(((SQLException) e).getSQLState()) == false) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
	}

	public boolean ignoreSQLException(String sqlState) {
		if (sqlState == null) {
			System.out.println("The SQL state is not defined!");
			return false;
		}
		if (sqlState.equalsIgnoreCase("X0Y32"))
			return true;
		if (sqlState.equalsIgnoreCase("42Y55"))
			return true;
		return false;
	}

	public static void main(String arg[]) {

	}
}
