/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-May-25 2:02:40 am 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 20021900
 *
 */
public interface PatientRepository extends JpaRepository<Patient, String> {


	 @Query("SELECT patient FROM Patient patient WHERE patient.id LIKE %?1%" 
	 + " OR patient.firstName LIKE %?1%" 
	 + " OR patient.lastName LIKE %?1%" 
	 + " OR patient.contactNo LIKE %?1%" 
	 + " OR patient.nric LIKE %?1%" 

)

	 public List <Patient> findAll(String keyword);
}
