package com.zilker.mymodule.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import com.zilker.mymodule.constants.Constants;

public class ConnectionClass {
	Connection connection;
	Constants constants;
	public Logger logger = Logger.getLogger(ConnectionClass.class.getName());

	public Connection getConnection() {
		constants = new Constants();
		try {
			Class.forName(constants.JDBC_DRIVER);
			connection = DriverManager.getConnection(constants.DB_NAME, constants.USERNAME,
					constants.PASSWORD);
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_CREATE_CONNECTION);
		}
		return connection;
	}

	public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.info(constants.EXCEPTION_UNABLE_TO_CLOSE_CONNECTION);
		}
	}
}
