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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TestResultsController {

	@Autowired
	private PackageRepository packageRepo;

	@Autowired
	private TestResultsRepository TRRepo;

	@Autowired
	private CorpUserRepository corpuserRepo;

	@Autowired
	private Appointment2Repository apptRepo;
	
	@Autowired
	private ItemRepository ItemRepo;
	


	@GetMapping(path = "/testresults/appt/{id}") // Pitems in package 1
	public String viewtestresults(@PathVariable("id") String id, Model model ) {
    	//Appointment appointment= apptRepo.getById(id);
    	List<TestResults> trlist = TRRepo.findByAppointment_id(id);
    	
    	model.addAttribute("trList", trlist); // add it to the model
		return "view_testresults"; // view the package items	
	}

	
	
	@GetMapping("/testresults/add/{id}") // Add results in a specific Appointment 
	public String addTestResult(@PathVariable("id") String id, Model model) {

		model.addAttribute("test_results", new TestResults()); 
		model.addAttribute("appointment", new Appointment2()); 
		
		Appointment2 lista = apptRepo.getById(id); // Get Appointment Id 
		model.addAttribute("listA", lista);

		List<Item> listI = ItemRepo.findAll();
		model.addAttribute("listI", listI);

		return "add_testresults";
	}

	@PostMapping("/testresults/save")
	public String saveTestResult(TestResults test_results) {
		TestResults saved = TRRepo.save(test_results);
		return "redirect:/testresults/add/" +saved.getAppointment().getId();
	}
	
	
	@GetMapping("/testresults/edit/{id}") // Add items in a specific Package
	public String editTestResult(@PathVariable Integer id, Model model) {

		TestResults test_results = TRRepo.getById(id);
		model.addAttribute("test_results", test_results);
		
		List<Appointment2> listA = apptRepo.findAll();
		model.addAttribute("listA", listA);
	
		List<Item> listI = ItemRepo.findAll();
		model.addAttribute("listI", listI);
		
		
		return "edit_testresults";
	}

	@PostMapping("/testresults/edit/{id}")
	public String saveUpdatedTestResult(@PathVariable Integer id,TestResults test_results) {

		TestResults saved = TRRepo.save(test_results);
		return "redirect:/testresults/appt/" + saved.getAppointment().getId();

	}
	@GetMapping("/testresults/delete/{id}") // Add items in a specific Package
	public String deletePackageItem(@PathVariable Integer id,String idd,Model model) {
		List<TestResults> trlist = TRRepo.findByAppointment_id(idd);

		model.addAttribute("trList", trlist); // add it to the model

		TRRepo.deleteById(id);
		return "view_testresults";

	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
