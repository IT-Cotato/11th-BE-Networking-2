package cotato.backend.domain.application;

import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.application.enums.Part;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(name = "application_generation")
    private int generation;

    @Column(name = "application_part")
    private Part part;

    @Column(name = "application_participation")
    private int participation;

    @Column(name = "application_growth")
    private int growth;

    @Column(name = "application_like")
    private int like;

    @Column(name = "application_submittedAt")
    private LocalDateTime submittedAt;

    @Builder
    public Application(Applicant applicant, int generation, Part part, int participation, int growth) {
        validateGeneration(generation);
        validateParticipation(participation);
        validateGrowth(growth);

        this.applicant = applicant;
        this.generation = generation;
        this.part = part;
        this.participation = participation;
        this.growth = growth;
        this.like = 0;
        this.submittedAt = LocalDateTime.now();
    }

    private void validateGeneration(int generation) {
        if (generation < 1) {
            throw new IllegalArgumentException("지원 기수는 1 이상이어야 합니다.");
        }
    }

    private void validateParticipation(int participation) {
        if (participation < 0 || participation > 10) {
            throw new IllegalArgumentException("참여 적극성은 0~10 사이여야 합니다.");
        }
    }

    private void validateGrowth(int growth) {
        if (growth < 0 || growth > 10) {
            throw new IllegalArgumentException("성장 의지는 0~10 사이여야 합니다.");
        }
    }


    public int addLike() {
        return this.like += 1;
    }
}
