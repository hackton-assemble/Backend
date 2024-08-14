package assemble.eolmangyo.market.infrastructure.entity;


import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import assemble.eolmangyo.market.domain.Store;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class StoreEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 시장
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MarketEntity market;

	// 가게 이름
	@Column(name = "store_name", nullable = false, length = 50)
	private String storeName;

	// 가게 위치
	@Column(name = "store_location", nullable = false, length = 100)
	private String storeLocation;

	/**
	 * StoreEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static StoreEntity fromDomain(Store store) {
		return StoreEntity.builder()
			.market(MarketEntity.fromDomain(store.getMarket()))
			.storeName(store.getStoreName())
			.storeLocation(store.getStoreLocation())
			.build();
	}

	// 2. toDomain
	public Store toDomain() {
		return Store.builder()
			.id(id)
			.market(market.toDomain())
			.storeName(storeName)
			.storeLocation(storeLocation)
			.build();
	}
}
