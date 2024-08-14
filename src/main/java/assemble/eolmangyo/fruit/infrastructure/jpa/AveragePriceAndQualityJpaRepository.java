package assemble.eolmangyo.fruit.infrastructure.jpa;


import assemble.eolmangyo.fruit.infrastructure.entity.AveragePriceAndQualityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface AveragePriceAndQualityJpaRepository extends JpaRepository<AveragePriceAndQualityEntity, Long> {

	/**
	 * AveragePriceAndQualityJpaRepository
	 * 1. 시장 과일로 AveragePriceAndQualityEntity 조회
	 * 2. 해당 날짜에 등록된 리뷰 개수 조회
	 */

	// 1. 시장 과일로 AveragePriceAndQualityEntity 조회
	Optional<AveragePriceAndQualityEntity> findByMarketFruitIdAndDate(Long marketFruitId, LocalDate date);

	// 2. 해당 날짜에 등록된 리뷰 개수 조회
	Integer countByDate(LocalDate date);
}
