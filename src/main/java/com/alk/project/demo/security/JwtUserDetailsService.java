package com.alk.project.demo.security;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alk.project.demo.dao.UserDao;
import com.alk.project.demo.entity.UserEntity;
import com.alk.project.demo.model.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//hard coded username password
//		if("test".equals(username)) {
//			return new User("test", "$2y$12$3CyofpHiprm7PaYL83pgnOJb0cnPoktpqt3cZFm.kh4gbQ/dD.56W", new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User not found !");
//		}
		
		UserEntity user=userDao.findByUsername(username);
		if(user ==null) {
			throw new UsernameNotFoundException("User not found !");
		}
		return user;
		
	}
	
	public UserEntity save(UserModel user) {
		UserEntity finalUser=new UserEntity();
		finalUser.setUsername(user.getUsername());
		finalUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		finalUser.setRole(user.getRole());
		return userDao.save(finalUser);
	}
	
	private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {

	    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

	    // Build user's authorities
	    for (String userRole : userRoles) {
	        setAuths.add(new SimpleGrantedAuthority(userRole));
	    }

	    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
	    return Result;
	}
	
//	private Collection<? extends GrantedAuthority> getAuthorities(
//			  Collection<Role> roles) {
//			    List<GrantedAuthority> authorities
//			      = new ArrayList<>();
//			    for (Role role: roles) {
//			        authorities.add(new SimpleGrantedAuthority(role.getName()));
//			        role.getPrivileges().stream()
//			         .map(p -> new SimpleGrantedAuthority(p.getName()))
//			         .forEach(authorities::add);
//			    }
//			    
//			    return authorities;
//			}
	

}
