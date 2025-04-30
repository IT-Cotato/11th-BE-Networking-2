package cotato.backend.domain.apply.converter;

import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import cotato.backend.domain.apply.entity.Apply;

public class ApplyConverter {

    public static Apply toApply(ApplyCreateRequest request) {
        return Apply.builder()
                .name(request.getName())
                .generation(request.getGeneration())
                .age(request.getAge())
                .part(request.getPart())
                .participationScore(request.getParticipationScore())
                .growthScore(request.getGrowthScore())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static ApplyCreateResponse toApplyCreateResponse(Apply apply) {
        return ApplyCreateResponse.builder()
                .name(apply.getName())
                .generation(apply.getGeneration())
                .age(apply.getAge())
                .part(apply.getPart())
                .participationScore(apply.getParticipationScore())
                .growthScore(apply.getGrowthScore())
                .phoneNumber(apply.getPhoneNumber())
                .build();
    }
}

