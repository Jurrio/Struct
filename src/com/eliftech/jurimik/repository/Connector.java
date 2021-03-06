package com.eliftech.jurimik.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.eliftech.jurimik.constants.Database;


public class Connector {
	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;

	int executeUpdate(String query) {
		int result = 0;
		
		try {
			connection = getDBConnection();
	        createStatement();
	        result = statement.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }
	
	ResultSet executeQuery(String query) {
		try {
			connection = getDBConnection();
			createStatement();
		    resultSet = statement.executeQuery(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return resultSet;
    }
		
	private Connection getDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Database.DRIVER);
        java.util.Properties properties = new java.util.Properties();
        properties.put("user", Database.USER);
        properties.put("password", Database.PASSWORD);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "UTF-8");
        return (DriverManager.getConnection(Database.URL, properties));
    }
	
	void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}
	
	private void createStatement() throws SQLException {
        statement = connection.createStatement();
        try {
        	statement.executeUpdate("USE " + Database.DATABASE_NAME);
        } catch (SQLException e) {
        	repairDatabase();
        }
    }

	void repairDatabase() throws SQLException {
		statement.executeUpdate("CREATE DATABASE " + Database.DATABASE_NAME);
    	statement.executeUpdate("USE " + Database.DATABASE_NAME);
    	statement.executeUpdate("CREATE TABLE " + Database.TABLE_COMPANY + 
    			" (id INT AUTO_INCREMENT NOT NULL," +
    			" name VARCHAR(255) NOT NULL," + 
    			" earnings INT NOT NULL," + 
    			" totalEarnings INT NOT NULL," +
    			" parent INT," + 
    			" PRIMARY KEY (id));");		
	}
}
