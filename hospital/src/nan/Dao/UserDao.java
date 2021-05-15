package nan.Dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import nan.Entity.User;

public class UserDao {

	private QueryRunner qRunner = new QueryRunner(new ComboPooledDataSource());
	
	public User checkUsername(String username) {
		User user = null;
		try {
			user = qRunner.query("select * from user where username = ?", new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public User checkEmail(String email) {
		User user = null;
		try {
			user = qRunner.query("select * from user where email = ?", new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public int registerUser(User user) {
		int row = 0;
		try {
			row = qRunner.update("insert into user values(null, ?, ?, ?, ?, ?)",
					user.getUname(), user.getPassword(), user.getUsername(), user.getEmail(), user.getCreate_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public User checkLogin(User user) {
		User newUser = null;
		try {
			newUser = qRunner.query("select * from user where username = ? and password = ?", 
					new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newUser;
	}

}
