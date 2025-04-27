package cotato.backend.domain.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apply_like")
@Getter
public class ApplyLike {

    @EmbeddedId
    private ApplyLikeId id;

    @MapsId("applyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Apply apply;

    @MapsId("managerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;


    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Builder
    @Getter @Setter
    public static class ApplyLikeId implements Serializable {
        private Long applyId;
        private Long managerId;

    }

}
