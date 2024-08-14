package assemble.eolmangyo.user.infrastructure.entity;


import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import assemble.eolmangyo.user.domain.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class ReviewEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_fruit_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private MarketFruitEntity marketFruit;

	@Column(name = "purchase_quantity", nullable = false)
	private Integer purchaseQuantity;

	@Column(name = "purchase_price", nullable = false)
	private Integer purchasePrice;

	@Column(name = "quality", nullable = false, columnDefinition = "TINYINT")
	private Integer quality;

	@Column(name = "fruit_image_url", nullable = false, length = 200)
	private String fruitImageUrl;

	@Column(name = "review_content", nullable = false, length = 255)
	private String reviewContent;

	@Column(name = "review_date", nullable = false)
	private LocalDate reviewDate;

	/**
	 * ReviewEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static ReviewEntity fromDomain(Review review) {
		return ReviewEntity.builder()
			.user(UserEntity.fromDomain(review.getUser()))
			.marketFruit(MarketFruitEntity.fromDomain(review.getMarketFruit()))
			.purchaseQuantity(review.getPurchaseQuantity())
			.quality(review.getQuality())
			.fruitImageUrl(review.getFruitImage())
			.reviewContent(review.getReviewContent())
			.build();
	}

	// 2. toDomain
	public Review toDomain() {
		return Review.builder()
			.id(id)
			.user(user.toDomain())
			.marketFruit(marketFruit.toDomain())
			.purchaseQuantity(purchaseQuantity)
			.quality(quality)
			.fruitImage(fruitImageUrl)
			.reviewContent(reviewContent)
			.build();
	}
}
