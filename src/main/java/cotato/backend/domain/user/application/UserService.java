package cotato.backend.domain.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.user.dao.UserRepository;
import cotato.backend.domain.user.dto.response.UserResponse;
import cotato.backend.domain.user.entity.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(String username, String email, String password) {
        UserEntity user = UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user).getId();
    }

    public UserResponse findById(Long id) {
        return UserResponse.from(
                userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND))
        );
    }
}
