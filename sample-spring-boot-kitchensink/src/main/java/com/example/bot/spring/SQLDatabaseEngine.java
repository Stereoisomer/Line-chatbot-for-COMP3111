package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	@Override
	String search(String text) throws Exception {
		//Write your code here
		String response = null;
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT response FROM chatbotDB WHERE lower(concat('%',keyword, '%')) LIKE lower(concat('%', ?, '%'));");
			stmt.setString(1, text);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) throw new Exception("I said you find nothing here lol");
			do {
				response = rs.getString(1);
			} while(rs.next());
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	//private static boolean LOCAL = true;
	private Connection getConnection() throws URISyntaxException, SQLException {
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username;
		String password;
		String dbUrl;
		/*
		if(true) {
			username="postgres";
			password="5tere0150mer";
			dbUrl="jdbc:postgresql://localhost:5432/chatbotDB";
		}else
		*/
		//{
			username = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
			dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		//}
		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
	}

}
