package cotato.backend.domain.like;

import cotato.backend.common.exception.AdminNotFoundException;
import cotato.backend.common.exception.ApplicationNotFoundException;
import cotato.backend.common.exception.LikeAlreadyExistsException;
import cotato.backend.domain.like.dao.LikeRepository;
import cotato.backend.domain.admin.dao.AdminRepository;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cotato.backend.common.exception.ErrorCode.NOT_FOUND;
import static cotato.backend.common.exception.ErrorCode.RESOURCE_CONFLICT;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final AdminRepository adminRepository;
    private final ApplicationRepository applicationRepository;

    public void addLike(Long adminId, Long applicationId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException(NOT_FOUND));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(NOT_FOUND));

        if (likeRepository.existsByAdminAndApplication(admin, application)) {
            throw new LikeAlreadyExistsException(RESOURCE_CONFLICT);
        }

        Like like = Like.builder()
                .admin(admin)
                .application(application)
                .createdAt(LocalDateTime.now())
                .build();
        likeRepository.save(like);

    }
}

