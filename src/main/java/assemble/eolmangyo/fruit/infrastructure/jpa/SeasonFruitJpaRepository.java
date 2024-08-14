package assemble.eolmangyo.fruit.infrastructure.jpa;


import assemble.eolmangyo.fruit.infrastructure.entity.SeasonFruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SeasonFruitJpaRepository extends JpaRepository<SeasonFruitEntity, Long> {

	/**
	 * SeasonFruitJpaRepository
	 * 1. 랜덤 계절 과일 조회
	 */

	// 1. 랜덤 계절 과일 조회
	@Query(value = "SELECT season_fruit.fruit_name FROM season_fruit ORDER BY RAND() LIMIT 1", nativeQuery = true)
	String findRandomSeasonFruit();
}
