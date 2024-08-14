package assemble.eolmangyo.user.api.facade;


import assemble.eolmangyo.fruit.application.service.MarketFruitServiceImpl;
import assemble.eolmangyo.fruit.domain.enums.FruitCountType;
import assemble.eolmangyo.fruit.infrastructure.entity.AveragePriceAndQualityEntity;
import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import assemble.eolmangyo.fruit.infrastructure.jpa.AveragePriceAndQualityJpaRepository;
import assemble.eolmangyo.fruit.infrastructure.jpa.MarketFruitJpaRepository;
import assemble.eolmangyo.fruit.infrastructure.jpa.SeasonFruitJpaRepository;
import assemble.eolmangyo.global.common.exception.BaseException;
import assemble.eolmangyo.global.common.response.BaseResponseStatus;
import assemble.eolmangyo.market.infrastructure.querydsl.MarketFruitQueryDsl;
import assemble.eolmangyo.user.api.request.RegisterReviewRequestDto;
import assemble.eolmangyo.user.application.service.ReviewServiceImpl;
import assemble.eolmangyo.user.application.service.StampServiceImpl;
import assemble.eolmangyo.user.application.service.UserServiceImpl;
import assemble.eolmangyo.user.domain.dto.RegisterReviewOutDto;
import assemble.eolmangyo.user.infrastructure.entity.ReviewEntity;
import assemble.eolmangyo.user.infrastructure.entity.StampEntity;
import assemble.eolmangyo.user.infrastructure.entity.UserEntity;
import assemble.eolmangyo.user.infrastructure.jpa.ReviewJpaRepository;
import assemble.eolmangyo.user.infrastructure.jpa.StampJpaRepository;
import assemble.eolmangyo.user.infrastructure.jpa.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RegisterReviewFacade {

	// service
	private final ReviewServiceImpl reviewServiceImpl;
	private final StampServiceImpl stampServiceImpl;
	private final UserServiceImpl userServiceImpl;
	private final MarketFruitServiceImpl marketFruitServiceImpl;

	// util
	private final ModelMapper modelMapper;
	private final UserJpaRepository userJpaRepository;
	private final MarketFruitJpaRepository marketFruitJpaRepository;
	private final ReviewJpaRepository reviewJpaRepository;
	private final StampJpaRepository stampJpaRepository;
	private final SeasonFruitJpaRepository seasonFruitJpaRepository;
	private final MarketFruitQueryDsl marketFruitQueryDsl;
	private final AveragePriceAndQualityJpaRepository averagePriceAndQualityJpaRepository;


	/**
	 * RegisterReviewFacade
	 * 1. 리뷰 등록
	 * 2. 리뷰 저장
	 * 3. 스탬프 저장
	 * 4. 평균 가격과 평균 품질 업데이트
	 */

	// 1. 리뷰 등록
	public RegisterReviewOutDto registerReview(UUID userUuid, RegisterReviewRequestDto requestDto) {
		// 유저 조회
		UserEntity user = userJpaRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
		// 시장 과일 조회
		MarketFruitEntity marketFruit = marketFruitJpaRepository.findById(requestDto.getMarketFruitId())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MARKET_FRUIT));
		// 리뷰 저장
		ReviewEntity review = ReviewEntity.builder()
			.user(user)
			.marketFruit(marketFruit)
			.quality(requestDto.getQuality())
			.purchaseQuantity(requestDto.getPurchaseQuantity())
			.purchasePrice(requestDto.getPurchasePrice())
			.fruitImageUrl(requestDto.getFruitImageUrl())
			.reviewContent(requestDto.getReviewContent())
			.build();
		reviewJpaRepository.save(review);
		// 스탬프 저장
		StampEntity lastStamp = stampJpaRepository.findFirstByUserOrderByCreatedAtDesc(user)
			.orElseGet(() -> null);
		Integer stampNumber = lastStamp == null ? 1 : lastStamp.getStampNumber() + 1;
		// 과일 스탬프 차례인지 확인
		MarketFruitEntity randomSeasonFruit = null;
		if (stampNumber%5 == 0) {
			String randomSeasonFruitName = seasonFruitJpaRepository.findRandomSeasonFruit();
			randomSeasonFruit = marketFruitQueryDsl.findRandomFruit(randomSeasonFruitName);
		}

		StampEntity stamp = StampEntity.builder()
				.stampNumber(stampNumber)
				.user(user)
				.marketFruit(randomSeasonFruit)
				.isReceived(true)
			.build();
		stampJpaRepository.save(stamp);
		// 과일 스탬프인지 확인
		Boolean isFruitStamp = stamp.getMarketFruit() != null;

		// 평균 가격 계산
		FruitCountType countType = marketFruit.getFruit().getFruitCountType();
		Integer averagePrice;
		if (countType == FruitCountType.COUNT) {
			averagePrice = requestDto.getPurchasePrice() / requestDto.getPurchaseQuantity();
		} else {
			averagePrice = requestDto.getPurchasePrice() / (requestDto.getPurchaseQuantity() / 100);
		}
		// 오늘의 평균 값들 업데이트
		AveragePriceAndQualityEntity averagePriceAndQuality = averagePriceAndQualityJpaRepository.findByMarketFruitIdAndDate(marketFruit.getId(), LocalDate.now())
			.orElseGet(() -> AveragePriceAndQualityEntity.builder()
					.marketFruit(marketFruit)
					.date(LocalDate.now())
					.build());
		Integer count = averagePriceAndQualityJpaRepository.countByDate(LocalDate.now());
		// null이라면 0이라 생각하고 평균 가격을 계산
		Integer updatedAveragePrice = averagePriceAndQuality.getAveragePrice() == null
			? averagePrice
			: (averagePriceAndQuality.getAveragePrice()*count + averagePrice ) / (count +1);
		// null이라면 0이라 생각하고 평균 품질을 계산
		Float updatedAverageQuality = averagePriceAndQuality.getAverageQuality() == null
			? (float) requestDto.getQuality()
			: (averagePriceAndQuality.getAverageQuality()*count + requestDto.getQuality()) / (count +1);
		updatedAverageQuality = (float) Math.round(updatedAverageQuality * 10) / 10.0f;
		// 업데이트
		averagePriceAndQuality.updateAveragePriceAndQuality(updatedAveragePrice, updatedAverageQuality);
		averagePriceAndQualityJpaRepository.save(averagePriceAndQuality);

		// 응답값 return
		return new RegisterReviewOutDto(isFruitStamp);
	}


