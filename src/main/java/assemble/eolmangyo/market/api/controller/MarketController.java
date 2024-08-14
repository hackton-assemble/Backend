package assemble.eolmangyo.market.api.controller;


import assemble.eolmangyo.global.common.response.BaseResponse;
import assemble.eolmangyo.market.api.response.MarketListResponseDto;
import assemble.eolmangyo.market.application.service.MarketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/market")
@RequiredArgsConstructor
public class MarketController {

	// service
	private final MarketServiceImpl marketService;
	// util
	private final ModelMapper modelMapper;

	/**
	 * MarketController
	 * 1. 시장 리스트 조회
	 */

	// 1. 시장 리스트 조회
	@GetMapping("/list")
	@Operation(summary = "시장 리스트 조회", description = "시장 리스트 조회", tags = { "Market" })
	public BaseResponse<MarketListResponseDto> getMarketList() {
		MarketListResponseDto responseDto = marketService.getMarketList();
		return new BaseResponse<>(responseDto);
	}
}
