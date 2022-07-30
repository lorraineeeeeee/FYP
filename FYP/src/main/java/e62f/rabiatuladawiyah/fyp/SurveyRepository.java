/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Jul-13 12:29:41 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 20023609
 *
 */

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

	@Query("SELECT COUNT(age) FROM Survey survey")
	public int findTotalCount();

	@Query("SELECT COUNT(age) FROM Survey survey WHERE survey.age = '18-29'")
	public int findAll18s();

	@Query("SELECT COUNT(age) FROM Survey survey WHERE survey.age = '30-39'")
	public int findAll30s();

	@Query("SELECT COUNT(age) FROM Survey survey WHERE survey.age = '40-49'")
	public int findAll40s();

	@Query("SELECT COUNT(age) FROM Survey survey WHERE survey.age = '50-59'")
	public int findAll50s();

	@Query("SELECT COUNT(age) FROM Survey survey WHERE survey.age = '60 and above'")
	public int findAll60s();
	
	 @Query("SELECT COUNT(smoke) FROM Survey survey WHERE smoke='no'")
	 public int findTotalSmokers();
	 
	 @Query("SELECT COUNT(smoke) FROM Survey survey WHERE smoke='yes'")
	 public int findTotalNonSmokers();
}
