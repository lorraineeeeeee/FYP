/**
 * 
 * I declare that this code was written by me, 20008933. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Samia Afrin
 * Student ID: 20008933
 * Class: FYP
 * Date created: 2022-May-04 02:02:18 
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
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author 20008933
 *
 */

@Entity
@Table(name = "package_item")
@SQLDelete(sql = "UPDATE package_item SET deleted = true WHERE package_item_id=?")
@Where(clause = "deleted=false")
public class PackageItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int package_item_id;
	private boolean deleted = Boolean.FALSE;
	
	public int getPackage_item_id() {
		return package_item_id;
	}

	public void setPackage_item_id(int package_item_id) {
		this.package_item_id = package_item_id;
	}
	
	@ManyToOne
	@JoinColumn(name="package_id", nullable=false)
	private Package packages;
	
	public Package getPackages() {
		return packages;
	}

	public void setPackages(Package packages) {
		this.packages = packages;
	}
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	private Item items;

		public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	

}
