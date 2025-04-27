package cotato.backend.domain.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long managerId;

    public ApplyLike likeApply(Apply apply) {
        return ApplyLike.builder()
            .apply(apply)
            .manager(this)
            .id(ApplyLike.ApplyLikeId.builder()
                .applyId(apply.getApplyId())
                .managerId(this.getManagerId())
                .build())
            .build();
    }
}
