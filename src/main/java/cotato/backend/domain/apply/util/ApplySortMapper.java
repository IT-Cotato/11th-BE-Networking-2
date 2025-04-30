package cotato.backend.domain.apply.util;

import org.springframework.data.domain.Sort;

public class ApplySortMapper {
    public static Sort getSort(String sortKey) {
        return switch (sortKey) {
            case "submitTimeDesc" -> Sort.by(Sort.Order.desc("submitTime"));
            case "likeCountDesc" -> Sort.by(Sort.Order.desc("likeCount"), Sort.Order.asc("submitTime"));
            case "participationScoreDesc" -> Sort.by(Sort.Order.desc("participationScore"), Sort.Order.asc("submitTime"));
            case "growthScoreDesc" -> Sort.by(Sort.Order.desc("growthScore"), Sort.Order.asc("submitTime"));
            default -> Sort.by(Sort.Order.asc("submitTime"));
        };
    }
}
