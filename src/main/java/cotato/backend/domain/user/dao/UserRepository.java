package cotato.backend.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
