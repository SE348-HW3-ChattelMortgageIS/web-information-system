package cmis.service;

import cmis.dto.GeneralMessage;

public interface getMessageService {
    public GeneralMessage getStatus(String id);
    public GeneralMessage testStatus(String id);
}
