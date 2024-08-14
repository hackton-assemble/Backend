package assemble.eolmangyo.market.infrastructure.querydsl;


import assemble.eolmangyo.fruit.infrastructure.entity.MarketFruitEntity;
import assemble.eolmangyo.fruit.infrastructure.entity.QFruitEntity;
import assemble.eolmangyo.fruit.infrastructure.entity.QMarketFruitEntity;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static assemble.eolmangyo.fruit.infrastructure.entity.QFruitEntity.fruitEntity;
import static assemble.eolmangyo.fruit.infrastructure.entity.QMarketFruitEntity.marketFruitEntity;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MarketFruitQueryDsl {

	private final JPAQueryFactory queryFactory;

	/**
	 * MarketFruitQueryDsl
	 * 1. 과일 이름으로 랜덤 조회
	 */

	// 1. 과일 이름으로 랜덤 조회
	public MarketFruitEntity findRandomFruit(String randomFruitName) {
		QMarketFruitEntity entity = marketFruitEntity;
		QFruitEntity ent2 = fruitEntity;
		return queryFactory
			.selectFrom(marketFruitEntity)
			.join(fruitEntity).on(fruitEntity.fruitName.eq(randomFruitName))
			.where(
				marketFruitEntity.fruit.eq(fruitEntity)
			)
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(1)
			.fetchFirst();
	}
}
