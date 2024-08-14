package assemble.eolmangyo.market.infrastructure.jpa;


import assemble.eolmangyo.market.infrastructure.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MarketJapRepository extends JpaRepository<MarketEntity, Long> {
}
