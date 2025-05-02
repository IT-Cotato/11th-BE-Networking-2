package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;


public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Page<Applicant> findAllByOrderBySubmittedAtAsc(Pageable pageable);
    Page<Applicant> findAllByOrderBySubmittedAtDesc(Pageable pageable);
    Page<Applicant> findAllByOrderByLikesDescSubmittedAtAsc(Pageable pageable);
    Page<Applicant> findAllByOrderByParticipationDescSubmittedAtAsc(Pageable pageable);
    Page<Applicant> findAllByOrderByGrowthDescSubmittedAtAsc(Pageable pageable);

}

