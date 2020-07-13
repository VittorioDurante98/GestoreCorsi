package it.polito.tdp.corssi.d;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectD {
	private static final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi"; 
	private static HikariDataSource ds;
	
	public static Connection getConnection(){
		if(ds==null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("root");
			config.setPassword("GoGetaSS4");
			
			config.addDataSourceProperty("cachePrepStmts", true);
			config.addDataSourceProperty("prepStmtCacheSize", 250);
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
		}
		
		try {
			return ds.getConnection();
		}catch (SQLException e) {
			System.err.println("Errore di connessione ad d");
			throw new RuntimeException(e);
		}
	}
}
