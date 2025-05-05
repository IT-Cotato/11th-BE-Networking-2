package cotato.backend.domain.admin;

import cotato.backend.domain.admin.dto.CreateAdminRequestDto;

import java.util.List;

public interface AdminService {
    public Long save(CreateAdminRequestDto dto);
    public List<Admin> getAllAdmins();
}
