package cotato.backend.domain.like;

import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.application.Application;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="likes")
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;


    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
