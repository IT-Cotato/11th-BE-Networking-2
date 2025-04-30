package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.dto.info.ApplyLikeInfo;
import cotato.backend.domain.example.entity.Apply;
import cotato.backend.domain.example.entity.ApplyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyLikeRepository extends JpaRepository<ApplyLike, ApplyLike.ApplyLikeId> {


    @Query("SELECT COUNT(l) FROM ApplyLike l WHERE l.apply.applyId = :applyId")
    int countLikesByApplyId(@Param("applyId") Long applyId);

    //좋아요가 이미 존재하는지 확인
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM ApplyLike l WHERE l.apply.applyId = :applyId AND l.manager.managerId = :managerId")
    boolean existsApplyLike(@Param("applyId") Long applyId, @Param("managerId") Long managerId);

    @Query(value =
            "SELECT APPLY_ID AS APPLY_ID, COUNT(*) AS LIKE_COUNT " +
            "FROM APPLY_LIKE " +
            "GROUP BY APPLY_ID " +
            "ORDER BY LIKE_COUNT DESC " +
            "LIMIT :page, :size", nativeQuery = true)
    List<ApplyLikeInfo> findApplyListsByOrderByLikeCountDesc(@Param("page") int page,
                                                             @Param("size") int size);

    @Modifying
    @Query(value = "INSERT INTO apply_like (apply_id, manager_id) VALUES (:applyId, :managerId)", nativeQuery = true)
    void insertApplyLike(@Param("applyId") Long applyId, @Param("managerId") Long managerId);

}
