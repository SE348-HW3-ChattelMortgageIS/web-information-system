package cmis.repository;

import cmis.entity.DepositReceipt;
import cmis.entity.ExceptionAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface ExceptionAlarmRepository extends JpaRepository<ExceptionAlarm, Integer> {
    ExceptionAlarm findByDepositReceipt(DepositReceipt depositReceipt);
    @Transactional
    @Modifying
    @Query(value = "update  exception_alarm set start_At = ?2 where alarm_id= ?1 ",nativeQuery = true)
    int updateTime(int id, Date data);
}
