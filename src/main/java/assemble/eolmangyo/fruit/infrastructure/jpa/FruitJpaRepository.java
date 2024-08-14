package assemble.eolmangyo.fruit.infrastructure.jpa;


import assemble.eolmangyo.fruit.infrastructure.entity.FruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FruitJpaRepository extends JpaRepository<FruitEntity, Long> {
}
