package assemble.eolmangyo.user.infrastructure;


import assemble.eolmangyo.user.application.repository.ReviewRepository;
import assemble.eolmangyo.user.domain.Review;
import assemble.eolmangyo.user.infrastructure.entity.ReviewEntity;
import assemble.eolmangyo.user.infrastructure.jpa.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

	private final ReviewJpaRepository reviewJpaRepository;

	/**
	 * ReviewRepository
	 * 1. 리뷰 저장
	 */

	// 1. 리뷰 저장
	@Override
	public Review save(Review review) {
		return reviewJpaRepository.save(ReviewEntity.fromDomain(review)).toDomain();
	}

}
