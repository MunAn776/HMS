package nan.Dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import nan.Entity.Doctor;
import nan.Utils.PageTool;

public class DoctorDao {

	private QueryRunner qRunner = new QueryRunner(new ComboPooledDataSource());
	
	public List<Doctor> findAllDoctors(PageTool pageTool) {
		List<Doctor> doctors = null;
		try {
			doctors = qRunner.query("select * from doctor limit ?, ?", new BeanListHandler<Doctor>(Doctor.class),
					pageTool.getStartIndex(), pageTool.getPageSize());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctors;
	}

	public int insertDoctor(Doctor doctor) {
		int row = 0;
		try {
			row = qRunner.update("insert into doctor values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					doctor.getDname(), doctor.getCardno(), doctor.getPhone(), doctor.getGender(),
					doctor.getAge(), doctor.getBirthday(), doctor.getEmail(), doctor.getDepartment(), 
					doctor.getDegree(), doctor.getMark());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public Doctor findDoctorByDid(String did) {
		Doctor doctor = null;
		try {
			doctor = qRunner.query("select * from doctor where did = ?", new BeanHandler<Doctor>(Doctor.class), did);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctor;
	}

	public int updateDoctor(Doctor doctor) {
		int row = 0;
		try {
			row = qRunner.update("update doctor set dname = ?, cardno = ?, phone = ?, gender = ?, "
					+ "age = ?, birthday = ?, email = ?, department = ?, degree = ?, mark = ? where did = ?", 
					doctor.getDname(), doctor.getCardno(), doctor.getPhone(), doctor.getGender(),
					doctor.getAge(), doctor.getBirthday(), doctor.getEmail(), doctor.getDepartment(), 
					doctor.getDegree(), doctor.getMark(), doctor.getDid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	//获取医生数量的方法
	public int selectDoctorCount() {
		int count = 0;
		try {
			long c = (Long)qRunner.query("select count(*) from doctor", new ScalarHandler());
			count = (int) c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public int deleteDoctor(String ids) {
		int row = 0;
		try {
			row = qRunner.update("delete from doctor where did in (" + ids + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public Doctor checkCardno(String cardno) {
		Doctor doctor = null;
		try {
			doctor = qRunner.query("select * from doctor where cardno = ?", new BeanHandler<Doctor>(Doctor.class), cardno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctor;
	}

	public Doctor checkPhone(String phone) {
		Doctor doctor = null;
		try {
			doctor = qRunner.query("select * from doctor where phone = ?", new BeanHandler<Doctor>(Doctor.class), phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctor;
	}

	public Doctor checkEamil(String email) {
		Doctor doctor = null;
		try {
			doctor = qRunner.query("select * from doctor where email = ?", new BeanHandler<Doctor>(Doctor.class), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctor;
	}


}
