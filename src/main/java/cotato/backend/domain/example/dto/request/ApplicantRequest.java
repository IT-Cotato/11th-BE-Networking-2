package cotato.backend.domain.example.dto.request;

import cotato.backend.domain.example.entity.Applicant;
import lombok.Getter;

@Getter
public class ApplicantRequest {

    private String name;

    private int generation;

    private int age;

    private Applicant.Part part;

    private int passion;

    private int growth;

    private String phoneNumber;
}

