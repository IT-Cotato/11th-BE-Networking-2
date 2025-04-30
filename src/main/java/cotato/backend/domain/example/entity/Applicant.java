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
    @Size(min=2, max=10)
    private String name;

    @Column(nullable = false)
    @Min(value = 1)
    private int generation;

    @Column(nullable = false)
    @Min(value = 22)
    @Max(value = 30)
    private int age;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Part part;

    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 10)
    private int passion;

    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 10)
    private int growth;

    @Column(nullable = false, length = 11)
    @Pattern(regexp = "010\\d{8}$")
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
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

    public void increaseLike() {
        this.likes++;
    }
}
