package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import entities.Account;
import utils.DBUtil;

public class AccountDao {
	public boolean login(Account account)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call Login(?,?,?)}");
		callableStatement.setString(1, account.getUserMail());
		callableStatement.setString(2, account.getPassword());
		callableStatement.registerOutParameter(3, Types.INTEGER);
		callableStatement.executeUpdate();
		int result = callableStatement.getInt(3);
		DBUtil.closeConnection(connection);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	public Account findUserByMail(String userMail)
			throws SQLException, ClassNotFoundException, IOException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call FindUserByMail(?)}");
		callableStatement.setString(1, userMail);
		ResultSet resultSet = callableStatement.executeQuery();
		
		Account account = null;
		
		if (resultSet.next()) {
			account = new Account(
					resultSet.getString("user_mail"),
					resultSet.getString("password"),
					resultSet.getInt("account_role"),
					resultSet.getString("user_name"),
					resultSet.getString("user_address"),
					resultSet.getString("user_phone"));
		}
		DBUtil.closeConnection(connection);
		return account;
	}
	
	public boolean isExistedMail(String userMail) throws 
			ClassNotFoundException, IOException, SQLException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call FindUserByMail(?)}");
		callableStatement.setString(1, userMail);
		ResultSet resultSet = callableStatement.executeQuery();
		boolean result = true;
		if (!resultSet.next()) {
			result =  false;
		}
		DBUtil.closeConnection(connection);
		return result;
	}
	
	public boolean registerUser(Account account) throws 
			ClassNotFoundException, IOException, SQLException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call RegisterUser(?,?,?,?,?)}");
		int param = 0;
		callableStatement.setString(++param, account.getUserMail());
		callableStatement.setString(++param, account.getPassword());
		callableStatement.setString(++param, account.getName());
		callableStatement.setString(++param, account.getAddress());
		callableStatement.setString(++param, account.getPhone());
		int result = callableStatement.executeUpdate();
		DBUtil.closeConnection(connection);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Account> listAdmin() throws 
		ClassNotFoundException, IOException, SQLException {
		Connection connection = DBUtil.getConnection();
		CallableStatement callableStatement = connection.prepareCall("{call ListAllAdmin()}");
		ResultSet resultSet = callableStatement.executeQuery();
		
		ArrayList<Account> listOfAdmins = new ArrayList<>();
		while (resultSet.next()) {
			String userMail = resultSet.getString("user_mail");
			String password = resultSet.getString("password");
			int role = resultSet.getInt("account_role");
			String userName = resultSet.getString("user_name");
			String userAddress = resultSet.getString("user_address");
			String userPhone = resultSet.getString("user_phone");

			Account account = new Account(userMail, password, role, userName, userAddress, userPhone);
			listOfAdmins.add(account);
		}
		DBUtil.closeConnection(connection);
		return listOfAdmins;
	}
}