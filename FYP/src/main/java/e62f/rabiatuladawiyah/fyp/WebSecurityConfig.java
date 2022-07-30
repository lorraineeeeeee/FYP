/**
 * 
 * I declare that this code was written by me, Rabiatul Adawiyah. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Rabiatul Adawiyah
 * Student ID: 20021900
 * Class: E62F
 * Date created: 2022-May-06 2:23:57 pm 
 * 
 */

package e62f.rabiatuladawiyah.fyp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 20021900
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean

	public CorpUserDetailsService CorpUserDetailsService() {

		return new CorpUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(CorpUserDetailsService());

		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				// Appointment
				.antMatchers("/appt", "/appt/add", "/appt/save", "/appt2/add", "/appt2/save",
						"/appt/reschedule/*", "/appt/cancel/*", "/appt/cancel2/*", "/screenadvisory", "/appt/*")
				.hasAnyRole("ADMIN", "USER", "COUNTERSTAFF")//Appt for CS,ADMIN,USER
				.antMatchers("/appt/edit/*", "/appt/completed/*")
				.hasAnyRole("ADMIN", "COUNTERSTAFF")//Appt for ADMIN,CS
				.antMatchers("/appt/delete/*").hasRole("ADMIN")//Appt for ADMIN
				.antMatchers("/apptuser").hasRole("USER")
				
				//Manager
				.antMatchers("/managers","/managers/add","/managers/save","/managers/edit/*","/managers/delete/*").hasRole("ADMIN")
				
				//Question
				.antMatchers("/questions","/questions/add","/questions/save","/questions/*","/questions/edit/*","/questions/delete/*").hasRole("ADMIN")
				
				//Survey
				.antMatchers("/response","/response/*").hasAnyRole("ADMIN","COUNTERSTAFF","USER")				
				.antMatchers("/survey/start","/survey/save").hasAnyRole("ADMIN","COUNTERSTAFF","USER")		
				.antMatchers("/survey2/start","/survey2/save").hasAnyRole("ADMIN","COUNTERSTAFF")				
				
				//Patient
				.antMatchers("/patients","/patients/add","/patients/save","/patients/edit/*","/patients/delete/*","/WIpatients","/WIpatients/add","/WIpatients/save").hasAnyRole("ADMIN","COUNTERSTAFF")	
				
				//TestResults
				.antMatchers("/testresults/appt/*").hasAnyRole("USER","COUNTERSTAFF","ADMIN")
	            .antMatchers("/testresults/add/*","/testresults/save","/testresults/edit/*","/testresults/delete/*").hasRole("COUNTERSTAFF")
				//Company
				.antMatchers("/company","/company/*").hasAnyRole("ADMIN","COUNTERSTAFF","HR")
				.antMatchers("/company/add","/company/save","/company/edit/*","/company/delete/*").hasRole("ADMIN")
				
				//Employee
				.antMatchers("/employee").hasAnyRole("ADMIN","COUNTERSTAFF","HR")
				.antMatchers("/employee/add","/employee/save","/employee/edit/*","/employee/delete/*").hasAnyRole("ADMIN","HR")

				//Counter Staff
				.antMatchers("/cstaff/add","/cstaff/save","/cstaff/edit/*","/cstaff/delete/*").hasRole("ADMIN")
				.antMatchers("/cstaff").hasAnyRole("ADMIN","COUNTERSTAFF")
				
				//Package
				.antMatchers("/packages/add", "/packages/edit/*", "/packages/save", "/packages/delete/*")
				.hasRole("ADMIN")
				
				//Package items
				.antMatchers("/items/add", "/items/edit/*", "/items/save", "/items/delete/*").hasRole("ADMIN")
			    
				//Items in package
				.antMatchers("/Pitems/add/*","/Pitems/save","/Pitems/edit/*","/Pitems/delete/*").hasRole("ADMIN")
			   
				//Report 
				.antMatchers("/allInfo","/information","/information2","information3","patientsReport/Info1","/patientsReport/Info2","/patients/ReportInfo").hasRole("ADMIN")

				//CorpUser
				.antMatchers("/users","users/add/hr","/hr/save","/users/add/cs","/cs/save","/users/delete/*").hasRole("ADMIN")
				.antMatchers("/users/add","/users/save").hasRole("ADMIN")
				.antMatchers("/users/edit/*").hasAnyRole("USER","ADMIN","COUNTERSTAFF","HR")
				
				// Anyone can access
				.antMatchers("/", "/bootstrap/*/*", "/packages", "/aboutus", "/contactus", "/home", "/items",
						"/users/add", "/users/save","/Pitems/package/*")
				.permitAll().antMatchers("/images/*").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().defaultSuccessUrl("/home").and().logout().logoutUrl("/logout")
				.permitAll().logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/403");

	}
}
