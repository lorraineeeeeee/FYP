/**
 * 
 * I declare that this code was written by me, 20008933. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Samia Afrin
 * Student ID: 20008933
 * Class: FYP
 * Date created: 2022-May-04 02:35:52 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author 20008933
 *
 */

@Controller
public class PackageItem_Controller {

	@Autowired
	private PackageItem_Repository PIRepo;

	@Autowired
	private PackageRepository packageRepo;

	@Autowired
	private ItemRepository ItemRepo;


	@GetMapping(path = "/Pitems/package/{id}") // Pitems in package 1
	public String getItemsbyCategory(@PathVariable Integer id, Model model) {

		List<PackageItem> listPitems = PIRepo.findByPackages_Id(id); // Finds the package id

		model.addAttribute("listPItems", listPitems); // add it to the model
		return "view_PItems"; // view the package items
	}

	@GetMapping("/Pitems/add/{id}") // Add items in a specific Package
	public String addPackageItem(@PathVariable Integer id, Model model) {

		model.addAttribute("package_item", new PackageItem()); // Adding into the PackageItem class
		model.addAttribute("package", new Package());

		Package listPitems = packageRepo.getById(id); // Get Package Id
		model.addAttribute("listPItems", listPitems);

		List<Item> listI = ItemRepo.findAll();
		model.addAttribute("listI", listI);

		return "add_PItems";
	}

	@PostMapping("/Pitems/save")
	public String savePackageItem(PackageItem package_item) {
		PackageItem saved = PIRepo.save(package_item);
		return "redirect:/Pitems/add/" + saved.getPackages().getId();

	}



	@GetMapping("/Pitems/delete/{id}") // Add items in a specific Package
	public String deletePackageItem(@PathVariable("id") Integer id , HttpServletRequest request) {

		PIRepo.deleteById(id);
		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;

	}

}
