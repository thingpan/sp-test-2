package com.sp.file.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoVO  implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uiNum;
	private String uiId;
	private String uiPwd;
	private String uiName;
	private String uiMobile;
	private String uiEmail;
	private String uiBirth;
	private String uiRoles;
	private String credat;
	private String lmodat;
	private String active;
	private Collection< ? extends GrantedAuthority> authorities;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return uiPwd;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return uiId;
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

	

	
}
