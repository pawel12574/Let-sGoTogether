package com.packt.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.packt.web.Dao.PasswordTokenDaoInterface;
import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.PasswordToken;
import com.packt.web.bean.Role;
import com.packt.web.bean.Server;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;
import com.packt.web.dto.EmailDto;
import com.packt.web.dto.PasswordDto;
import com.packt.web.dto.TripDTO;
import com.packt.web.dto.UserDto;
import com.packt.web.service.DateService;
import com.packt.web.service.Email;
import com.packt.web.service.UserServiceInterface;

@Controller
public class UserController {

	@Autowired
	UserServiceInterface userService;
	@Autowired
	ServletContext servletContext;
	
	
	
	//viewResolver
	@RequestMapping(value="/user/view")
	public String userPage(){
		return "userPage";
	}
	
	@RequestMapping(value="/user/view/password")
	public String userPassword(){
		return "userPassword";
	}
	
	@RequestMapping(value="/user/view/avatar")
	public String avatar(){
		return "userAvatar";
	}
	
	@RequestMapping(value="/user/view/data")
	public String userData(){
		return "userData";
	}
	
	@RequestMapping(value="/user/changepass")
	public String changePasspage(){
		return "changePassword";
	}
	
	@RequestMapping(value="/user/forgotPassword")
	public String forgotPasspage(){
		return "forgotPassword";
	}
	
	@RequestMapping(value="/user/userTraveler")
	public String userTraveler(){
		return "userTraveler";
	}
	
	@RequestMapping(value="/user/emailIsExist",method=RequestMethod.POST)
	public @ResponseBody User checkEmail(@RequestBody EmailDto email){
		
		User user=new User();
		if(userService.checkEmailExist(email.getUsername())!=null){
		   user.setUsername(userService.checkEmailExist(email.getUsername()));
		   return user;
		}
		else 
		return null;
	}
	
