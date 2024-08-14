package assemble.eolmangyo.fruit.infrastructure.entity;


import assemble.eolmangyo.fruit.domain.AveragePriceAndQuality;
import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "average_price_and_quality")
public class AveragePriceAndQualityEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 시장 과일
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_fruit_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MarketFruitEntity marketFruit;

	// 평균 가격
	@Column(name = "average_price", nullable = false)
	private Integer averagePrice;

	// 평균 품질
	@Column(name = "average_quality", nullable = false, columnDefinition = "DECIMAL(2, 1)")
	private Float averageQuality;

	// 날짜
	@Column(name = "date", nullable = false)
	private LocalDate date;

	/**
	 * AveragePriceAndQualityEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static AveragePriceAndQualityEntity fromDomain(AveragePriceAndQuality averagePriceAndQuality) {
		return AveragePriceAndQualityEntity.builder()
			.marketFruit(MarketFruitEntity.fromDomain(averagePriceAndQuality.getMarketFruit()))
			.averagePrice(averagePriceAndQuality.getAveragePrice())
			.averageQuality(averagePriceAndQuality.getAverageQuality())
			.date(averagePriceAndQuality.getDate())
			.build();
	}

	// 2. toDomain
	public AveragePriceAndQuality toDomain() {
		return AveragePriceAndQuality.builder()
			.id(id)
			.marketFruit(marketFruit.toDomain())
			.averagePrice(averagePrice)
			.averageQuality(averageQuality)
			.date(date)
			.build();
	}


}
