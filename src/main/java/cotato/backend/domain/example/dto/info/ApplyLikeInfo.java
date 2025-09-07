package cotato.backend.domain.example.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder @ToString
public class ApplyLikeInfo {
    private Long applyId;

    private Long likeCount;


}
