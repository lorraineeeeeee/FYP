/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee, Rabiatul Adawiyah
 * Student ID: 20023609, 20021900
 * Class: FYP
 * 
 */
package e62f.rabiatuladawiyah.fyp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/home")
	public String index() {
		return "homepage";
	}
	@GetMapping("/contactus")
	public String ContactUs() {
		return "contactus";
	}
	@GetMapping("/aboutus")
	public String AboutUs() {
		return "aboutus";
	}
	
	}


