package cmis.repository;

import cmis.entity.SteelRoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SteelRollRepository extends JpaRepository<SteelRoll, Integer> {
    SteelRoll findBySteelRollId(Integer id);
}
