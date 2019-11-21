package cmis.service;


import cmis.dto.GeneralMessage;
import cmis.entity.SteelRoll;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface SteelRollService {

    public List<SteelRoll> queryAllSteelRoll();
    public GeneralMessage delete(int id);
    public GeneralMessage verify(int id);
    public GeneralMessage create(BigDecimal price, String remark, User principal);
}
