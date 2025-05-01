package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.dto.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.ApplicantSearchCondition;
import cotato.backend.domain.applicant.entity.Applicant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cotato.backend.domain.applicant.entity.QApplicant.applicant;

@Repository
@RequiredArgsConstructor
public class ApplicantRepositoryImpl implements ApplicantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ApplicantListResponse> findApplicants(ApplicantSearchCondition condition, Pageable pageable) {
        List<Applicant> content = queryFactory
                .selectFrom(applicant)
                .orderBy(getSort(condition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(applicant.count())
                .from(applicant)
                .fetchOne();

        List<ApplicantListResponse> responseList = content.stream()
                .map(ApplicantListResponse::from)
                .toList();

        return new PageImpl<>(responseList, pageable, total);
    }

    private com.querydsl.core.types.OrderSpecifier<?> getSort(ApplicantSearchCondition condition) {
        return switch (condition) {
            case OLDEST -> applicant.submittedAt.asc();
            case LATEST -> applicant.submittedAt.desc();
            case MOST_LIKES -> applicant.likes.desc().nullsLast();
            case HIGH_PARTICIPATION -> applicant.participation.desc().nullsLast();
            case HIGH_GROWTH -> applicant.growth.desc().nullsLast();
        };
    }
}