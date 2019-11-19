package cmis.service;


import cmis.entity.SteelRoll;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface SteelRollService {

    public List<SteelRoll> queryAllSteelRoll();
    public int delete(int id);
    public int verify(int id);
    public int create(BigDecimal price, String remark, User principal);
}
