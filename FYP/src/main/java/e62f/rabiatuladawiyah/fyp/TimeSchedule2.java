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

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author  20008933
 *
 */
@Entity
public class TimeSchedule2 {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id; 
	private String time;
	
	@OneToMany(mappedBy="timesch")
	private Set<Appointment2> appointment;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
