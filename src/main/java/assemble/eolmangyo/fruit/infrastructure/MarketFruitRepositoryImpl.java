package assemble.eolmangyo.fruit.infrastructure;


import assemble.eolmangyo.fruit.application.repository.MarketFruitRepository;
import assemble.eolmangyo.fruit.domain.MarketFruit;
import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import assemble.eolmangyo.fruit.infrastructure.jpa.MarketFruitJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MarketFruitRepositoryImpl implements MarketFruitRepository {

	// jpa
	private final MarketFruitJpaRepository marketFruitJpaRepository;

	/**
	 * MarketFruitRepository
	 * 1. id로 시장과일 조회
	 */

	// 1. id로 시장과일 조회
	@Override
	public Optional<MarketFruit> findById(Long id) {
		return marketFruitJpaRepository.findById(id).map(MarketFruitEntity::toDomain);
	}

}
