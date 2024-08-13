package assemble.eolmangyo.fruit.infrastructure.entity;


import assemble.eolmangyo.fruit.domain.Fruit;
import assemble.eolmangyo.fruit.domain.enums.FruitCountType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "fruit")
public class FruitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 과일 이름
	@Column(name = "fruit_name", nullable = false, length = 50)
	private String fruitName;

	// 수량 타입
	@Column(name = "fruit_count_type", nullable = false, length = 1)
	private FruitCountType fruitCountType;

	/**
	 * FruitEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static FruitEntity fromDomain(Fruit fruit) {
		return FruitEntity.builder()
			.fruitName(fruit.getFruitName())
			.fruitCountType(fruit.getFruitCountType())
			.build();
	}

	// 2. toDomain
	public Fruit toDomain() {
		return Fruit.builder()
			.id(id)
			.fruitName(fruitName)
			.fruitCountType(fruitCountType)
			.build();
	}

}
