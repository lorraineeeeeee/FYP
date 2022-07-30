/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Feb-08 12:25:43 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 20023609
 *
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	/*
	 * public orderItem findByCorpUserIdAndItemId(int memberId, int itemId);
	 */
}
