package assemble.eolmangyo.user.api.request;


import lombok.Getter;


@Getter
public class RegisterReviewRequestDto {

	private Long marketFruitId;
	private Integer purchaseQuantity;
	private Integer quality;
	private String fruitImageUrl;
	private String reviewContent;
	private String reviewDate;

}
