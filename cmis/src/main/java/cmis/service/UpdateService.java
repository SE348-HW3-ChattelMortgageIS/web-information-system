package cmis.service;

import cmis.dto.GeneralMessage;

public interface UpdateService {
    public boolean update();
    public GeneralMessage generateAlarm(String id);
}
