package cotato.backend.domain.apply.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "apply_entity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_id", nullable = false)
    private long id;

    private String name;

    private int generation; //지원 기수

    private int age;

    @Enumerated(EnumType.STRING)
    private PartType part;

    private int participationScore; //참여 적극성

    private int growthScore;  //성장 의지

    @Column(length = 11)
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime submitTime;

    private int likeCount = 0;

    @Builder
    public Apply(String name, int generation, int age, PartType part, int participationScore, int growthScore, String phoneNumber, LocalDateTime submitTime, int likeCount) {
        this.name = name;
        this.generation = generation;
        this.age = age;
        this.part = part;
        this.participationScore = participationScore;
        this.growthScore = growthScore;
        this.phoneNumber = phoneNumber;
        this.submitTime = submitTime;
        this.likeCount = likeCount;
    }

    public void addLike() {
        this.likeCount += 1;
    }
}
