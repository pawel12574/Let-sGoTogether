package com.packt.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.UserDaoInterface;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserDaoInterface userDao;
	
	
	 public UserDetails loadUserByUsername(String username) {
		
		com.packt.web.bean.User user = userDao.getUser(username); 
				
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = user.isAccountNonLocked();
		
		
		return  new User(
  /*email*/  user.getUsername(), 
             user.getPassword(), 
             enabled, 
             accountNonExpired, 
             credentialsNonExpired, 
             accountNonLocked,
             getAuthorities(user.getRole().getRole())
     );
 }
	 public Collection getAuthorities(Integer role) {
		 
		         List authList = getGrantedAuthorities(getRoles(role));
		         return authList;
		 
		    }
		 
	 public List<String> getRoles(Integer role) {
			List<String> roles = new ArrayList<String>();
			
			if (role.intValue() == 1) {
				roles.add("ROLE_USER");
				roles.add("ROLE_ADMIN");
				
			} else if (role.intValue() == 2) {
				roles.add("ROLE_USER");
			}
			
			return roles;
		}
		  
		public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String role : roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			return authorities;
		}
	 
	  
	   
	 
	 
		 }
