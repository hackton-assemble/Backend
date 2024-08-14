package assemble.eolmangyo.user.infrastructure.entity;


import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import assemble.eolmangyo.user.domain.Stamp;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stamp")
public class StampEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "stamp_number", nullable = false)
	private Integer stampNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_fruit_id", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MarketFruitEntity marketFruit;

	@Column(name = "is_received", nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean isReceived;


	/**
	 * StampEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static StampEntity fromDomain(Stamp stamp) {
		return StampEntity.builder()
			.stampNumber(stamp.getStampNumber())
			.user(UserEntity.fromDomain(stamp.getUser()))
			.marketFruit(MarketFruitEntity.fromDomain(stamp.getMarketFruit()))
			.isReceived(stamp.getIsReceived())
			.build();
	}

	// 2. toDomain
	public Stamp toDomain() {
		return Stamp.builder()
			.id(id)
			.stampNumber(stampNumber)
			.user(user.toDomain())
			.marketFruit(marketFruit.toDomain())
			.isReceived(isReceived)
			.build();
	}

}
