package assemble.eolmangyo.market.application.service;


import assemble.eolmangyo.market.api.response.MarketListResponseDto;
import assemble.eolmangyo.market.domain.dto.MarketInfoDto;
import assemble.eolmangyo.market.infrastructure.entity.MarketEntity;
import assemble.eolmangyo.market.infrastructure.jpa.MarketJapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class MarketServiceImpl {

	private final MarketJapRepository marketJapRepository;

	/**
	 * MarketService
	 * 1. 시장 리스트 조회
	 */

	// 1. 시장 리스트 조회
	public MarketListResponseDto getMarketList() {
		// 시장 리스트 조회
		List<MarketEntity> allMarket = marketJapRepository.findAll();
		// responseDto
		List<MarketInfoDto> content = new ArrayList<>();
		for (MarketEntity marketEntity : allMarket) {
			MarketInfoDto marketInfoDto = new MarketInfoDto(marketEntity.getId(), marketEntity.getMarketName());
			content.add(marketInfoDto);
		}
		// response
		return new MarketListResponseDto(content);
	}
}
