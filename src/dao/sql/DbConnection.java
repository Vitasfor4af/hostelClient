package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {
	private String host;
	private String root;
	private String password;
	private String nameDb;
	private String url;

	private Properties properties = new Properties();



	private Connection connect;

	public DbConnection(String host, String root, String password, String nameDb) {
		this.host = host;
		this.root = root;
		this.password = password;
		this.nameDb = nameDb;
	}

	public DbConnection(String url, Properties properties) {
		this.url = url;
		this.properties = properties;

	}

	public void initProperties() {

		url = "jdbc:mysql://" + host + "/" + nameDb + "?useUnicode=true&serverTimezone=UTC";

		properties.setProperty("user", root);
		properties.setProperty("password", password);
		properties.setProperty("characterEncoding", "UTF-8");
		properties.setProperty("UseUnicode", "true");

		System.out.println("URL: " + url);

	}

	public void init() {
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String query) {
		ResultSet result = null;
		try {
			Statement stmt = connect.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateQuery(String query) {
		try {
			Statement stmt = connect.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnect() {
		return connect;
	}
}
