package com.mall.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class SysUser implements UserDetails { // 1

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private String password;

	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER) // 2
	private List<SysRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //3
		List<GrantedAuthority> auths = new ArrayList<>();
		List<SysRole> roles = this.getRoles();
		for (SysRole role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
