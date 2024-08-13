package assemble.eolmangyo.user.infrastructure.jpa;


import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

	/**
	 * UserJpaRepository
	 * 1. 로그인 id로 존재 여부 조회
	 * 2. userUuid로 UserEntity 조회
	 * 3. 로그인 id로 UserEntity 조회
	 */

	// 1. 로그인 id로 존재 여부 조회
	boolean existsByLoginId(String loginId);

	// 2. userUuid로 UserEntity 조회
	Optional<UserEntity> findByUserUuid(UUID userUuid);

	// 3. 로그인 id로 UserEntity 조회
	Optional<UserEntity> findByLoginId(String loginId);

}
