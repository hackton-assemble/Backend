package assemble.eolmangyo.fruit.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AveragePriceAndQuality {

	// 평균 가격과 품질 id
	private Long id;

	// 시장 과일
	private MarketFruit marketFruit;

	// 평균 가격
	private Integer averagePrice;

	// 평균 품질
	private Float averageQuality;

	// 날짜
	private LocalDate date;

	@Builder
	public AveragePriceAndQuality(Long id, MarketFruit marketFruit, Integer averagePrice, Float averageQuality, LocalDate date) {
		this.id = id;
		this.marketFruit = marketFruit;
		this.averagePrice = averagePrice;
		this.averageQuality = averageQuality;
		this.date = date;
	}

}
