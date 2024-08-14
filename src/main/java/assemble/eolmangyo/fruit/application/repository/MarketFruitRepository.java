package assemble.eolmangyo.fruit.application.repository;


import assemble.eolmangyo.fruit.domain.MarketFruit;

import java.util.Optional;


public interface MarketFruitRepository {

	/**
	 * MarketFruitRepository
	 * 1. id로 시장과일 조회
	 */

	// 1. id로 시장과일 조회
	Optional<MarketFruit> findById(Long id);


}
