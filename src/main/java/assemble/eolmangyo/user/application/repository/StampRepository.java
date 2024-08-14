package assemble.eolmangyo.user.application.repository;


import assemble.eolmangyo.user.domain.Stamp;
import assemble.eolmangyo.user.domain.Users;

import java.util.Optional;


public interface StampRepository {

	/**
	 * StampRepository
	 * 1. 스탬프 저장
	 * 2. 유저의 마지막 스탬프 조회
	 */

	// 스탬프 저장
	Stamp save(Stamp stamp);

	// 유저의 마지막 스탬프 조회
	Optional<Stamp> findLastStamp(Users user);

}
