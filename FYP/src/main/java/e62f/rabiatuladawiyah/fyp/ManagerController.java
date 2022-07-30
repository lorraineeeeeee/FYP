/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-May-25 11:15:44 am 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author 20021900
 *
 */
@Controller
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepo;
	@Autowired
	private ManagersService msService;

	@GetMapping("/managers")
	public String viewEmployee(Model model, @Param("keyword") String keyword) {
		List<Managers> listManagers = msService.getAllPackages(keyword);
		model.addAttribute("listManagers", listManagers);
		model.addAttribute("keyword", keyword);
		return "view_managers";

	}

	// add
	@GetMapping("/managers/add")
	public String addManagers(Model model) {
		model.addAttribute("managers", new Managers());
		return "add_managers";
	}

	@PostMapping("/managers/save")
	public String saveCategory(Managers managers,RedirectAttributes redirectAttribute) {

		managerRepo.save(managers);
		redirectAttribute.addFlashAttribute("success", "Manager registered!");
		return "redirect:/managers";
	}

	// edit

	@GetMapping("/managers/edit/{id}")
	public String editCategory(@PathVariable("id") String id, Model model) {

		Managers managers = managerRepo.getById(id);
		model.addAttribute("managers", managers);

		return "edit_managers";
	}

	@PostMapping("/managers/edit/{id}")
	public String saveUpdatedEmployee(@PathVariable("id") String id, Managers managers,RedirectAttributes redirectAttribute) {

		managerRepo.save(managers);
		redirectAttribute.addFlashAttribute("success", "Manager Details Updated!");
		return "redirect:/managers";
	}

	// delete

	@GetMapping("/managers/delete/{id}")
	public String deleteEmployee(@PathVariable("id") String id, RedirectAttributes redirectAttribute) {

		managerRepo.deleteById(id);
		redirectAttribute.addFlashAttribute("failed", "Manager Removed.");
		return "redirect:/managers";
	}

}



