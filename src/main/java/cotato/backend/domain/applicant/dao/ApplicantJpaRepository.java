package cotato.backend.domain.applicant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.applicant.entity.ApplicantEntity;

public interface ApplicantJpaRepository extends JpaRepository<ApplicantEntity, Long> {
}