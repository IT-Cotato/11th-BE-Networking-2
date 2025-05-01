package cotato.backend.domain.applicant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;
    private int generation;
    private int age;

    @Enumerated(EnumType.STRING)
    private Part part;
    private int participation;
    private int growth;

    @Column(length = 11)
    private String phone;
    private LocalDateTime submittedAt;
    private int likes;

    public void addLike() {
        this.likes++;
    }

    public enum Part {
        PLANNER, DESIGNER, FRONTEND, BACKEND
    }

}
