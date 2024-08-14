package assemble.eolmangyo.user.application.service;


import assemble.eolmangyo.user.application.repository.ReviewRepository;
import assemble.eolmangyo.user.domain.Review;
import assemble.eolmangyo.user.domain.dto.RegisterReviewInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {

	// repository
	private final ReviewRepository reviewRepository;
	// util
	 private final ModelMapper modelMapper;

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



}
