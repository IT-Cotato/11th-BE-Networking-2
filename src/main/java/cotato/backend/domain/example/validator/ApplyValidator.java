package cotato.backend.domain.example.validator;

import cotato.backend.common.exception.ApplyArgumentException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dto.request.ApplyCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

public class ApplyValidator {

    public static void validateApplyCreateRequest(ApplyCreateRequest applyCreateRequest){
        validateName(applyCreateRequest.getName());
        validatePhoneNumber(applyCreateRequest.getPhoneNumber());
        validateAge(applyCreateRequest.getAge());
        validateParticipationLevel(applyCreateRequest.getParticipationLevel());
        validateGrowLevel(applyCreateRequest.getGrowLevel());
    }

    private static void validateName(String name) {
        boolean isValid = (name.getBytes(StandardCharsets.UTF_8).length >= ApplyValidEnum.MIN_LENGTH_NAME.getValue()
                        && name.getBytes(StandardCharsets.UTF_8).length <= ApplyValidEnum.MAX_LENGTH_NAME.getValue());

        if(!isValid) {
            throw new ApplyArgumentException(ErrorCode.BAD_REQUEST, ApplyValidEnum.ERROR_MESSAGE_NAME);
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        String regex = "010[0-9]{8}";
        boolean isValid = phoneNumber.matches(regex);

        if(!isValid) {
            throw new ApplyArgumentException(ErrorCode.BAD_REQUEST, ApplyValidEnum.ERROR_MESSAGE_PHONE_NUMBER);
        }
    }

    private static void validateAge(int age) {
        boolean isValid = (age >= ApplyValidEnum.MIN_AGE.getValue()
                        && age <= ApplyValidEnum.MAX_AGE.getValue());

        if(!isValid) {
            throw new ApplyArgumentException(ErrorCode.BAD_REQUEST, ApplyValidEnum.ERROR_MESSAGE_AGE);
        }
    }

    private static void validateParticipationLevel(int participationLevel) {
        boolean isValid = (participationLevel >= ApplyValidEnum.MIN_PARTICIPATION_LEVEL.getValue()
                        && participationLevel <= ApplyValidEnum.MAX_PARTICIPATION_LEVEL.getValue());

        if(!isValid) {
            throw new ApplyArgumentException(ErrorCode.BAD_REQUEST, ApplyValidEnum.ERROR_MESSAGE_PARTICIPATION_LEVEL);
        }
    }

    private static void validateGrowLevel(int growLevel) {
        boolean isValid = (growLevel >= ApplyValidEnum.MIN_GROW_LEVEL.getValue()
                        && growLevel <= ApplyValidEnum.MAX_GROW_LEVEL.getValue());

        if(!isValid) {
            throw new ApplyArgumentException(ErrorCode.BAD_REQUEST, ApplyValidEnum.ERROR_MESSAGE_GROW_LEVEL);
        }
    }



    @Getter
    @AllArgsConstructor
    private static enum ApplyValidEnum{
        MIN_LENGTH_NAME(2),
        MAX_LENGTH_NAME(10),
        MIN_AGE(22),
        MAX_AGE(30),
        MIN_PARTICIPATION_LEVEL(0),
        MAX_PARTICIPATION_LEVEL(10),
        MIN_GROW_LEVEL(0),
        MAX_GROW_LEVEL(10);

        private final int value;

        //에러 메세지
        private static final String ERROR_MESSAGE_NAME =
                "이름은 " + MIN_LENGTH_NAME.getValue() + "자 이상 " + MAX_LENGTH_NAME.getValue() + "자 이하로 입력해주세요.";

        private static final String ERROR_MESSAGE_PHONE_NUMBER =
                "전화번호는 010으로 시작하는 11자리 숫자여야 합니다.";

        private static final String ERROR_MESSAGE_AGE =
                "나이는 " + MIN_AGE.getValue() + "세 이상 " + MAX_AGE.getValue() + "세 이하로 입력해주세요.";

        private static final String ERROR_MESSAGE_PARTICIPATION_LEVEL =
                "참여도는 " + MIN_PARTICIPATION_LEVEL.getValue() + "이상 " + MAX_PARTICIPATION_LEVEL.getValue() + "이하로 입력해주세요.";

        private static final String ERROR_MESSAGE_GROW_LEVEL =
                "성장도는 " + MIN_GROW_LEVEL.getValue() + "이상 " + MAX_GROW_LEVEL.getValue() + "이하로 입력해주세요.";
    }


}
