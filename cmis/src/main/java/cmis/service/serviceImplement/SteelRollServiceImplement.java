package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.RepositoryPosition;
import cmis.entity.SteelRoll;
import cmis.entity.UserEntity;
import cmis.repository.RepositoryPositionRepository;
import cmis.repository.SteelRollRepository;
import cmis.repository.UserEntityRepository;
import cmis.service.SteelRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Service
public class SteelRollServiceImplement implements SteelRollService {

    @Autowired
    private SteelRollRepository steelRollEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RepositoryPositionRepository repositoryPositionRepository;
    public List<SteelRoll> queryAllSteelRoll(){
        return steelRollEntityRepository.findAll();
    }
    public GeneralMessage delete(int id){
        if(steelRollEntityRepository.deleteSteelRoll(id) != 0){
            return new GeneralMessage(200, "delete", true, null);
        }
        return new GeneralMessage(500,"delete failed",false,null);
    }
    public GeneralMessage verify(int id){
        if(steelRollEntityRepository.verifySteelRoll(id) != 0){
            return new GeneralMessage(200,"verify",true,null);
        }
        return new GeneralMessage(500,"verify failed",false,null);
    }
    public GeneralMessage create(BigDecimal price, String remark, User principal){
        String username = principal.getUsername();
        UserEntity userEntity = userEntityRepository.findByUsername(username);
        RepositoryPosition repositoryPosition = repositoryPositionRepository.findByPositionId(1);
        int userId = userEntity.getUserId();
        SteelRoll steelRoll = new SteelRoll();
        steelRoll.setBelongTo(userEntity);
        steelRoll.setMovable(true);
        steelRoll.setPosition(repositoryPosition);
        steelRoll.setPrice(price);
        steelRoll.setRemark(remark);
        steelRoll.setSteelRollState(SteelRoll.SteelRollState.TO_BE_VERIFIED);
        try{steelRollEntityRepository.save(steelRoll);}
        catch(Exception e){
            return new GeneralMessage(500,e.getMessage(),false,null);
        }
        return new GeneralMessage(200,"create",true,null);
    }
}
