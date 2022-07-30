/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-22 7:16:21 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class Appointment2Controller {
	@Autowired
	private Appointment2Repository appt2Repo;
	@Autowired
	private PackageRepository packageRepo;
	@Autowired
	private EmployeeTypeRepository emptypeRepo;
	@Autowired
	private TimeScheduleRepository timeschRepo;
	@Autowired
	private TimeScheduleRepository2 timeschRepo2;
	@Autowired
	private CorpUserRepository corpuserRepo;
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private AppointmentService apptservice;

	@GetMapping("/appt")
	public String viewAppointment(Model model, @Param("keyword") String keyword) {
		// Retrieve all categories from db
		List<Appointment2> listAppt = apptservice.getAllAppointment2(keyword);

		// Add the categories to the model
		model.addAttribute("listAppt", listAppt);
		model.addAttribute("keyword", keyword);
		// return the response as view
		return "view_appt";
	}

	@GetMapping("/apptuser")
	public String viewAppointmentUser(Model model, @Param("keyword") String keyword) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		// Retrieve all categories from db
		List<Appointment2> apptList = appt2Repo.findByCorpuserId(loggedInMemberId);
		List<Appointment2> listAppt = apptservice.getAllAppointment2(keyword);
		// Add the categories to the model
		model.addAttribute("keyword", keyword);
		model.addAttribute("listAppt", listAppt);
		model.addAttribute("listAppt", apptList);
		System.out.println("size" + apptList.size());

		// return the response as view
		return "view_appt2";
	}

	@GetMapping("/appt/add")
	public String addAppt(Model model, Principal principal) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		// Add the appointment to the model
		model.addAttribute("listAppt", new Appointment2());
		//
		CorpUser user = corpuserRepo.getById(loggedInMemberId);
		model.addAttribute("", user);

		List<Package> packageList = packageRepo.findAll();
		// Add the package to the model
		model.addAttribute("listPackage", packageList);

		List<EmployeeType> emptypeList = emptypeRepo.findAll();
		// Add the employeeType to the model
		model.addAttribute("listEmptype", emptypeList);

		List<TimeSchedule> timeschList = timeschRepo.findAll();
		model.addAttribute("listTimeSchedule", timeschList);

		// Set Date Calendar to date in present and future
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		// return the response as view
		return "add_appt";
	}

	@PostMapping("/appt/save")
	public String saveAppt(Appointment2 appointment, RedirectAttributes redirectAttribute) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		CorpUser corpUser = corpuserRepo.getById(loggedInMemberId);

		// Set Appointment By Corpuser id when added.
		appointment.setCorpuser(corpUser);

		// Set Status to Booked once appointment is added
		appointment.setStatus("Booked");
		appt2Repo.save(appointment);

		// Email Done By: Lorraine Lee,20023609
		String subject = "Appointment is confirmed!";
		String body = "Thank you Booking With Us!\n" + "Appointment ID: " + appointment.getId() + "\n Corporate ID: "
				+ appointment.getCorporateid() + "\n Employee ID: " + appointment.getEmployeeid() + "\n Date: "
				+ appointment.getDate() + "\n Time: " + appointment.getTimesch().getTime() + "\n Package Booked: "
				+ appointment.getPackages().getPname();
		String to = corpUser.getEmail();
		sendEmail(to, subject, body);
		redirectAttribute.addFlashAttribute("success", "Appointment Booked. See you!");
		return "redirect:/screenadvisory";
	}

	// Email Done By: Lorraine Lee,20023609
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(body);
		System.out.println("Sending");
		javaMailSender.send(msg);
		System.out.println("Sent");
	}

	// Schedule Appointment - For Walk-In Patients
	@GetMapping("/appt2/add")
	public String addAppt2(Model model, Principal principal) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		model.addAttribute("listAppt", new Appointment2());
		CorpUser user = corpuserRepo.getById(loggedInMemberId);
		model.addAttribute("", user);

		List<Package> packageList = packageRepo.findAll();
		// Add the package to the model

		model.addAttribute("listPackage", packageList);

		List<EmployeeType> emptypeList = emptypeRepo.findAll();
		// Add the categories to the model
		model.addAttribute("listEmptype", emptypeList);

		List<TimeSchedule2> timeschList = timeschRepo2.findAll();
		model.addAttribute("listTimeSchedule", timeschList);

		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);

		// return the response as view
		return "add_appt2";
	}

	// Schedule Appointment - For Walk-In Patients
	@PostMapping("/appt2/save") 
	public String saveAppt2(Appointment2 appointment) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		CorpUser corpUser = corpuserRepo.getById(loggedInMemberId);
		appointment.setCorpuser(corpUser);
		appointment.setStatus("Booked");
		appt2Repo.save(appointment);

		// Email Portion Done By: Lorraine Lee,20023609
		String subject = "Appointment is confirmed!";
		String body = "Thank you Booking With Us!\n" + "Appointment ID: " + appointment.getId() + "\n Corporate ID: "
				+ appointment.getCorporateid() + "\n Employee ID: " + appointment.getEmployeeid() + "\n Date: "
				+ appointment.getDate() + "\n Time: " + appointment.getTimesch().getTime() + "\n Package Booked: "
				+ appointment.getPackages().getPname();
		String to = appointment.getEmail();
		sendEmail(to, subject, body);
		return "redirect:/appt";
	}

	@GetMapping("/appt/reschedule/{id}")
	public String rsAppt(@PathVariable("id") String id, Model model, Principal principal) {
		Appointment2 appointment = appt2Repo.getById(id);
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		CorpUser user = corpuserRepo.getById(loggedInMemberId);
		model.addAttribute("", user);
		model.addAttribute("listAppt", appointment);

		List<Package> packageList = packageRepo.findAll();
		// Add the package to the model
		model.addAttribute("listPackage", packageList);
		// return the response as view

		List<EmployeeType> emptypeList = emptypeRepo.findAll();
		model.addAttribute("listEmptype", emptypeList);
		List<TimeSchedule> timeschList = timeschRepo.findAll();
		model.addAttribute("listTimeSchedule", timeschList);
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		// Add the categories to the model

		return "reschedule_appt";
	}

	@PostMapping("/appt/reschedule/{id}")
	public String saveReschedule(@PathVariable("id") String id, Appointment2 appointment,
			RedirectAttributes redirectAttribute) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();
		CorpUser corpUser = corpuserRepo.getById(loggedInMemberId);
		appointment.setCorpuser(corpUser);
		appt2Repo.save(appointment);

		// Email Portion Done By: Lorraine Lee,20023609
		String subject = "Appointment is Rescheduled!";
		String body = "Thank you Booking With Us!\n" + "Appointment ID: " + appointment.getId() + "\n Corporate ID: "
				+ appointment.getCorporateid() + "\n Employee ID: " + appointment.getEmployeeid() + "\n Updated Date: "
				+ appointment.getDate() + "\n Updated time: " + appointment.getTimesch().getTime()
				+ "\n Package Booked: " + appointment.getPackages().getPname();
		String toUser = appointment.getEmail();
		sendEmail(toUser, subject, body);
		redirectAttribute.addFlashAttribute("success", "Appointment Reschedule. See you!");
		return "redirect:/apptuser";
	}

	// Email Portion Done By: Lorraine Lee,20023609
	public void sendEmailCustomer(String toUser, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toUser);
		msg.setSubject(subject);
		msg.setText(body);
		System.out.println("Sending");
		javaMailSender.send(msg);
		System.out.println("Sent");
	}

	// For Counter Staff Reschedule => Redirects to view all appointment list
	@GetMapping("/appt/reschedule2/{id}")
	public String rsAppt2(@PathVariable("id") String id, Model model, Principal principal) {
		Appointment2 appointment = appt2Repo.getById(id);
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();

		CorpUser user = corpuserRepo.getById(loggedInMemberId);
		model.addAttribute("", user);
		model.addAttribute("listAppt", appointment);

		List<Package> packageList = packageRepo.findAll();
		// Add the package to the model
		model.addAttribute("listPackage", packageList);
		// return the response as view

		List<EmployeeType> emptypeList = emptypeRepo.findAll();
		model.addAttribute("listEmptype", emptypeList);
		List<TimeSchedule> timeschList = timeschRepo.findAll();
		model.addAttribute("listTimeSchedule", timeschList);
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		// Add the categories to the model

		return "reschedule_appt2";
	}

	// For User Reschedule => Redirects to their own view appointment list
	@PostMapping("/appt/reschedule2/{id}")
	public String saveReschedule2(@PathVariable("id") String id, Appointment2 appointment,
			RedirectAttributes redirectAttribute) {
		CorpUserDetails loggedInMember = (CorpUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		int loggedInMemberId = loggedInMember.getUser().getId();
		CorpUser corpUser = corpuserRepo.getById(loggedInMemberId);
		appointment.setCorpuser(corpUser);
		appt2Repo.save(appointment);
		String subject = "Appointment is Rescheduled!";
		String body = "Thank you Booking With Us!\n" + "Appointment ID: " + appointment.getId() + "\n Corporate ID: "
				+ appointment.getCorporateid() + "\n Employee Id: " + appointment.getEmployeeid() + " \n Updated Date: "
				+ appointment.getDate() + "\n Updated time: " + appointment.getTimesch().getTime()
				+ "\n Package Booked: " + appointment.getPackages().getPname();
		String toUser = appointment.getEmail();
		sendEmail(toUser, subject, body);
		redirectAttribute.addFlashAttribute("success", "Appointment Reschedule. See you!");
		return "redirect:/appt";
	}

	public void sendEmailCustomer2(String toUser, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toUser);
		msg.setSubject(subject);
		msg.setText(body);
		System.out.println("Sending");
		javaMailSender.send(msg);
		System.out.println("Sent");
	}

	// For CounterStaff to cancel patient appointment
	@GetMapping("/appt/cancel/{id}")
	public String cancelAppt(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttribute) {
		Appointment2 appointment = appt2Repo.getById(id);

		appointment.setStatus("Cancelled");
		model.addAttribute("appointment", appointment);
		appt2Repo.save(appointment);
		redirectAttribute.addFlashAttribute("failed", "Appointment Cancelled.");
		return "redirect:/appt";
	}

	// For User to Cancel their appointment
	@GetMapping("/appt/cancel2/{id}") // cancel for user
	public String cancelAppt2(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttribute) {
		Appointment2 appointment = appt2Repo.getById(id);

		appointment.setStatus("Cancelled");
		model.addAttribute("appointment", appointment);
		appt2Repo.save(appointment);
		redirectAttribute.addFlashAttribute("failed", "Appointment Cancelled.");
		return "redirect:/apptuser";
	}

	// For Appointment Completed- Done by CounterStaff
	@GetMapping("/appt/completed/{id}")
	public String completedAppt(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttribute) {
		Appointment2 appointment = appt2Repo.getById(id);

		appointment.setStatus("Completed");
		model.addAttribute("appointment", appointment);
		appt2Repo.save(appointment);
		redirectAttribute.addFlashAttribute("success", "Appointment Completed!");
		return "redirect:/appt";
	}

	@GetMapping("/screenadvisory")
	public String screenadvisory() {
		return "screenadvisory";
	}

	@GetMapping("/appt/{id}")
	public String viewSingleAppt(@PathVariable("id") String id, Model model) {
		Appointment2 appt = appt2Repo.getById(id);
		model.addAttribute("appt", appt);
		return "view_single_appt";

	}
}
