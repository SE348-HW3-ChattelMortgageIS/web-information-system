package cmis.repository;

import cmis.entity.SteelRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface SteelRollRepository extends JpaRepository<SteelRoll, Integer> {
    List<SteelRoll> findAll();
    @Transactional
    @Modifying
    @Query(value = "delete from steel_roll where steel_roll_id= ?1",nativeQuery = true)
    int deleteSteelRoll(int steel_roll_id);
    @Transactional
    @Modifying
    @Query(value = "update  steel_roll set steel_roll_state = 1 where steel_roll_id= ?1 and steel_roll_state = 0",nativeQuery = true)
    int verifySteelRoll(int steel_roll_id);
    @Transactional
    @Modifying
    @Query(value = "update  steel_roll set steel_roll_state = 5 where steel_roll_id= ?1 ",nativeQuery = true)
    int abnormal(int id);
    SteelRoll findBySteelRollId(Integer id);
    List<SteelRoll> findByMovableFalse();

}
