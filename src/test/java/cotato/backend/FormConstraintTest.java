package cotato.backend;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cotato.backend.domain.Form;
import cotato.backend.domain.attribute.Age;
import cotato.backend.domain.attribute.Engagement;
import cotato.backend.domain.attribute.Generation;
import cotato.backend.domain.attribute.GrowthDesire;
import cotato.backend.domain.attribute.Part;
import cotato.backend.exception.invalidInfo.InvalidInfoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FormConstraintTest {
    @Test
    @DisplayName("모든 제약 조건을 만족하는 경우 통과")
    void all_valid_ok() {
        new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                GrowthDesire.UPPER_BOUND, "01012345678");
    }

    @Test
    @DisplayName("이름이 영문인 경우 에러")
    void name_english_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("abc", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("이름이 숫자인 경우 에러")
    void name_number_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("1234", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("이름이 글자 제한 수를 초과한 경우 에러")
    void name_overLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김수한무거북이와두루미", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND,
                        Engagement.UPPER_BOUND, GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("지원 기수가 하한 보다 작을 경우 에러")
    void generation_underLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND - 1, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("나이가 하한 보다 작을 경우 에러")
    void age_underLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND - 1, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("나이가 상한 보다 클 경우 에러")
    void age_overLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.UPPER_BOUND + 1, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("참여도가 하한 보다 작을 경우 에러")
    void engagement_underLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.LOWER_BOUND - 1,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("참여도가 상한 보다 클 경우 에러")
    void engagement_overLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND + 1,
                        GrowthDesire.UPPER_BOUND, "01012345678"));
    }

    @Test
    @DisplayName("성장 의지가 하한 보다 작을 경우 에러")
    void growthDesire_underLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.LOWER_BOUND - 1, "01012345678"));
    }

    @Test
    @DisplayName("성장 의지가 상한 보다 클 경우 에러")
    void growthDesire_overLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND + 1, "01012345678"));
    }

    @Test
    @DisplayName("휴대폰 번호 제한 길이를 초과하는 경우 에러")
    void phoneNumber_overLimit_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "010123456789"));
    }

    @Test
    @DisplayName("휴대폰 번호가 잘못 구성된 경우 에러")
    void phoneNumber_unformat_error() {
        assertThrows(InvalidInfoException.class,
                () -> new Form("김철수", Generation.LOWER_BOUND, Age.LOWER_BOUND, Part.BACKEND, Engagement.UPPER_BOUND,
                        GrowthDesire.UPPER_BOUND, "0101234567a"));
    }
}
