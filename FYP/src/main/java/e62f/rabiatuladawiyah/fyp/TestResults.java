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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author 20008933
 *
 */
@Entity
@Table(name = "test_results")
@SQLDelete(sql = "UPDATE test_results SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class TestResults {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Comment;
	private String unit;
	private boolean deleted = Boolean.FALSE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne
	@JoinColumn(name = "appt2_id", nullable = false)
	private Appointment2 appointment;


	public Appointment2 getAppointment() {
		return appointment;
	}

	
	public void setAppointment(Appointment2 appointment) {
		this.appointment = appointment;
	}

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item items;

	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	


}
