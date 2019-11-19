package cmis.repository;

import cmis.entity.ExceptionAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionAlarmRepository extends JpaRepository<ExceptionAlarm, Integer> {

}
