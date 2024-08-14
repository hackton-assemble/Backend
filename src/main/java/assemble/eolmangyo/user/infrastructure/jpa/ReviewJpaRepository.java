package assemble.eolmangyo.user.infrastructure.jpa;


import assemble.eolmangyo.user.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {

	List<ReviewEntity> findByMarketFruitId(Long marketFruitId);

}
