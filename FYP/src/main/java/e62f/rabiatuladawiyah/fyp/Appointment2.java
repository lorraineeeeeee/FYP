/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-Jul-20 5:10:35 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 20021900
 *
 */
@Entity
public class Appointment2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence_emp")
	@GenericGenerator(name = "hibernate_sequence_emp", strategy = "e62f.rabiatuladawiyah.fyp.AppointmentIdGenerator", parameters = {
			@Parameter(name = AppointmentIdGenerator.VALUE_PREFIX_PARAMETER, value = "APPT"),
			@Parameter(name = AppointmentIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
private String id; 
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate date; 
private LocalTime time; 
private String nric; 
private String corporateid; 
private String employeeid;
private String location; 
private String status; 
private String doctor; 
private String name;
private String email;
private String paid; 

@ManyToOne
@JoinColumn(name = "corpuser_id")
private CorpUser corpuser;

@ManyToOne
@JoinColumn(name="package_id",nullable=false)
private Package packages;

@ManyToOne
@JoinColumn(name="emptype_id",nullable=false)
private EmployeeType emptype;

@ManyToOne
@JoinColumn(name="timeschedule_id",nullable=false)
private TimeSchedule timesch;


@OneToMany(mappedBy="appointment")
private Set<TestResults> testresults;


public CorpUser getCorpuser() {
	return corpuser;
}

public void setCorpuser(CorpUser corpuser) {
	this.corpuser = corpuser;
}


public Package getPackages() {
	return packages;
}


public void setPackages(Package packages) {
	this.packages = packages;
}


public EmployeeType getEmptype() {
	return emptype;
}


public void setEmptype(EmployeeType emptype) {
	this.emptype = emptype;
}


public TimeSchedule getTimesch() {
	return timesch;
}


public void setTimesch(TimeSchedule timesch) {
	this.timesch = timesch;
}


public Set<TestResults> getTestresults() {
	return testresults;
}


public void setTestresults(Set<TestResults> testresults) {
	this.testresults = testresults;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public LocalDate getDate() {
	return date;
}

public void setDate(LocalDate date) {
	this.date = date;
}

public LocalTime getTime() {
	return time;
}

public void setTime(LocalTime time) {
	this.time = time;
}

public String getNric() {
	return nric;
}

public void setNric(String nric) {
	this.nric = nric;
}

public String getCorporateid() {
	return corporateid;
}

public void setCorporateid(String corporateid) {
	this.corporateid = corporateid;
}

public String getEmployeeid() {
	return employeeid;
}

public void setEmployeeid(String employeeid) {
	this.employeeid = employeeid;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getDoctor() {
	return doctor;
}

public void setDoctor(String doctor) {
	this.doctor = doctor;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}

public String getPaid() {
	return paid;
}


public void setPaid(String paid) {
	this.paid = paid;
}

}
