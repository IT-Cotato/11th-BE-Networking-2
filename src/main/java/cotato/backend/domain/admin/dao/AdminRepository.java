package cotato.backend.domain.admin.dao;

import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByPhoneNumber(String phoneNumber);
    List<Admin> findAll();
}