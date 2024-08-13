package assemble.eolmangyo.user.application.repository;


import assemble.eolmangyo.user.domain.Users;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository {

	/**
	 * UserRepository
	 * 1. 유저 저장
	 * 2. 로그인 id로 존재 여부 조회
	 * 3. userUuid로 Users 조회
	 */

	// 1. 유저 저장
	Users save(Users users);

	// 2. 로그인 id로 존재 여부 조회
	boolean existsByLoginId(String loginId);

	// 3. userUuid로 Users 조회
	Optional<Users> findByUserUuid(UUID userUuid);

	// 4. 로그인 id로 Users 조회
	Optional<Users> findByLoginId(String loginId);

}
