package assemble.eolmangyo.fruit.infrastructure.jpa;


import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface MarketFruitJpaRepository extends JpaRepository<MarketFruitEntity, Long> {

	/**
	 * MarketFruitJpaRepository
	 * 1. id로 MarketFruitEntity 조회
	 * 2. 이름으로 랜덤 조회
	 */

	// 1. id로 MarketFruitEntity 조회
	Optional<MarketFruitEntity> findById(Long id);

	// 2. 이름으로 랜덤 조회
	@Query(value =
		"SELECT mf.id as market_fruit_id, mf.*, f.id as fruit_id, f.* " +
		"FROM market_fruit mf " +
		"JOIN fruit f ON f.fruit_name = :randomSeasonFruitName " +
		"WHERE mf.fruit_id = f.id " +
		"ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Optional<MarketFruitEntity> findByRandomFruit(String randomSeasonFruitName);

}
