/**
 * 
 * I declare that this code was written by me, 20023609. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Lorraine Lee
 * Student ID: 20023609
 * Class: E62F
 * Date created: 2022-Jul-23 9:49:29 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 20023609
 *
 */
@Controller
public class ReportController {
	@Autowired
	private SurveyRepository surveyRepo;

	@Autowired
	private Appointment2Repository appt2Repo;
	@Autowired
	private TestResultsRepository TRRepo;
	@GetMapping("/allInfo")
	public String viewAllInfo() {
		return "view_all_reports";
	}

	@GetMapping("/information")
	public String viewSingleSurvey(Model model) {
		int Total18s = surveyRepo.findAll18s();
		int Total30s = surveyRepo.findAll30s();
		int Total40s = surveyRepo.findAll40s();
		int Total50s = surveyRepo.findAll50s();
		int Total60s = surveyRepo.findAll60s();
		int total = surveyRepo.findTotalCount();
		double Percentage18s = Total18s / total;
		double Percentage30s = Total30s / total;
		double Percentage40s = Total40s / total;
		double Percentage50s = Total50s / total;
		double Percentage60s = Total60s / total;

		model.addAttribute("Percentage18s", Percentage18s);
		model.addAttribute("Percentage30s", Percentage30s);
		model.addAttribute("Percentage40s", Percentage40s);
		model.addAttribute("Percentage50s", Percentage50s);
		model.addAttribute("Percentage60s", Percentage60s);

		model.addAttribute("Total18s", Total18s);
		model.addAttribute("Total30s", Total30s);
		model.addAttribute("Total40s", Total40s);
		model.addAttribute("Total50s", Total50s);
		model.addAttribute("Total60s", Total60s);
		model.addAttribute("total", total);

		return "survey_information";
	}

	@GetMapping("/information2")
	public String showInfo2(Model model) {
		int month1 = appt2Repo.showMonth1();
		int month2 = appt2Repo.showMonth2();
		int month3 = appt2Repo.showMonth3();
		int month4 = appt2Repo.showMonth4();
		int month5 = appt2Repo.showMonth5();
		int month6 = appt2Repo.showMonth6();
		int month7 = appt2Repo.showMonth7();
		int month8 = appt2Repo.showMonth8();
		int month9 = appt2Repo.showMonth9();
		int month10 = appt2Repo.showMonth10();
		int month11 = appt2Repo.showMonth11();
		int month12 = appt2Repo.showMonth12();

		int total2 = appt2Repo.findTotalCount();

		model.addAttribute("month1", month1);
		model.addAttribute("month2", month2);
		model.addAttribute("month3", month3);
		model.addAttribute("month4", month4);
		model.addAttribute("month5", month5);
		model.addAttribute("month6", month6);
		model.addAttribute("month7", month7);
		model.addAttribute("month8", month8);
		model.addAttribute("month9", month9);
		model.addAttribute("month10", month10);
		model.addAttribute("month11", month11);
		model.addAttribute("month12", month12);

		model.addAttribute("total2", total2);

		return "appointment_information";
	}
	@GetMapping("information3")
	public String showApptInfo2(Model model) {
		int classicMen = appt2Repo.showClassicMen();
		int ClassicWomen = appt2Repo.showClassicWomen();
		int EliteMen = appt2Repo.showEliteMen();
		int EliteWomen = appt2Repo.showEliteWomen();
		int ExclusiveMen = appt2Repo.showExclusiveMen();
		int ExclusiveWomen = appt2Repo.showExclusiveWomen();
		int SignatureMen = appt2Repo.showSignatureMen();
		int SignatureWomen = appt2Repo.showSignatureWomen();
		int PremierMen = appt2Repo.showPremierMen();
		int PremierWomen = appt2Repo.showPremierWomen();
		int PrestigeMen = appt2Repo.showPrestigeMen();
		int PrestigeWomen = appt2Repo.showPrestigeWomen();

		int total = appt2Repo.showTotalPackage();

		model.addAttribute("classicMen", classicMen);
		model.addAttribute("ClassicWomen", ClassicWomen);
		model.addAttribute("EliteMen", EliteMen);
		model.addAttribute("EliteWomen", EliteWomen);
		model.addAttribute("ExclusiveMen", ExclusiveMen);
		model.addAttribute("ExclusiveWomen", ExclusiveWomen);
		model.addAttribute("SignatureMen", SignatureMen);
		model.addAttribute("SignatureWomen", SignatureWomen);
		model.addAttribute("PremierMen", PremierMen);
		model.addAttribute("PremierWomen", PremierWomen);
		model.addAttribute("PrestigeMen", PrestigeMen);
		model.addAttribute("PrestigeWomen", PrestigeWomen);

		model.addAttribute("total", total);

		return "appointment_information2.html";
	}
	@GetMapping("patientsReport/Info1")
	public String viewPatientInfo1(Model model) {
		    int totalUW = TRRepo.findTotalUnderWeight();
		    int totalHW = TRRepo.findTotalHealthyWeight();
		    int totalOW = TRRepo.findTotalOverWeight();
		    int totalOB = TRRepo.findTotalObese();
		    int overallTotal = totalUW + totalHW + totalOW + totalOB;
		    
		    
		    model.addAttribute("totalUnderWeight", totalUW);
		    model.addAttribute("totalHealthyWeight", totalHW);
		    model.addAttribute("totalOverWeight", totalOW);
		    model.addAttribute("totalObese", totalOB);
		    model.addAttribute("total", overallTotal);
		
		return "patient_info1";
	}
	
	@GetMapping("/patientsReport/Info2")
	public String viewPatientInfo2(Model model) {
		    int totalSM = surveyRepo.findTotalNonSmokers();
		    int totalNS = surveyRepo.findTotalSmokers();
		    int overallTotal = totalSM + totalNS;
		    
		    
		    model.addAttribute("totalSM", totalSM);
		    model.addAttribute("totalNS", totalNS);
		    model.addAttribute("overallTotal", overallTotal);
		
		return "patient_info2";
	}
	
	
	@GetMapping("/patients/ReportInfo")
	public String viewPatientInfoTable(Model model) {
		
		return "paitientInfo_table";
	}

}
