package cotato.backend.domain.apply.converter;

import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import cotato.backend.domain.apply.entity.Apply;

public class ApplyConverter {

    //요청(Request) DTO → Entity 변환
    //클라이언트가 POST 요청으로 지원자 서류 등록을 할 때
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

    //Entity → 응답(Response) DTO 변환
    //서류 등록이 완료된 뒤, 클라이언트에게 등록된 결과를 응답으로 줄 때
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

