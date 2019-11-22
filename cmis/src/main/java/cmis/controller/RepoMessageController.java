package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class RepoMessageController {
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(value = "/repositorymessage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GeneralMessage repoMessage() {
        return this.repositoryService.lookRepositoryMessage();
    }
}
