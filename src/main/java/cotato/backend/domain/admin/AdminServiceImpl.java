package cotato.backend.domain.admin;

import cotato.backend.common.exception.AdminAlreadyExistsException;
import cotato.backend.domain.admin.dao.AdminRepository;
import cotato.backend.domain.admin.dto.CreateAdminRequestDto;
import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static cotato.backend.common.exception.ErrorCode.RESOURCE_CONFLICT;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    @Override
    public Long save(CreateAdminRequestDto dto) {
        if (adminRepository.findByPhoneNumber(dto.phoneNumber()).isPresent()) {
            throw new AdminAlreadyExistsException(RESOURCE_CONFLICT);
        }
        return adminRepository.save(dto.toEntity()).getId();
    }
    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
