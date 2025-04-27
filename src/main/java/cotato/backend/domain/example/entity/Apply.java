package cotato.backend.domain.example.entity;

import cotato.backend.common.code.ApplyPartGubun;
import cotato.backend.common.code.GisuGubun;
import cotato.backend.common.converter.ApplyPartGubunConverter;
import cotato.backend.common.converter.GisuGubunConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apply")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    private String name;

    @Convert(converter = GisuGubunConverter.class)
    private GisuGubun gisuGubun;

    private int age;

    @Convert(converter = ApplyPartGubunConverter.class)
    private ApplyPartGubun applyPartGubun;

    private String phoneNumber;

    private int participationLevel;

    private int growLevel;

    private LocalDateTime applyTime;

    public void createdAt(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }


}
