package assemble.eolmangyo.user.infrastructure;


import assemble.eolmangyo.user.application.repository.UserRepository;
import assemble.eolmangyo.user.domain.Users;
import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import assemble.eolmangyo.user.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	// jpa
	private final UserJpaRepository userJpaRepository;

	/**
	 * UserRepository
	 * 1. 유저 저장
	 * 2. 로그인 id로 존재 여부 조회
	 * 3. userUuid로 Users 조회
	 */

	// 1. 유저 저장
	@Override
	public Users save(Users users) {
		return userJpaRepository.save(UserEntity.fromDomain(users)).toDomain();
	}

	// 2. 로그인 id로 존재 여부 조회
	@Override
	public boolean existsByLoginId(String loginId) {
		return userJpaRepository.existsByLoginId(loginId);
	}

	// 3. userUuid로 Users 조회
	@Override
	public Optional<Users> findByUserUuid(UUID userUuid) {
		return userJpaRepository.findByUserUuid(userUuid).map(UserEntity::toDomain);
	}


	@Override
	public Optional<Users> findByLoginId(String loginId) {
		return userJpaRepository.findByLoginId(loginId).map(UserEntity::toDomain);
	}

}
