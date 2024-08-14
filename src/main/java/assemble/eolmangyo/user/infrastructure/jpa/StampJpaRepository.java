package assemble.eolmangyo.user.infrastructure.jpa;


import assemble.eolmangyo.user.infrastructure.entity.StampEntity;
import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StampJpaRepository extends JpaRepository<StampEntity, Long> {

	/**
	 * StampJpaRepository
	 * 1. 유저의 마지막 스탬프 조회
	 */

	// 1. 유저의 마지막 스탬프 조회
	Optional<StampEntity> findFirstByUserOrderByCreatedAtDesc(UserEntity user);

}
