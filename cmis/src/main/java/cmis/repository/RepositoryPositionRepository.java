package cmis.repository;

import cmis.entity.RepositoryPosition;
import org.springframework.data.jpa.repository.JpaRepository;


   

import java.util.List;
public interface RepositoryPositionRepository extends JpaRepository<RepositoryPosition, Integer> {
    List<RepositoryPosition> findAll();
     RepositoryPosition findByPositionId(int id);
     RepositoryPosition findByInUseFalse();
}
