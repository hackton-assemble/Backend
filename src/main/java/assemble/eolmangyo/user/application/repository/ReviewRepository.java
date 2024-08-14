package assemble.eolmangyo.user.application.repository;


import assemble.eolmangyo.user.domain.Review;


public interface ReviewRepository {

	/**
	 * ReviewRepository
	 * 1. 리뷰 저장
	 */

	// 리뷰 저장
	Review save(Review review);

}
