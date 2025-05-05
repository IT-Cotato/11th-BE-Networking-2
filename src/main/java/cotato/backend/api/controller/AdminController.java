package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.admin.AdminService;
import cotato.backend.domain.admin.dto.CreateAdminRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<DataResponse<Long>> save(@RequestBody CreateAdminRequestDto req) {
        Long save = adminService.save(req);
        return ResponseEntity.ok(DataResponse.created(save));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Admin>>> getAllAdmins() {
        List<Admin> allAdmins = adminService.getAllAdmins();
        return ResponseEntity.ok(DataResponse.from(allAdmins));
    }

}
