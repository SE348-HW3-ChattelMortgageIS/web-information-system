package cmis.repository;

import cmis.entity.RepositoryPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
public interface RepositoryPositionRepository extends JpaRepository<RepositoryPosition, Integer> {
    List<RepositoryPosition> findAll();
    RepositoryPosition findByPositionId(int id);
    @Transactional
    @Modifying
    @Query(value = "update  repository_position set in_use = true where position_id= ?1",nativeQuery = true)
    int inUse(int id);
    @Transactional
    @Modifying
    @Query(value = "update  repository_position set in_use = false where position_id= ?1",nativeQuery = true)
    int notUse(int id);
    List<RepositoryPosition> findByInUseFalse();
}
