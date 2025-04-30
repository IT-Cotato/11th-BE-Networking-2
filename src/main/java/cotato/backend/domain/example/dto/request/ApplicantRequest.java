package cotato.backend.domain.example.dto.request;



import cotato.backend.domain.example.entity.Enum.Part;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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

    @Pattern(regexp = "^010\\d{8}$", message = "010으로 시작하는 11자리 숫자여야 합니다.")
    private String phone;
}