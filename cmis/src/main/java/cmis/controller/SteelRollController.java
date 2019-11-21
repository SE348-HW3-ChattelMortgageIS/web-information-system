package cmis.controller;

import cmis.dto.CreateSteelRollRequest;
import cmis.entity.SteelRoll;
import cmis.entity.UserEntity;
import cmis.service.SteelRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cmis.dto.GeneralMessage;
import cmis.dto.RegisterRequest;
import cmis.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class SteelRollController {

    @Autowired
    private SteelRollService steelRollService;
    @RequestMapping(value = "/findAllSteelRoll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<SteelRoll> findAllSteelRoll() {
        return this.steelRollService.queryAllSteelRoll();
    }
    @RequestMapping(value = "/deleteSteelRoll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    GeneralMessage delteSteelRoll(int id) {
        return steelRollService.delete(id);
    }
    @RequestMapping(value = "/verifySteelRoll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    GeneralMessage verifySteelRoll(int id) {
        return steelRollService.verify(id);
    }
    @RequestMapping(value = "/createSteelRoll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    GeneralMessage createSteelRoll(@RequestBody CreateSteelRollRequest createSteelRollRequest) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BigDecimal price = createSteelRollRequest.getPrice();
        String remark = createSteelRollRequest.getRemark();
        return this.steelRollService.create(price,remark,principal);
    }
}
