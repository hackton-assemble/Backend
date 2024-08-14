package assemble.eolmangyo.user.domain.dto;


import assemble.eolmangyo.fruit.domain.MarketFruit;
import assemble.eolmangyo.user.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RegisterReviewInDto {

	private Users user;
	private MarketFruit marketFruit;
	private Integer purchaseQuantity;
	private Integer quality;
	private String fruitImage;
	private String reviewContent;
	private LocalDate reviewDate;

	@Builder(toBuilder = true)
	public RegisterReviewInDto(Users user, MarketFruit marketFruit, Integer purchaseQuantity, Integer quality, String fruitImage, String reviewContent, LocalDate reviewDate) {
		this.user = user;
		this.marketFruit = marketFruit;
		this.purchaseQuantity = purchaseQuantity;
		this.quality = quality;
		this.fruitImage = fruitImage;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
	}

}
