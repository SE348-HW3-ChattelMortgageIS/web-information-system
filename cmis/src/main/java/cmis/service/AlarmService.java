package cmis.service;

import cmis.dto.GeneralMessage;

public interface AlarmService {
  GeneralMessage getAlarm();
  GeneralMessage readAlarm(int id);
  GeneralMessage fixAlarm(int id);
}
