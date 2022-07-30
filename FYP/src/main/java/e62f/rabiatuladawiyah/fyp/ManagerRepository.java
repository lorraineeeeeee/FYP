/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-May-25 11:29:42 am 
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
public interface ManagerRepository extends JpaRepository<Managers,String>{

	 @Query("SELECT managers FROM Managers managers WHERE managers.id LIKE %?1%" 
	 + " OR managers.name LIKE %?1%" 
	 + " OR managers.department LIKE %?1%" 

)

	 public List <Managers> findAll(String keyword);

}
