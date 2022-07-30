/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-20 4:27:15 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author 20021900
 *
 */
@Entity
@Table(name = "counter_staff2")
@SQLDelete(sql = "UPDATE counter_staff2 SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CounterStaff2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence_emp")
	@GenericGenerator(name = "hibernate_sequence_emp", strategy = "e62f.rabiatuladawiyah.fyp.CounterStaffIdGenerator", parameters = {
			@Parameter(name = AppointmentIdGenerator.VALUE_PREFIX_PARAMETER, value = "CS"),
			@Parameter(name = AppointmentIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id; 
	private String details;
	private String counterNo;
	private boolean deleted = Boolean.FALSE;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}
	

	public void setDetails(String details) {
		this.details = details;
	}


	public String getCounterNo() {
		return counterNo;
	}


	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	} 
	
	
	

}
