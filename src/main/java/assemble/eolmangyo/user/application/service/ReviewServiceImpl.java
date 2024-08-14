package assemble.eolmangyo.user.application.service;


import assemble.eolmangyo.user.api.response.ReviewListResponseDto;
import assemble.eolmangyo.user.application.repository.ReviewRepository;
import assemble.eolmangyo.user.domain.Review;
import assemble.eolmangyo.user.domain.dto.RegisterReviewInDto;
import assemble.eolmangyo.user.domain.dto.ReviewInfoDto;
import assemble.eolmangyo.user.infrastructure.entity.ReviewEntity;
import assemble.eolmangyo.user.infrastructure.jpa.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl {

	// repository
	private final ReviewRepository reviewRepository;
	// util
	 private final ModelMapper modelMapper;
	private final ReviewJpaRepository reviewJpaRepository;


	/**
	 * ReviewService
	 * 1. 리뷰 등록
	 */

	// 1. 리뷰 등록
	public Review registerReview(RegisterReviewInDto inDto) {
		Review review = modelMapper.map(inDto, Review.class);
		// 리뷰 저장
		return reviewRepository.save(review);
	}


	public ReviewListResponseDto getReviewList(Long marketFruitId) {
		List<ReviewEntity> reviewList = reviewJpaRepository.findByMarketFruitId(marketFruitId);
		List<ReviewInfoDto> reviewInfoDtoList = new ArrayList<>();
		reviewList.forEach(reviewEntity -> {
			reviewInfoDtoList.add(
				ReviewInfoDto.builder()
					.reviewId(reviewEntity.getId())
					.userNickname(reviewEntity.getUser().getNickName())
					.quality(reviewEntity.getQuality())
					.price(reviewEntity.getPurchasePrice())
					.fruitImageUrl(reviewEntity.getFruitImageUrl())
					.createdAt(reviewEntity.getReviewDate())
					.build()
				);
			});

		ReviewEntity reviewInfo = reviewList.get(0);
		return ReviewListResponseDto.builder()
			.fruitName(reviewInfo.getMarketFruit().getFruit().getFruitName())
			.fruitId(reviewInfo.getMarketFruit().getFruit().getId())
			.marketName(reviewInfo.getMarketFruit().getMarket().getMarketName())
			.marketId(reviewInfo.getMarketFruit().getMarket().getId())
			.countType(reviewInfo.getMarketFruit().getFruit().getFruitCountType())
			.reviews(reviewInfoDtoList)
			.build();
	}

}
