package nan.Service;

import java.util.List;


import nan.Dao.DoctorDao;
import nan.Entity.Doctor;
import nan.Utils.PageTool;

public class DoctorService {
	
	private DoctorDao doctorDao = new DoctorDao();

	public List<Doctor> findAllDoctors(PageTool pageTool) {
		return doctorDao.findAllDoctors(pageTool);
	}

	public boolean insertDoctor(Doctor doctor) {
		int row = doctorDao.insertDoctor(doctor);
		if(row > 0) {
			return true;
		}
		return false;
	}

	public Doctor findDoctorByDid(String did) {
		return doctorDao.findDoctorByDid(did);
	}

	public boolean updateDoctor(Doctor doctor) {
		int row = doctorDao.updateDoctor(doctor);
		if(row > 0) {
			return true;
		}
		return false;
	}

	public int selectDoctorCount() {
		return doctorDao.selectDoctorCount();
	}

	public boolean deleteDoctor(String ids) {
		int row = doctorDao.deleteDoctor(ids);
		if(row > 0) {
			return true;
		}
		return false;
	}

	public boolean checkCardno(String cardno) {
		Doctor doctor = doctorDao.checkCardno(cardno);
		if(doctor != null) {
			return true;
		}
		return false;
	}

	public boolean checkPhone(String phone) {
		Doctor doctor = doctorDao.checkPhone(phone);
		if(doctor != null) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(String email) {
		Doctor doctor = doctorDao.checkEamil(email);
		if(doctor != null) {
			return true;
		}
		return false;
	}


	

}
