package cotato.backend.domain.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.example.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}