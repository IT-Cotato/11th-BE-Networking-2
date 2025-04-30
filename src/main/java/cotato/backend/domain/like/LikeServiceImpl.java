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

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final AdminRepository adminRepository;
    private final ApplicationRepository applicationRepository;

    public void addLike(Long adminId, Long applicationId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException());
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException());

        if (likeRepository.existsByAdminAndApplication(admin, application)) {
            throw new LikeAlreadyExistsException();
        }

        Like like = Like.builder()
                .admin(admin)
                .application(application)
                .build();
        likeRepository.save(like);

    }
}

