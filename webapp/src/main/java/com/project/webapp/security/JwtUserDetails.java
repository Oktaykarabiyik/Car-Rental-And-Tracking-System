package com.project.webapp.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.webapp.entities.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JwtUserDetails implements UserDetails, Serializable{//authentication için bu userı kullanacak
	//bu classın servisi->userdetailsserviceimp
	public Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private JwtUserDetails(Long id,String username,String password, Collection<? extends GrantedAuthority>authorities) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.authorities=authorities;}
	
	public static JwtUserDetails create(User user) {
		List<GrantedAuthority>authoritiesList=new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));
		return new JwtUserDetails(user.getId(),user.getUserName(),user.getPassword(),authoritiesList);
	}
//user geldiğinde ondan bir userdetails objesi dönüyoruz
	//
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
	//userdetails=>spring security nin sağladığı bir arayüz.
	//userın içinde başka bilgilerde olabilir. bu userdetails de sadece username ve password bulunuyor
//authonticationda kullanacağımız user bu.
	//gerekli metodları implement ettik


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getUsername() {
		return username;
	}

	public long getId() {
		return id;
	}

}
