package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.ExceptionAlarm;
import cmis.entity.ExceptionAlarm.AlarmState;
import cmis.repository.ExceptionAlarmRepository;
import cmis.service.AlarmService;
import cmis.service.UpdateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImplement implements AlarmService {
  @Autowired
  private ExceptionAlarmRepository exceptionAlarmRepository;
  @Autowired
  private UpdateService updateService;

  public GeneralMessage getAlarm() {
    updateService.update();
    List<ExceptionAlarm> alarms = this.exceptionAlarmRepository.findAll();
    return new GeneralMessage(200, "ok", true, alarms);
  }

  public GeneralMessage readAlarm(int id) {
    ExceptionAlarm alarm;
    if (this.exceptionAlarmRepository.findById(id).isPresent()) {
      alarm = this.exceptionAlarmRepository.findById(id).get();
    } else {
      return new GeneralMessage(404, "alarm id not found", false, id);
    }
    alarm.setAlarmState(AlarmState.READ);
    alarm = this.exceptionAlarmRepository.save(alarm);
    return new GeneralMessage(200, "ok", true, alarm);
  }

  public GeneralMessage fixAlarm(int id) {
    ExceptionAlarm alarm;
    if (this.exceptionAlarmRepository.findById(id).isPresent()) {
      alarm = this.exceptionAlarmRepository.findById(id).get();
    } else {
      return new GeneralMessage(404, "alarm id not found", false, id);
    }
    alarm.setAlarmState(AlarmState.SOLVED);
    alarm = this.exceptionAlarmRepository.save(alarm);
    return new GeneralMessage(200, "ok", true, alarm);
  }

}
