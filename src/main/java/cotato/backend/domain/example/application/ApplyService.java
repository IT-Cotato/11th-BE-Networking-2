package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplyLikeRepository;
import cotato.backend.domain.example.dao.ApplyRepository;
import cotato.backend.domain.example.dao.ManagerRepository;
import cotato.backend.domain.example.dto.response.ApplyDetailResponse;
import cotato.backend.domain.example.entity.Apply;
import cotato.backend.domain.example.entity.ApplyLike;
import cotato.backend.domain.example.entity.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final ApplyLikeRepository applyLikeRepository;
    private final ManagerRepository managerRepository;

    public Long saveApply(Apply apply) {
        //지원시간 설정
        apply.createdAt(LocalDateTime.now());

        return applyRepository.save(apply).getApplyId();
    }

    public ApplyDetailResponse findByApplyId(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        ApplyDetailResponse applyDetailResponse = ApplyDetailResponse.from(apply);
        
        //좋아요 계산
        int likeCount = applyLikeRepository.countLikesByApplyId(applyId);
        applyDetailResponse.setLikeCount(likeCount);
        
        return applyDetailResponse;

    }

    public Page<Apply> findApplyLists(Pageable pageable) {
        return applyRepository.findAll(pageable);
    }

    @Transactional
    public void likeApply(Long applyId, Long mangerId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        Manager manager = managerRepository.findById(mangerId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        //좋아요 중복 체크
        boolean isAlreadyLiked = applyLikeRepository.existsApplyLike(apply.getApplyId(), manager.getManagerId());

        if (isAlreadyLiked) {
            throw new IllegalArgumentException("Already liked by this manager");
        }


        ApplyLike applyLike = manager.likeApply(apply);

        //applyLikeRepository.save(applyLike);
        //save 메소드 사용시 apply, manager 조인 쿼리 발생
        //위에서 중복체크를 했기 때문에 native쿼리로 영속성 컨텍스트를 거치지 않고 insert
        applyLikeRepository.insertApplyLike(
                applyLike.getApply().getApplyId(),
                applyLike.getManager().getManagerId()
        );

    }
}
