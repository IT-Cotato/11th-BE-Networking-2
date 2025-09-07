package cotato.backend.domain.example.dao;

import cotato.backend.domain.example.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {


}
