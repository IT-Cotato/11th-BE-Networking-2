package cotato.backend.application.port;

import cotato.backend.adapter.in.dto.LikeFormDto;
import cotato.backend.application.port.in.LikeFormCommand;
import cotato.backend.application.port.out.AddLikePort;
import cotato.backend.application.port.out.LoadAdminPort;
import cotato.backend.application.port.out.LoadFormPort;
import cotato.backend.application.port.out.LoadLikePort;
import cotato.backend.domain.Admin;
import cotato.backend.domain.Form;
import cotato.backend.domain.Like;
import cotato.backend.exception.AlreadyLikedException;
import cotato.backend.exception.notFoundInfo.NotFoundAdminException;
import cotato.backend.exception.notFoundInfo.NotFoundFormException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaLikeFormService implements LikeFormCommand {
    private final LoadFormPort loadFormPort;
    private final LoadAdminPort loadAdminPort;
    private final LoadLikePort loadLikePort;
    private final AddLikePort addLikePort;

    @Transactional
    public void like(LikeFormDto dto) {
        Form form = loadFormPort.findById(dto.formId()).orElseThrow(NotFoundFormException::construct);
        Admin admin = loadAdminPort.findById(dto.adminId()).orElseThrow(NotFoundAdminException::construct);
        loadLikePort.findByFormAndAdmin(form, admin).ifPresentOrElse(
                like -> AlreadyLikedException.raise(),
                () -> addLikePort.save(new Like(form, admin))
        );
    }
}
