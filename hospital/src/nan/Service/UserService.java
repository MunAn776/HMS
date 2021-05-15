package nan.Service;

import javax.servlet.http.HttpSession;



import nan.Dao.UserDao;
import nan.Entity.User;

public class UserService {
	
	private UserDao userDao = new UserDao();


	public boolean checkUsername(String username) {
		User user = userDao.checkUsername(username);
		if(user != null) {
			return true;
		}
		return false;
	}


	public boolean checkEmail(String email) {
		User user = userDao.checkEmail(email);
		if(user != null) {
			return true;
		}
		return false;
	}


	public boolean registerUser(User user) {
		int row = userDao.registerUser(user);
		if(row > 0) {
			return true;
		}
		return false;
	}


	public boolean checkLogin(User user, HttpSession session) {
		User newUser = userDao.checkLogin(user);
		if(newUser != null) {
			session.setAttribute("user", newUser);
			return true;
		}
		return false;
	}
	
}
