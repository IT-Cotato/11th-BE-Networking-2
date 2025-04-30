package cotato.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.user.application.UserService;
import cotato.backend.domain.user.dto.request.UserRequest;
import cotato.backend.domain.user.dto.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(
                DataResponse.created(
                        DefaultIdResponse.of(
                                userService.save(request.getUsername(), request.getEmail(), request.getPassword())
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<UserResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                DataResponse.from(userService.findById(id))
        );
    }
}
