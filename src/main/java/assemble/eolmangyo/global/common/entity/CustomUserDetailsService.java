package assemble.eolmangyo.global.common.entity;


import assemble.eolmangyo.user.application.repository.UserRepository;
import assemble.eolmangyo.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;


	// uuid로 Users 조회 후, CustomUserDetails로 변환하여 반환
	@Override
	public UserDetails loadUserByUsername(String userUuidString) throws UsernameNotFoundException {
		Users user = (Users) userRepository.findByUserUuid(UUID.fromString(userUuidString))
			.orElseThrow(() -> new UsernameNotFoundException(userUuidString));
		return new CustomUserDetails(user);
	}

}
