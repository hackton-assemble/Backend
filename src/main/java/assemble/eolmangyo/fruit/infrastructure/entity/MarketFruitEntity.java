package assemble.eolmangyo.fruit.infrastructure.entity;


import assemble.eolmangyo.fruit.domain.MarketFruit;
import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import assemble.eolmangyo.market.infrastructure.entity.MarketEntity;
import assemble.eolmangyo.market.infrastructure.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "market_fruit")
public class MarketFruitEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 시장
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MarketEntity market;

	// 가게
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private StoreEntity store;

	// 과일
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fruit_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private FruitEntity fruit;

	/**
	 * MarketFruitEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static MarketFruitEntity fromDomain(MarketFruit marketFruit) {
		return MarketFruitEntity.builder()
			.market(MarketEntity.fromDomain(marketFruit.getMarket()))
			.store(StoreEntity.fromDomain(marketFruit.getStore()))
			.fruit(FruitEntity.fromDomain(marketFruit.getFruit()))
			.build();
	}

	// 2. toDomain
	public MarketFruit toDomain() {
		return MarketFruit.builder()
			.id(id)
			.market(market.toDomain())
			.store(store.toDomain())
			.fruit(fruit.toDomain())
			.build();
	}
}
