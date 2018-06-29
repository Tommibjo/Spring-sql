package com.juurivuohi.anonyymit_ulvojat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("messagedao")
public class MessagesDAO {

	private int id = 6;

	public Connection connect() {
		String url = "jdbc:sqlite:C:/Users/Tommi/Desktop/Database_test/messages";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("connection error: " + e.getMessage());
		}
		return connection;
	}

	public List<Messages> getMessages() {
		String sql = "SELECT * FROM messages";
		List<Messages> messageList = new ArrayList<>();
		try (Connection connection = connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Messages message = new Messages();
				message.setId(rs.getInt("id"));
				message.setName(rs.getString("name"));
				message.setMessage(rs.getString("message"));
				messageList.add(message);
			}

		} catch (SQLException e) {
			System.out.println("SQL reading error: " + e.getMessage());
		}
		return messageList;

	}

	public void writeMessage(String name, String msg) {

		String sql = "INSERT INTO messages (id, name, message) VALUES(" + latestID() + ",'" + name + "','" + msg + "')";
		try (Connection connect = this.connect();
				PreparedStatement ppstmt = connect.prepareStatement(sql);
				Statement stmt = connect.createStatement()) {
			ppstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL writing error: " + e.getMessage());
		}
		this.id++;
	}

	public int latestID() {
		int nextID = 0;
		String sql = "SELECT id FROM messages";
		try (Connection connection = connect();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				nextID = rs.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("Error fetching latest ID: " + e.getMessage());
		}
		return nextID + 1;
	}

}
