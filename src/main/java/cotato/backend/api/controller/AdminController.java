package cotato.backend.api.controller;

import cotato.backend.common.dto.SuccessResponse;
import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.admin.AdminService;
import cotato.backend.domain.admin.dto.CreateAdminRequestDto;
import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.applicant.ApplicantService;
import cotato.backend.domain.application.dto.CreateApplicationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Long>> save(@RequestBody CreateAdminRequestDto req) {
        Long save = adminService.save(req);
        return ResponseEntity.ok(new SuccessResponse<>(200,"관리자 저장 성공", save));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Admin>>> getAllAdmins() {
        List<Admin> allAdmins = adminService.getAllAdmins();
        return ResponseEntity.ok(new SuccessResponse<>(200,"모든 관리자 조회 성공", allAdmins));
    }

}
