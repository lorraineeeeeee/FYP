/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Jul-21 9:40:31 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 20023609
 *
 */
@Controller
public class CartApptController {
	@Autowired
	private CartApptRepository cartApptRepo;

	@Autowired
	private Appointment2Repository appt2Repo;

	@Autowired
	private CorpUserRepository memberRepo;

	@Autowired
	private OrderItemRepository orderRepo;
	/*
	 * @Autowired private PackageRepository packageRepo;
	 */
	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping("/cart")
	public String showCart(Model model, Principal principal) {

		// Get currently logged in user
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		// Get shopping cart items added by this user
		// *Hint: You will need to use the method we added in the CartItemRepository
		List<CartAppt> cartApptList = cartApptRepo.findByCorpuserId(loggedInMemberId);

		// Add the shopping cart items to the model
		model.addAttribute("cartApptList", cartApptList);

		// Calculate the total cost of all items in the shopping cart
		double cartTotal = 0.0;
		for (int i = 0; i < cartApptList.size(); i++) {
			CartAppt currentCartItem = cartApptList.get(i);
			int itemQuantityInCart = currentCartItem.getQuantity();

			Appointment2 appt = currentCartItem.getAppt();
			double itemPrice = appt.getPackages().getCost();
			cartTotal += itemPrice * itemQuantityInCart;
		}

		// Add the shopping cart total to the model
		model.addAttribute("cartTotal", cartTotal);
		model.addAttribute("corpUserId", loggedInMemberId);

		return "cart";
	}

	@PostMapping("/cart/add/{apptId}")
	public String addToCart(@PathVariable("apptId") String apptId, Principal principal) {

		// Get currently logged in user
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		// Check in the cartItemRepo if item was previously added by user.
		// *Hint: we will need to write a new method in the CartItemRepository
		CartAppt cartAppt = cartApptRepo.findByCorpuserIdAndAppointment_id(loggedInMemberId, apptId);

		// if the item was previously added, then we get the quantity that was
		// previously added and increase that

		if (cartAppt != null) {
			int currentQuantity = cartAppt.getQuantity();
			/*
			 * cartAppt.setQuantity(currentQuantity + quantity);
			 */ cartApptRepo.save(cartAppt);
		} else {
			// if the item was NOT previously added,
			// then prepare the item and member objects
			Appointment2 appt = appt2Repo.getById(apptId);
			CorpUser member = memberRepo.getById(loggedInMemberId);

			// Create a new CartItem object

			CartAppt newCartItem = new CartAppt();

			// Set the item and member as well as the new quantity in the new CartItem
			// object

			newCartItem.setAppt(appt);

			newCartItem.setCorpuser(member);

			// Save the new CartItem object to the repository

			cartApptRepo.save(newCartItem);
		}

		return "redirect:/cart";
	}



	@GetMapping("/cart/remove/{id}")
	public String removeFromCart(@PathVariable("id") int cartItemId) {

		// Remove item from cartItemRepo
		cartApptRepo.deleteById(cartItemId);

		return "redirect:/cart";
	}

	@PostMapping("/cart/process_order")
	public String processOrder(Model model, @RequestParam("formcartTotal") double cartTotal,
			@RequestParam("corpUserId") int memberId, @RequestParam("orderId") String orderId,
			@RequestParam("transactionId") String transactionId) {
		// Retrieve cart items purchased
		List<CartAppt> cartApptList = cartApptRepo.findByCorpuserId(memberId);
		// Get member object
		CorpUser member = memberRepo.getById(memberId);
		// Loop to iterate through all cart items
		for (int i = 0; i < cartApptList.size(); i++) {
			// Retrieve details about current cart item
			CartAppt currentCartItem = cartApptList.get(i);
			int qtyPurchased = currentCartItem.getQuantity();
			// Update item table

			// Add item to order table
			OrderItem newOrder = new OrderItem();
			/*
			 * newOrder.setAppt(apptId);;
			 */
			newOrder.setCorpUser(member);
			newOrder.setQuantity(qtyPurchased);
			newOrder.setOrderid(orderId);
			newOrder.setTransactionid(transactionId);
			newOrder.setSubtotal(currentCartItem.getSubtotal());
			orderRepo.save(newOrder);

			// clear cart items belonging to member
			cartApptRepo.deleteById(currentCartItem.getId());
		}
		// Pass info to view to display success page
		model.addAttribute("cartTotal", cartTotal);
		model.addAttribute("cartApptList", cartApptList);
		model.addAttribute("corpUserId", member);
		model.addAttribute("orderId", orderId);
		model.addAttribute("transactionId", transactionId);

		// Send email
		String subject = "Appointment Paid. Thank you!";
		String body = "Thank you for your payment!\n" + "Order ID: " + orderId + "\n" + "Transaction ID: "
				+ transactionId;
		String to = member.getEmail();
		sendEmail(to, subject, body);

		return "payment_success";
	}

	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		System.out.println("Sending");
		javaMailSender.send(msg);
		System.out.println("Sent");
	}

}
