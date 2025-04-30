package cotato.backend.domain.applicant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "example_id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Builder
	public Applicant(String name) {
		this.name = name;
	}
}