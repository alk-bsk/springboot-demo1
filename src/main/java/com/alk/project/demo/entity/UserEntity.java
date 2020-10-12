package com.alk.project.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class UserEntity implements UserDetails,Serializable{


	private static final long serialVersionUID = 3604550550888089092L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true,nullable = false)
	private String username;
	
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="user_roll",joinColumns = @JoinColumn(name="id",referencedColumnName = "id"))
	private List<String> role;
	

	public UserEntity(String username, String password, ArrayList<String> role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}


	public void setRole(List<String> role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> authority=new ArrayList<SimpleGrantedAuthority>();
		for(String r:role) {
			authority.add(new SimpleGrantedAuthority("ROLE_"+r));
		}
		return authority;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}


	
	
}
