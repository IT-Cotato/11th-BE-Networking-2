package cotato.backend.domain.applicant;

import org.springframework.data.domain.Sort;

public enum ApplicantSortType {
    OLDEST(Sort.by(Sort.Order.asc("submittedAt"))),
    NEWEST(Sort.by(Sort.Order.desc("submittedAt"))),
    LIKES(Sort.by(Sort.Order.desc("likes"), Sort.Order.asc("submittedAt"))),
    PARTICIPATION(Sort.by(Sort.Order.desc("participationScore"), Sort.Order.asc("submittedAt"))),
    GROWTH(Sort.by(Sort.Order.desc("growthScore"), Sort.Order.asc("submittedAt")));

    private final Sort sort;

    ApplicantSortType(Sort sort) {
        this.sort = sort;
    }

    public Sort toSort() {
        return switch (this) {
            case NEWEST -> Sort.by(Sort.Direction.DESC, "submittedAt");
            case LIKES -> Sort.by(Sort.Direction.DESC, "likes").and(Sort.by(Sort.Direction.ASC, "submittedAt"));
            case PARTICIPATION -> Sort.by(Sort.Direction.DESC, "participationScore").and(Sort.by("submittedAt"));
            case GROWTH -> Sort.by(Sort.Direction.DESC, "growthScore").and(Sort.by("submittedAt"));
            case OLDEST -> Sort.by(Sort.Direction.ASC, "submittedAt");
        };
    }
}
