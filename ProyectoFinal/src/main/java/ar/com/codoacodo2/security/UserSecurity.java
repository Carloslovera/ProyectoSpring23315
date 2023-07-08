package ar.com.codoacodo2.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.codoacodo2.domain.User;

public class UserSecurity implements UserDetails {
	
	private User user;
	public UserSecurity(User userFromDB) {
		this.user = userFromDB;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return	this.user.getRoles()
			.stream()
			.map(u -> u.getRol())
			.map(SimpleGrantedAuthority::new)
			.toList();
		 
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
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