	@RequestMapping(value="/user/update",method=RequestMethod.POST)
	public ResponseEntity<String>  updateUser(@RequestBody User user){
		
		User u = userService.getLoggedUser();
		if(user.getName()!="" && user.getSurname()!="" && user.getPhoneNumber()!=""){
			u.setName(user.getName());
			u.setSurname(user.getSurname());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setBirthDate(user.getBirthDate());
			userService.merge(u);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/create", method = RequestMethod.POST)
	public @ResponseBody UserDto createUser(@RequestBody UserDto userDto){
		if (userDto != null) {
            User user = new User();
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setName(userDto.getName());
			user.setSurname(userDto.getSurname());
			user.setBirthDate(userDto.getBirthDate());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setAccountNonLocked(false);
			
			String token = UUID.randomUUID().toString(); //create token
			
			
			
			String path = servletContext.getRealPath("/");
			
			ApplicationContext context = new ClassPathXmlApplicationContext("email.xml");
			Email emailService = (Email) context.getBean("Email");
			Server server = (Server) context.getBean("serverProperty");
			
			String url = server.getServerPath()+"/user/activate/"+token+"";
			emailService.sendMail("eliasz.paw@gmail.com",
					               user.getUsername(),
		    		              "Let'sGoTogether",
		    		              "Kliknij w link, aby aktywowaæ konto "+url+"");

			Role role = new Role();
			PasswordToken passToken = new PasswordToken();
			passToken.setToken(token);
			passToken.setUser(user);
			role.setRole(2);
			role.setUser(user);
			user.setRole(role);
			user.setToken(passToken);
			
			if(!userDto.getUsername().equals(userService.checkEmailExist(userDto.getUsername()))){
			  userService.createUser(user);
			}

			return userDto;

		} else
			return null;
	}
	
	@RequestMapping(value="/user/activate/{token}", method=RequestMethod.GET)
	public String activate(@PathVariable String token){
		
		PasswordToken passToken = userService.getToken(token);
		
		try{   
		  User user = passToken.getUser();
		  user.setAccountNonLocked(true);
	      user.setToken(null);
		  userService.merge(user);
		}catch(Exception e){
			System.out.println("no result for token");
		}
		
		userService.deleteToken(passToken);
		
		return "redirect:/#/login";

	}
	
	@RequestMapping(value="user/changePassword", method=RequestMethod.GET)
	public String changePassword(@RequestParam int id, @RequestParam String token) throws Exception {
		
		
	    PasswordToken passToken = userService.getToken(token);
	    userService.deleteToken(passToken);
	    
	    User user = passToken.getUser();
	    if(user.getId()== id){
		  Authentication auth = new UsernamePasswordAuthenticationToken(
				      user, null, Arrays.asList(
				      new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		  SecurityContextHolder.getContext().setAuthentication(auth);
		
		  return "redirect:/#/changePassword";
	    }else
	      return "redirect:/#/";
			
	}
	
	@RequestMapping(value="user/changePassword", method=RequestMethod.POST)//when forgot password
	public String changePasswordForm(@RequestBody PasswordDto passwordDto)  {
		
		User user = userService.getLoggedUserFromContext();

		user.setPassword(passwordDto.getPassword());
		userService.changePassword(user);
		
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		return "redirect:/#/login";
	}
	
	@RequestMapping(value="user/changePasswordv2", method=RequestMethod.POST)//when user want change password
	public ResponseEntity<String> changePasswordv2(@RequestBody PasswordDto passwordDto)  {
			
		 User user = userService.getLoggedUser();
			if(userService.matchPassword(passwordDto.getPassword(), user)){
				userService.changePassword(passwordDto.getPasswordre() ,user);
			    SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			    return new ResponseEntity<>(HttpStatus.OK);
			}
		 return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
    @RequestMapping(value="/user/add/avatar", method=RequestMethod.POST)
	public  ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile fileUpload)  {
		 
		User user= userService.getLoggedUser();
		try {
			user.setAvatar(fileUpload.getBytes());
			if(fileUpload.getSize()>15360){
				return null;
			}else {
			    userService.mergeUser(user);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(value="/user/get/avatar", method=RequestMethod.GET)//return User an then user.getAvatar
	public @ResponseBody User getAvatar()  {
		 
		User user= userService.getLoggedUser();
		if(user!=null)
		   return user;
		else 
		   return null;

	}
	
	@RequestMapping(value="/user/get/{id}", method=RequestMethod.GET)//return User
	public @ResponseBody User getUser(@PathVariable int id)  {
		 
		User user= userService.getUser(id);
		if(user!=null)
		   return user;
		else 
		   return null;

	}
	
	@RequestMapping(value="/user/delete/avatar", method=RequestMethod.GET)
	public ResponseEntity<String> deleteAvatar()  {
		 
		User user= userService.getLoggedUser();
		if(user!=null){
		  user.setAvatar(null);
		  userService.merge(user);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@RequestMapping(value="/users/all", method=RequestMethod.GET)
	public @ResponseBody List<User> getAllUser(){
		
		return userService.getAllUsers();
		
	}
	
	@RequestMapping(value="/users/lock/{userId}", method=RequestMethod.GET)
	public  ResponseEntity<String> setUserRole(@PathVariable int userId){
		
		User user = userService.getUser(userId);
		user.setAccountNonLocked(!user.isAccountNonLocked());
		userService.merge(user);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
    @RequestMapping(value="/users/changePrivileges/{userId}", method=RequestMethod.GET)
    public  ResponseEntity<String> changePrivileges(@PathVariable int userId){
	
	   User user = userService.getUser(userId);
	   if(user.getRole().getRole()<2){
		   user.getRole().setRole(2);
		   userService.merge(user);
		   }
	   else{
		   user.getRole().setRole(1);
		   userService.merge(user);
	   }	   
	  return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @RequestMapping(value="/users/remove/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable int userId){
		
		userService.removeUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}	

