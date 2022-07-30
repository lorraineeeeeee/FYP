/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-May-06 1:38:07 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;



import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author 20021900
 *
 */
public interface CorpUserRepository extends JpaRepository<CorpUser, Integer> {
	
	  public CorpUser findByUsername(String username); 
	  public CorpUser findById(int id);




}
