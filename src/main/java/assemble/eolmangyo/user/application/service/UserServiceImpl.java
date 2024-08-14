package assemble.eolmangyo.user.application.service;


import assemble.eolmangyo.global.common.exception.BaseException;
import assemble.eolmangyo.global.common.response.BaseResponseStatus;
import assemble.eolmangyo.user.application.repository.UserRepository;
import assemble.eolmangyo.user.domain.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	// repository
	private final UserRepository userRepository;

	/**
	 * UserService
	 * 1. Uuid로 유저 조회
	 */

	// 1. Uuid로 유저 조회
	public Users findUserByUuid(UUID uuid) {
		return userRepository.findByUserUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
	}

}
