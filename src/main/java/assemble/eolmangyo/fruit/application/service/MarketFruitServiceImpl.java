package assemble.eolmangyo.fruit.application.service;


import assemble.eolmangyo.fruit.application.repository.MarketFruitRepository;
import assemble.eolmangyo.fruit.domain.MarketFruit;
import assemble.eolmangyo.fruit.infrastructure.jpa.MarketFruitJpaRepository;
import assemble.eolmangyo.global.common.exception.BaseException;
import assemble.eolmangyo.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MarketFruitServiceImpl {

	// repository
	private final MarketFruitRepository marketFruitRepository;
	private final MarketFruitJpaRepository marketFruitJpaRepository;


	/**
	 * MarketFruitServiceImpl
	 * 1. id로 시장과일 조회
	 */

	// 1. id로 시장과일 조회
	public MarketFruit findById(Long id) {
		return marketFruitRepository.findById(id)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MARKET_FRUIT));
	}

}
