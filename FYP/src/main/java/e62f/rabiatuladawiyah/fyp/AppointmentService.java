/**
 * I declare that this code was written by me, 20018528. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Matthew Ong
 * Student ID: 20018528
 * Class: FYP
 

 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 20018528
 *
 */
@Service
public class AppointmentService {
	 @Autowired
	 private Appointment2Repository appt2Repo;

	 public List<Appointment2> getAllAppointment2(String keyword){
		 if(keyword != null) {
			 return appt2Repo.findAll(keyword); //List<Package> list =  (List<Package>)repository.findAll();
		 }

		  return appt2Repo.findAll();
		 }
}
