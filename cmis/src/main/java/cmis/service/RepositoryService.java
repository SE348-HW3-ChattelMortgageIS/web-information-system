package cmis.service;

import cmis.dto.GeneralMessage;

public interface RepositoryService {
    GeneralMessage lookRepositoryMessage();
    GeneralMessage alarmRepositoryMessage();
}
