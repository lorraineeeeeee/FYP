/**
 * 
 * I declare that this code was written by me, 20008933. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Samia Afrin
 * Student ID: 20008933
 * Class: FYP

 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @author  20008933
 *
 */
public interface TestResultsRepository extends JpaRepository<TestResults, Integer>  {
	public List<TestResults> findByAppointment_id(String appid);

	/* This portion is Done By: Matthew Ong , 20018528 */
	@Query("SELECT COUNT(result) FROM TestResults test_results WHERE item_id = 2 AND result < 18.5")
	public int findTotalUnderWeight();
	
	@Query("SELECT COUNT(result) FROM TestResults test_results WHERE item_id = 2 AND result BETWEEN 18.5 AND 24.9")
	public int findTotalHealthyWeight();
	
	@Query("SELECT COUNT(result) FROM TestResults test_results WHERE item_id = 2 AND result BETWEEN 25.0 AND 29.9")
	public int findTotalOverWeight();
	
	@Query("SELECT COUNT(result) FROM TestResults test_results WHERE item_id = 2 AND result >= 30")
	public int findTotalObese();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
