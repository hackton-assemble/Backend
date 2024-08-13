package assemble.eolmangyo.market.infrastructure.entity;


import assemble.eolmangyo.market.domain.Market;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "market")
public class MarketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 시장 이름
	@Column(name = "market_name", nullable = false, length = 50)
	private String marketName;

	/**
	 * MarketEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static MarketEntity fromDomain(Market market) {
		return MarketEntity.builder()
			.marketName(market.getMarketName())
			.build();
	}

	// 2. toDomain
	public Market toDomain() {
		return Market.builder()
			.id(id)
			.marketName(marketName)
			.build();
	}

}
