package cotato.backend.domain.example.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Part;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplicantResponse {

    private Long id;
    private int generation;
    private Part part;
    private int passion;
    private int growth;
    private int likes;
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime submitTime;

    public static ApplicantResponse from(Applicant applicant) {
        return ApplicantResponse.builder()
                .id(applicant.getId())
                .generation(applicant.getGeneration())
                .part(applicant.getPart())
                .passion(applicant.getPassion())
                .growth(applicant.getGrowth())
                .likes(applicant.getLikes())
                .phoneNumber(applicant.getPhoneNumber())
                .submitTime(applicant.getSubmitTime())
                .build();
    }
}
