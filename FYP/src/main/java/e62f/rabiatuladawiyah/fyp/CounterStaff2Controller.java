/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-20 4:30:36 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 20021900
 *
 */
@Controller
public class CounterStaff2Controller {
	@Autowired
	private CounterStaff2Repo csRepo;
	@Autowired
	private CounterStaffService csService;
	@GetMapping("/cstaff")
	public String viewCounterStaff(Model model, @Param("keyword") String keyword) {
		List<CounterStaff2> listCounter = csService.getAllPackages(keyword);
		
		model.addAttribute("listCounter", listCounter);
		model.addAttribute("keyword", keyword);
		return "view_counterstaff";
		}
	@GetMapping("/cstaff/add")
	public String addCS(Model model) {
		model.addAttribute("counterstaff2", new CounterStaff2());
		return "add_counterstaff";
	}
	
	@PostMapping("/cstaff/save")
	public String saveCategory(CounterStaff2 counterstaff2) {

		csRepo.save(counterstaff2);
		return "redirect:/cstaff";
	}

	@GetMapping("/cstaff/edit/{id}")
	public String editQuestion(@PathVariable("id") String id, Model model) {

		CounterStaff2 counterstaff2 = csRepo.getById(id);
		model.addAttribute("counterstaff2", counterstaff2);

		return "edit_counterstaff";
	}
	@PostMapping("/cstaff/edit/{id}")
	public String saveUpdatedQuestions(@PathVariable("id") String id, CounterStaff2 counterstaff2) {

		csRepo.save(counterstaff2);
		return "redirect:/cstaff";
	}
	@GetMapping("/cstaff/delete/{id}")
	public String deleteEmployee(@PathVariable("id") String  id) {

		csRepo.deleteById(id);

		return "redirect:/cstaff";
	}
	
	
}

	


	




