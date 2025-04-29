package cotato.backend.domain.example.entity;

import cotato.backend.domain.example.dto.request.ApplicantRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long id;

    @Column(nullable = false, length = 10)
    @Size(min=2, max=10, message="이름은 2글자 이상 10글자 이하여야 합니다.")
    private String name;

    @Column(nullable = false)
    @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
    private int generation;

    @Column(nullable = false)
    @Min(value = 22, message = "나이는 22살 이상이어야 합니다.")
    @Max(value = 30, message = "나이는 30살 이하여야 합니다.")
    private int age;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(nullable = false)
    @Min(value = 0, message = "참여 적극성은 0 이상이어야 합니다.")
    @Max(value = 10, message = "참여 적극성은 10 이하여야 합니다.")
    private int passion;

    @Column(nullable = false)
    @Min(value = 0, message = "성장 의지는 0 이상이어야 합니다.")
    @Max(value = 10, message = "성장 의지는 10 이하여야 합니다.")
    private int growth;

    @Column(nullable = false, length = 11)
    @Pattern(regexp = "010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다.")
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime submitTime;

    @Column(nullable = false)
    private int likes = 0;

    @Builder
    public Applicant(ApplicantRequest request) {
        this.name = request.getName();
        this.generation = request.getGeneration();
        this.age = request.getAge();
        this.part = request.getPart();
        this.passion = request.getPassion();
        this.growth = request.getGrowth();
        this.phoneNumber = request.getPhoneNumber();
        this.submitTime = LocalDateTime.now();
    }

    public enum Part {
        MANAGER, DESIGNER, FRONTEND, BACKEND
    }
}
