package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.RepositoryPosition;
import cmis.repository.RepositoryPositionRepository;
import cmis.service.RepositoryService;
import cmis.service.getMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryServiceImplement implements RepositoryService {
    @Autowired
    private RepositoryPositionRepository repositoryPositionRepository;


    public GeneralMessage lookRepositoryMessage(){
        try {
            List<RepositoryPosition> listRepo = repositoryPositionRepository.findAll();
            return new GeneralMessage(200, "get repository message", true, listRepo);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
    }
}
