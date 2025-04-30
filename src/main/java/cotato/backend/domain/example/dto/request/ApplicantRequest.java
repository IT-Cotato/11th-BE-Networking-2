package cotato.backend.domain.example.dto.request;

import org.hibernate.annotations.processing.Pattern;

import cotato.backend.domain.example.entity.Enum.Part;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantRequest {

    @Size(min = 2, max = 10)
    private String name;

    @Min(1)
    private Integer generation;

    @Min(22)
    @Max(30)
    private Integer age;

    private Part part;

    @Min(0)
    @Max(10)
    private Integer passionScore;

    @Min(0)
    @Max(10)
    private Integer growthScore;

    @Pattern(regexp = "^010\\d{8}$")
    private String phone;
}