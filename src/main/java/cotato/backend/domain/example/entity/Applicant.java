package cotato.backend.domain.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private Integer generation;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Part part;

    @Column(nullable = false)
    private Integer passionScore;

    @Column(nullable = false)
    private Integer growthScore;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false)
    private Integer likeCount = 0;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @Builder
    public Applicant(String name, Integer generation, Integer age, Part part,
                     Integer passionScore, Integer growthScore, String phone, LocalDateTime submittedAt) {
        this.name = name;
        this.generation = generation;
        this.age = age;
        this.part = part;
        this.passionScore = passionScore;
        this.growthScore = growthScore;
        this.phone = phone;
        this.submittedAt = submittedAt;
        this.likeCount = 0;
    }

    public void addLike() {
        this.likeCount++;
    }
}