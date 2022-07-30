/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Feb-08 12:20:17 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author 20023609
 *
 */
@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String orderid;
	private String Transactionid;
	@ManyToOne
	@JoinColumn(name = "corpuser_id")
	private CorpUser corpUser;
	@ManyToOne
	@JoinColumn(name = "appt_id")
	private Appointment2 appt;
	private int quantity;
	@Transient
	private double subtotal;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the transactionid
	 */
	public String getTransactionid() {
		return Transactionid;
	}

	/**
	 * @param transactionid the transactionid to set
	 */
	public void setTransactionid(String transactionid) {
		Transactionid = transactionid;
	}

	/**
	 * @return the member
	 */
	public CorpUser getMember() {
		return corpUser;
	}

	/**
	 * @param member the member to set
	 */
	public void setCorpUser(CorpUser corpuser) {
		this.corpUser = corpuser;
	}

	/**
	 * @return the item
	 */
	public Appointment2 getAppt() {
		return appt;
	}

	/**
	 * @param item the item to set
	 */
	public void setAppt(Appointment2 appt) {
		this.appt = appt;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
