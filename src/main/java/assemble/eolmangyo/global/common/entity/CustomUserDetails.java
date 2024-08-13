package assemble.eolmangyo.global.common.entity;


import assemble.eolmangyo.user.domain.Users;
import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;


public class CustomUserDetails implements UserDetails {

	private UUID userUuid;
	private String password;


	public CustomUserDetails() {
	}


	public CustomUserDetails(Users user) {
		this.userUuid = user.getUserUuid();
		this.password = user.getLoginPassword();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return this.password;
	}


	@Override
	public String getUsername() {
		// token에 userUUID를 넘겨주기 위해, userUUID를 return
		return this.userUuid.toString();
	}


	// 유저의 uuid를 return
	public UUID getUserUuid() {
		return this.userUuid;
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
