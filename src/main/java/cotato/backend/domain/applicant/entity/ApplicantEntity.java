package cotato.backend.domain.applicant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "applicant")
public class ApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Column(nullable = false)
    @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
    private Integer generation;

    @Column(nullable = false)
    @Min(value = 1, message = "나이는 1살 이상이어야 합니다.")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Part part;

    @Column(nullable = false)
    @Min(value = 0, message = "참여 점수는 0 이상이어야 합니다.")
    private Integer participationScore;

    @Column(nullable = false)
    @Min(value = 0, message = "성장 점수는 0 이상이어야 합니다.")
    private Integer growthScore;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @Column(nullable = false)
    @Min(value = 0, message = "좋아요 수는 0 이상이어야 합니다.")
    private Integer likes;

    @Builder
    public ApplicantEntity(
            String name,
            Integer generation,
            Integer age,
            Part part,
            Integer participationScore,
            Integer growthScore,
            String phoneNumber,
            LocalDateTime submittedAt,
            Integer likes
    ) {
        this.name = name;
        this.generation = generation;
        this.age = age;
        this.part = part;
        this.participationScore = participationScore;
        this.growthScore = growthScore;
        this.phoneNumber = phoneNumber;
        this.submittedAt = submittedAt;
        this.likes = likes;
    }

    public void increaseLikes() {
        this.likes++;
    }

}
