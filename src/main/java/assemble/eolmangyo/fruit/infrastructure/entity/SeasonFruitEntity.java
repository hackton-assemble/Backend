package assemble.eolmangyo.fruit.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "season_fruit")
public class SeasonFruitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 과일 이름
	@Column(name = "fruit_name", nullable = false, length = 50)
	private String fruitName;

}
