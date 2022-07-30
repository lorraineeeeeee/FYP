/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Jul-13 12:29:30 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 20023609
 *
 */
@Controller
public class SurveyController {
	@Autowired
	private SurveyRepository surveyRepo;

	@GetMapping("/response")
	public String viewAllResponse(Model model) {

		List<Survey> listSurvey = surveyRepo.findAll();
		model.addAttribute("listSurvey", listSurvey);
		return "view_response";
	}

	@GetMapping("/survey/start")
    public String StartSurvey(Model model) {

        model.addAttribute("survey", new Survey());
        return "Survey";
    }

	@PostMapping("/survey/save")
	public String saveSurvey(Survey survey) {

		surveyRepo.save(survey);
		return "redirect:/appt/add";
	}
	@GetMapping("/survey2/start")//For walk-in patient
    public String StartSurvey2(Model model) {

        model.addAttribute("survey", new Survey());
        return "Survey2";
    }

  @PostMapping("/survey2/save")//For walk-in patient
  public String saveSurvey2(Survey survey) {

    surveyRepo.save(survey);
    return "redirect:/appt2/add";
  }
	// View single Question
	@GetMapping("/response/{id}")
	public String viewSingleSurvey(@PathVariable("id") Integer id, Model model) {

		Survey survey = surveyRepo.getById(id);
		model.addAttribute("survey", survey);

		return "view_single_survey";
	}
	


}
