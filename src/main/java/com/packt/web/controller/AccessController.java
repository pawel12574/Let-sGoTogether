package com.packt.web.controller;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.web.bean.Server;
import com.packt.web.bean.User;
import com.packt.web.service.Email;
import com.packt.web.service.UserServiceInterface;

@Controller
@RequestMapping
public class AccessController {
	
	@Autowired
	UserServiceInterface userService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	ServletContext servletContext;
	
	
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logoutPage () {
	   
	    return "redirect:/#/";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure() {
		
		return "redirect:/#/loginFailure";
	}
	
	@RequestMapping("/loginFailure")
	public String loginFailurePage() {
		
		return "loginFailure";
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess() {
		return "tripFinder";
	}
	
	@RequestMapping(value = "/registration", method=RequestMethod.GET)
 	public String registration() {
		return "registration";
	}
	
	//Rest
	@RequestMapping(value="/resetPassword/{email:.+}", method=RequestMethod.GET)
	public  ResponseEntity<String> resetPassword(@PathVariable String email) throws Exception {
		
		User user = userService.getUser(email);
		if (user == null) {
	        throw new NullPointerException("Brak takiego adresu email w bazie");
	    }
		String token = UUID.randomUUID().toString(); //create token
		userService.savePasswordToken(user, token);  // save user,token
		
		
		String path = servletContext.getRealPath("/");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("email.xml");
		Email emailService = (Email) context.getBean("Email");
		Server server = (Server) context.getBean("serverProperty");
		
		String url = server.getServerPath()+"/user/changePassword?id="+user.getId()+"&token="+token+"";
		emailService.sendMail("eliasz.paw@gmail.com",
				               email,
	    		              "Let'sGoTogether",
	    		              "Kliknij w link aby zresetowaæ has³o "+url+"");
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		}
}


//SecureRandom rand = new SecureRandom();
		//String newPassword=new BigInteger((4 + rand.nextInt(3)) * 8, rand).toString(36);
		//user.setPassword(passwordEncoder.encode(newPassword));