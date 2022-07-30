/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-22 7:17:06 pm 
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
public interface Appointment2Repository extends JpaRepository<Appointment2, String> {
	public List<Appointment2> findByPackages_Id(int id);
	public List<Appointment2> findByCorpuserId(int id);
	
	@Query("SELECT appointment2 FROM Appointment2 appointment2 WHERE appointment2.id LIKE %?1%" + " OR appointment2.nric LIKE %?1%"
			+ " OR appointment2.employeeid LIKE %?1%")
	public List<Appointment2> findAll(String keyword);
	
	@Query("SELECT appointment2 FROM Appointment2 appointment2 ORDER BY id DESC")
	public List<Appointment2> findAllId(String id);
	
	/* This Portion Onwards is Done By: Lorraine Lee,20023609*/
	
	//Appointment Report
	@Query("SELECT COUNT(date),MONTH(date) FROM Appointment2 appointment")
	public int findTotalCount();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 1")
	public int showMonth1();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 2")
	public int showMonth2();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 3")
	public int showMonth3();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 4")
	public int showMonth4();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 5")
	public int showMonth5();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 6")
	public int showMonth6();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 7")
	public int showMonth7();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 8")
	public int showMonth8();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 9")
	public int showMonth9();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 10")
	public int showMonth10();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 11")
	public int showMonth11();

	@Query("SELECT COUNT(date) FROM Appointment2 appointment WHERE MONTH(date) = 12")
	public int showMonth12();

   // Package Report
	@Query("SELECT COUNT(package_id) FROM Appointment2 a")
	public int showTotalPackage();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 1")
	public int showClassicMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 2")
	public int showClassicWomen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 3")
	public int showEliteMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 4")
	public int showEliteWomen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 5")
	public int showExclusiveMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 6")
	public int showExclusiveWomen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 7")
	public int showSignatureMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 8")
	public int showSignatureWomen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 9")
	public int showPremierMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 10")
	public int showPremierWomen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 11")
	public int showPrestigeMen();

	@Query("SELECT COUNT(package_id) FROM Appointment2 WHERE package_id = 12")
	public int showPrestigeWomen();
	/* Lorraine Lee,20023609*/
}


