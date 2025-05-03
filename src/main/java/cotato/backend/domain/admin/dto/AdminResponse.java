package cotato.backend.domain.admin.dto;

import cotato.backend.domain.admin.Admin;

public record AdminResponse(String name, String phoneNumber) {
    public static AdminResponse from(Admin admin) {
        return new AdminResponse(
                admin.getName(),
                admin.getPhoneNumber()
        );
    }
}
