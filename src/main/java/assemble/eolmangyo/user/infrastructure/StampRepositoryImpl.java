package assemble.eolmangyo.user.infrastructure;


import assemble.eolmangyo.user.application.repository.StampRepository;
import assemble.eolmangyo.user.domain.Stamp;
import assemble.eolmangyo.user.domain.Users;
import assemble.eolmangyo.user.infrastructure.entity.StampEntity;
import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import assemble.eolmangyo.user.infrastructure.jpa.StampJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class StampRepositoryImpl implements StampRepository {

	private final StampJpaRepository stampJpaRepository;


	/**
	 * StampRepository
	 * 1. 스탬프 저장
	 * 2. 유저의 마지막 스탬프 조회
	 */

	@Override
	public Stamp save(Stamp stamp) {
		return stampJpaRepository.save(StampEntity.fromDomain(stamp)).toDomain();
	}

	// 유저의 마지막 스탬프 조회
	@Override
	public Optional<Stamp> findLastStamp(Users user) {
		return stampJpaRepository.findFirstByUserOrderByCreatedAtDesc(UserEntity.fromDomain(user)).map(StampEntity::toDomain);
	}

}
