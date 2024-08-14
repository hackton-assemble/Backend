package assemble.eolmangyo.user.domain;


import assemble.eolmangyo.fruit.domain.MarketFruit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

	// 리뷰 id
	private Long id;

	// 유저
	private Users user;

	// 시장 과일
	private MarketFruit marketFruit;

	// 구매량
	private Integer purchaseQuantity;

	// 품질
	private Integer quality;

	// 과일 사진
	private String fruitImage;

	// 리뷰 내용
	private String reviewContent;

	// 리뷰 작성일
	private LocalDate reviewDate;

	@Builder
	public Review(Long id, Users user, MarketFruit marketFruit, Integer purchaseQuantity, Integer quality, String fruitImage, String reviewContent, LocalDate reviewDate) {
		this.id = id;
		this.user = user;
		this.marketFruit = marketFruit;
		this.purchaseQuantity = purchaseQuantity;
		this.quality = quality;
		this.fruitImage = fruitImage;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
	}

}
