package assemble.eolmangyo.user.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInfoDto {

	private Long reviewId;
	private String userNickname;
	private Integer quality;
	private Integer price;
	private String fruitImageUrl;
	private LocalDate createdAt;
	private String reviewContent;

}
