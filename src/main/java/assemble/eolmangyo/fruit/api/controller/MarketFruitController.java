package assemble.eolmangyo.fruit.api.controller;


import assemble.eolmangyo.fruit.application.service.MarketFruitServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/fruit")
@RequiredArgsConstructor
public class MarketFruitController {

	// service
	private final MarketFruitServiceImpl marketFruitService;

	/**
	 * MarketFruitController
	 * 1. 시장 과일의 최근 10일 평균 가격 리스트 조회
	 */

	// 1. 시장 과일의 최근 10일 평균 가격 리스트 조회



}