//	// 1. 리뷰 등록
//	public RegisterReviewOutDto registerReview(UUID userUuid, RegisterReviewRequestDto requestDto) {
//		// 유저 조회
//		Users user = userServiceImpl.findUserByUuid(userUuid);
//
//		// 시장 과일 조회
//		MarketFruit marketFruit = marketFruitServiceImpl.findById(requestDto.getMarketFruitId());
//
//		// 리뷰 저장
//		RegisterReviewInDto inDto = modelMapper.map(requestDto, RegisterReviewInDto.class);
//		Review review = this.saveReview(requestDto, user, marketFruit, inDto);
//
//		// 스탬프 저장
//		Stamp stamp = this.saveStamp(user, marketFruit);
//
//		// 과일 스탬프인지 확인
//		Boolean isFruitStamp = false;
//		if (stamp.getMarketFruit() != null) {
//			isFruitStamp = true;
//		}
//
//		// 응답값 return
//		return new RegisterReviewOutDto(isFruitStamp);
//	}
//
//
//	// 2. 리뷰 저장
//	public Review saveReview(RegisterReviewRequestDto requestDto, Users user, MarketFruit marketFruit, RegisterReviewInDto inDto) {
//		inDto = inDto.toBuilder()
//			.user(user)
//			.marketFruit(marketFruit)
//			.fruitImage(requestDto.getFruitImageUrl())
//			.build();
//		return reviewServiceImpl.registerReview(inDto);
//	}
//
//
//	// 3. 스탬프 저장
//	public Stamp saveStamp(Users user, MarketFruit marketFruit) {
//		// 마지막 스템프 번호 조회
//		Stamp lastStamp = stampServiceImpl.findLastStamp(user);
//		// 스탬프 저장
//		RegisterStampInDto inDto = RegisterStampInDto.builder()
//			.stampNumber(lastStamp.getStampNumber() + 1)
//			.user(user)
//			.marketFruit(marketFruit)
//			.isReceived(true)
//			.build();
//		return stampServiceImpl.registerStamp(inDto);
//	}

}
