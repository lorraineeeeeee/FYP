/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-20 4:37:28 pm 
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
public interface CounterStaff2Repo extends JpaRepository<CounterStaff2, String>  {
	 @Query("SELECT counterstaff2 FROM CounterStaff2 counterstaff2 WHERE counterstaff2.id LIKE %?1%" 
	 + " OR counterstaff2.counterNo LIKE %?1%" 
	 + " OR counterstaff2.details LIKE %?1%" 

)

	 public List <CounterStaff2> findAll(String keyword);
}
